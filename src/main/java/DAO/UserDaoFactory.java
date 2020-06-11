package DAO;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;
import util.DBHelper;
import util.PropertyReader;

import java.io.IOException;
import java.util.Properties;

public class UserDaoFactory {

    private static SessionFactory sessionFactory;

    public static UserDAO getUserDAO() throws IOException {
        Properties property = PropertyReader.read();
        String jpa = property.getProperty("daotype");
        if (jpa.equals("UserJdbcDAO")){
            return new UserJdbcDAO(DBHelper.getConnection());
        }else if (jpa.equals("UserHibernateDAO")){
            return new UserHibernateDAO(getSessionFactory().openSession());
        } else {
            throw new IOException("Data base not recognised");
        }
    }

    private static SessionFactory getSessionFactory() {
        if (sessionFactory == null) {
            sessionFactory = createSessionFactory();
        }
        return sessionFactory;
    }

    private static SessionFactory createSessionFactory() {
        DBHelper.getInstance();
        org.hibernate.cfg.Configuration configuration = DBHelper.getConfiguration();
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        return configuration.buildSessionFactory(serviceRegistry);
    }

}
