package service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import DAO.UserHibernateDAO;
import DAO.UserJdbcDAO;
import model.User;
import org.hibernate.SessionFactory;
import util.DBHelper;

import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.*;
import java.util.List;

import static DAO.UserDaoFactory.*;

public class UserService {

    private static UserService userService;
    private UserDAO userDAO;

    private UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public static UserService getInstance() throws IOException {
        if (userService == null) {
            userService = new UserService(UserDaoFactory.getUserDAO());
        }
        return userService;
    }

    public User getUserById(long id) throws Exception {
        try {
            return userDAO.getUserById(id);
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public User getUserByName(String name) throws ServletException {
        try {
            return userDAO.getUserByName(name);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public List<User> getAllUsers() throws ServletException {
        try {
            userDAO.createTable();
            return userDAO.getAllUsers();
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    public void deleteUserById(long id) throws SQLException {
        userDAO.deleteUserById(id);
    }

    public void addUser(User user) throws Exception {
        userDAO.addUser(user);
    }

    public void cleanUp() throws Exception {
        try {
            userDAO.dropTable();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }

    public void createTable() throws Exception{
        try {
            userDAO.createTable();
        } catch (SQLException e) {
            throw new Exception(e);
        }
    }


}
