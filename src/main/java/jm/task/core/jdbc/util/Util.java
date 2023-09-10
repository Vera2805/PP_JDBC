package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/mypp";
    private static final String user = "root";
    private static final String password = "root";

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }

    private static SessionFactory sessionFactory = buildSessionFactory();

    protected static SessionFactory buildSessionFactory() {
        try {

            sessionFactory = new Configuration()
                    .configure()
                    .addAnnotatedClass(User.class)
                    .buildSessionFactory();
        } catch (Throwable ex) {
            System.out.println("Failed to create SessionFactory object." + ex);
            throw  new ExceptionInInitializerError(ex);
        }
        return sessionFactory;
    }


    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

}
