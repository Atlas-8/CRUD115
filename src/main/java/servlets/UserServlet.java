package servlets;

import service.Service;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/user")
public class UserServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        Service service = Service.getInstance();
        try {
            service.getUserByName(req.getParameter("name"));
            resp.setContentType("text/html");
            RequestDispatcher dispatcher = req.getRequestDispatcher("/userPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            resp.sendRedirect("/");
        }
    }
}