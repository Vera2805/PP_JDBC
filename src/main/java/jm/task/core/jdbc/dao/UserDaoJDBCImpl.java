package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    private Connection connection;

    public UserDaoJDBCImpl(Connection connection) {
        this.connection = connection;
    }

    public UserDaoJDBCImpl() {

    }

    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +

                "name VARCHAR(255), lastName VARCHAR(255), age INT)");

        statement.close();
        connection.close();
    }


    public void dropUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("DROP TABLE IF EXISTS users");
        connection.commit();

        if (connection != null) {
            connection.rollback();
            connection.close();
        }

    }



    public void saveUser(String name, String lastName, byte age) throws SQLException {

        Connection connection = Util.getConnection();

        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("INSERT INTO users(name, lastName,age)" +
                    " VALUES(?,?,?)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        try {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, name);
            preparedStatement.setString(2, lastName);
            preparedStatement.setByte(3, age);
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            try {
                connection.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);

            } finally {
                try {
                    if (preparedStatement != null) {
                        preparedStatement.close();
                    }
                    connection.close();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }


    public List<User> getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName (resultSet.getString("name"));
            user.setLastName (resultSet.getString("lastName"));
            user.setAge (resultSet.getByte("age"));
            userList.add(user);
        }
        resultSet.close();
        statement.close();
        connection.close();

        return userList;

    }


    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }
}



