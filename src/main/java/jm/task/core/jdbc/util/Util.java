package jm.task.core.jdbc.util;

import java.sql.*;


public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/mypp";
    private static final String user = "root";
    private static final String password = "root";


    private static Connection connection;

    public static  Connection getConnection() {
        try {
            connection= DriverManager.getConnection(url, user, password);

        } catch (SQLException sqlEx) {
            System.out.println("connect ERROR");
            sqlEx.printStackTrace();
        }

        return connection;
    }
}
