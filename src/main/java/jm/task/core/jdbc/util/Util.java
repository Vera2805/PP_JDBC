package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDao;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/mypp";
    private static final String user = "root";
    private static final String password = "root";
    // private static java.sql.SQLException SQLException;
    private static  SessionFactory sessionFactory;

    static {
        SessionFactory factory = null;
        try {
            factory = new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(UserDao.class)
                    .buildSessionFactory();

            Session session = factory.getCurrentSession();
            session.beginTransaction();
            session.getTransaction().commit();

        } finally {
            factory.close();


        }
    }


    public static Connection getConnection() throws SQLException {
        return
                DriverManager.getConnection(url, user, password);
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
    public static void shutdown() {
        getSessionFactory().close();
    }
}
