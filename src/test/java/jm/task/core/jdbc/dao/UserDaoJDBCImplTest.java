package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.util.Util;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class UserDaoJDBCImplTest extends TestCase {

    public void testCleanUsersTable() {

        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users WHERE id !=0");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}