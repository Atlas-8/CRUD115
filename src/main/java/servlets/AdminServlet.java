package servlets;

import model.User;
import service.Service;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(urlPatterns = "/admin")
public class AdminServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        List<User> users = service.getAllUsers();
        try {
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("users", users);
            RequestDispatcher dispatcher = req.getRequestDispatcher("/adminPage.jsp");
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            resp.sendRedirect("/");
        }
    }
}