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
import java.sql.SQLException;

@WebServlet(urlPatterns = "/updateUser")
public class UpdationServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        Service service = Service.getInstance();
        long id = Long.parseLong(req.getParameter("id"));
        String name = req.getParameter("name");
        long age = Long.parseLong(req.getParameter("age"));
        String role;
        try {
            if (req.getParameter("role") != null) {
                role = req.getParameter("role");
            } else {
                role = service.getUserById(id).getRole();
            }
        } catch (SQLException e) {
            role = "user";
        }
        User user = new User(id, name, age, role);
        try {
            service.updateUser(user);
        } catch (Exception e) {
            e.printStackTrace();
        }
        resp.sendRedirect("/admin");
    }
}