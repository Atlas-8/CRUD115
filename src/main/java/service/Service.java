package service;

import DAO.UserDAO;
import DAO.UserDaoFactory;
import model.User;
import javax.servlet.ServletException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

public interface Service {

    static Service getInstance() throws IOException {
        return UserService.getInstance();
    }

    User getUserById(long id) throws SQLException;

    User getUserByName(String name) throws ServletException;

    List<User> getAllUsers() throws ServletException;

    void deleteUserById(long id) throws SQLException;

    void addUser(User user) throws Exception;

    void updateUser(User user) throws SQLException;

    void cleanUp() throws Exception;

    void createTable() throws Exception;

}
