package servlets;

import service.Service;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebFilter(urlPatterns = "/validate")
public class FilterServlet implements Filter {

    private String userRole = "unknown";
    long userAge;

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        String login = request.getParameter("name");
        HttpServletResponse resp = (HttpServletResponse) response;
        if (!request.getParameter("age").matches("[0-9]+") || login.isEmpty()){
            RequestDispatcher dispatcher = request.getRequestDispatcher("/");
            dispatcher.forward(httpRequest, response);
        }
        Service service = Service.getInstance();
        HttpSession session = httpRequest.getSession();
        try {
            userRole = service.getUserByName(login).getRole();
            userAge = service.getUserByName(login).getAge();
        } catch (ServletException e) {
            request.setAttribute("entry error", "do nothing");
        } catch (NullPointerException e2) {
            request.setAttribute("another entry error", "do nothing");
        }
        session.setAttribute("status", userRole);
        if (userRole.equals("admin") & Long.parseLong(request.getParameter("age")) == userAge) {
            httpRequest.setAttribute("name", login);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/admin");
            dispatcher.forward(httpRequest, response);
        } else if (userRole.equals("user") & Long.parseLong(request.getParameter("age")) == userAge) {
            httpRequest.setAttribute("name", login);
            httpRequest.setAttribute("age", userAge);
            RequestDispatcher dispatcher = request.getRequestDispatcher("/user");
            dispatcher.forward(httpRequest, response);
        } else {
            resp.sendRedirect("/");
        }
    }
}