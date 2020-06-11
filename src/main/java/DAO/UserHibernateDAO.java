package DAO;

import model.User;
import org.hibernate.*;

import java.sql.SQLException;
import java.util.List;

public class UserHibernateDAO implements UserDAO {


    private final Session session;

    public UserHibernateDAO(Session session) {
        this.session = session;
    }

    @Override
    public List<User> getAllUsers() {
        Transaction transaction = session.beginTransaction();
        List<User> users = session.createQuery("FROM User").list();
        transaction.commit();
        session.clear();
        return users;
    }

    @Override
    public User getUserById(long id) {
        String hql = "FROM User WHERE id = :id";
        Query query = session.createQuery(hql);
        query.setParameter("id", id);
        session.clear();
        return (User) query.uniqueResult();
    }

    @Override
    public void deleteUserById(long id) throws SQLException {
        String sql = "delete from users where id=" + id;
        session.beginTransaction();
        Query query = session.createSQLQuery(sql);
        int deleted = query.executeUpdate();
        query.executeUpdate();
        session.getTransaction().commit();
    }

    @Override
    public User getUserByName(String name) throws SQLException {
        String hql = "FROM User WHERE name = :name";
        Query query = session.createQuery(hql);
        query.setParameter("name", name);
        session.clear();
        return (User) query.uniqueResult();
    }

    @Override
    public void addUser(User user) throws SQLException {
        session.beginTransaction();
        session.save(user);
        session.getTransaction().commit();
        session.clear();
    }

    @Override
    public void updateUser(User user) throws SQLException {
        Transaction transaction = session.beginTransaction();
        session.createQuery("UPDATE User SET name =:name, age =:age, role =:role where id = :id")
                .setParameter("name", user.getName())
                .setParameter("age", user.getAge())
                .setParameter("role", user.getRole())
                .setParameter("id", user.getId())
                .executeUpdate();
        transaction.commit();
        session.clear();
    }

    @Override
    public void createTable() throws SQLException {

    }

    @Override
    public void dropTable() throws SQLException {

    }

}
