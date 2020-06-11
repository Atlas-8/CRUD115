package servlets;

import model.User;
import service.Service;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/saveUser")
public class AdditionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service service = Service.getInstance();
        String name = req.getParameter("name");
        String role = req.getParameter("role");
        long age  = 0;
        try {
            age = Long.parseLong(req.getParameter("age"));
        } catch (NumberFormatException e) {
            age = 0;
        }
        User user = new User(name, age, role);
        if (age > 0) {
            try {
                service.addUser(user);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        resp.sendRedirect("/admin");
    }
}