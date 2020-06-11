package DAO;

import model.User;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserJdbcDAO implements UserDAO {

    private Connection connection;

    public UserJdbcDAO(Connection connection) {
        this.connection = connection;
    }

    public long getUserIdByName(String name) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users WHERE name='" + name + "';");
        ResultSet result = stmt.getResultSet();
        result.next();
        long id = result.getLong(1);
        result.close();
        stmt.close();
        return id;
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("SELECT * FROM users;");
        ResultSet result = stmt.getResultSet();
        List<User> usersList = new ArrayList<>();
        while (result.next()) {
            usersList.add(new User(result.getLong("id"), result.getString("name"),
                    result.getLong("age"), result.getString("role")));
        }
        result.close();
        stmt.close();
        return usersList;
    }

    @Override
    public User getUserById(long id) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM users WHERE id='" + id + "';");
            try (ResultSet result = stmt.getResultSet()) {
                result.next();
                return new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getLong("age"),
                        result.getString("role"));
            }
        }
    }

    @Override
    public void deleteUserById(long id) throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DELETE FROM users WHERE id=" + id);
        stmt.close();
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        try (Statement stmt = connection.createStatement()) {
            stmt.execute("SELECT * FROM users WHERE name='" + name + "';");
            try (ResultSet result = stmt.getResultSet()) {
                result.next();
                return new User(
                        result.getLong("id"),
                        result.getString("name"),
                        result.getLong("age"),
                        result.getString("role"));
            }
        }
    }

    @Override
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (id, name, age, role) VALUES (?, ?, ?, ?);";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setLong(1, user.getId());
        statement.setString(2, user.getName());
        statement.setLong(3, user.getAge());
        statement.setString(4, user.getRole());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void updateUser(User user) throws SQLException{
        String sql = "UPDATE users SET name = ? , age = ?, role = ? WHERE id = ?;";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, user.getName());
        statement.setLong(2, user.getAge());
        statement.setString(3, user.getRole());
        statement.setLong(4, user.getId());
        statement.executeUpdate();
        statement.close();
    }

    @Override
    public void createTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.execute("CREATE TABLE IF NOT EXISTS users (id BIGINT AUTO_INCREMENT, " +
                "name VARCHAR(256), age BIGINT, role VARCHAR(8), PRIMARY KEY (id))");
        stmt.close();
    }

    @Override
    public void dropTable() throws SQLException {
        Statement stmt = connection.createStatement();
        stmt.executeUpdate("DROP TABLE IF EXISTS user");
        stmt.close();
    }

}
