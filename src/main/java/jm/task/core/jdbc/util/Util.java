package jm.task.core.jdbc.util;

import java.sql.*;
import java.sql.SQLException;

public class Util {
    // реализуйте настройку соеденения с БД
    private static final String url = "jdbc:mysql://localhost:3306/mypp";
    private static final String user = "root";
    private static final String password = "root";
    private static java.sql.SQLException SQLException;

    public static Connection getConnection() throws SQLException {

        Connection connection = DriverManager.getConnection(url, user, password);

        return DriverManager.getConnection(url, user, password);
    }
}
