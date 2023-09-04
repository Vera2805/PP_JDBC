package jm.task.core.jdbc.util;

import jm.task.core.jdbc.dao.UserDao;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {

    private static final String url = "jdbc:mysql://localhost:3306/mypp";
    private static final String user = "root";
    private static final String password = "root";

   private static  SessionFactory sessionFactory;

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, user, password);
    }
    public static SessionFactory  getSessionFactory () {
        if (sessionFactory == null) {
              try {Configuration configuration = new Configuration()
                          .configure("hibernate.cfg.xml")
                         .addAnnotatedClass(UserDao.class);
                   sessionFactory = configuration.buildSessionFactory();
               } catch (Exception e) {
                   e.printStackTrace();
               }
           }
        return sessionFactory;
       }

}
