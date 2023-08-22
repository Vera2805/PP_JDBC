package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {
    public void add(User user) throws SQLException {
        Connection connection = Util.getConnection();
        PreparedStatement preparedStatement = null;
        String sql = "INSERT INTO users (name, lastName, age, id) VALUES (?,?,?,?)";

        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, user.getName());
        preparedStatement.setString(2, user.getLastName());
        preparedStatement.setByte(3, user.getAge());
        preparedStatement.setLong(4, user.getId());
        preparedStatement.executeUpdate();

        preparedStatement.close();
        connection.close();
    }

    @Override
    public void createUsersTable() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS users " +
                "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +

                "name VARCHAR(255), lastName VARCHAR(255), age INT)");

        statement.close();
        connection.close();
    }

    @Override
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


    @Override
    public void saveUser(String name, String lastName, byte age) {

    }


    public void saveUser(User user) throws SQLException {
        Connection connection = Util.getConnection();

        PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO users(name, lastName,age)" +
                " VALUES(?,?,?)");
        try {
            connection.setAutoCommit(false);
            preparedStatement.setString(1, user.getName());
            preparedStatement.setString(2, user.getLastName());
            preparedStatement.setByte(3, user.getAge());
            preparedStatement.executeUpdate();

            connection.commit();
        } catch (SQLException e) {
            connection.rollback();
            throw e;
        } finally {
            preparedStatement.close();
            connection.close();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        Connection connection = Util.getConnection();
        Statement statement = connection.createStatement();

        ResultSet resultSet = statement.executeQuery("SELECT * FROM users");
        List<User> userList = new ArrayList<>();
        while (resultSet.next()) {
            User user = new User();
            user.setId(resultSet.getLong("id"));
            user.setName = (resultSet.getString("name"));
            user.setlastName = (resultSet.getString("lastName"));
            user.setAge = (resultSet.getByte("age"));
            userList.add(user);
        }
        resultSet.close();
        statement.close();
        connection.close();

        return userList;

    }

    @Override
    public void cleanUsersTable() {
        try (Connection connection = Util.getConnection();
             Statement statement = connection.createStatement()) {
            statement.executeUpdate("DELETE FROM users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {


    }
}



