package jm.task.core.jdbc.service;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {
    Connection connection = Util.getConnection();
 /*   private UserDao userDao = new UserDaoJDBCImpl() {
        @Override
        public void createUsersTable() {
            userDao.createUsersTable();
        }

        public void dropUsersTable() {
            userDao.dropUsersTable();
        }

        public void saveUser(String name, String lastName, Byte age) {
            userDao.saveUser(name, lastName, age);
            System.out.println("User с именем-" + name + "добавлен в базу данных");
        }

        public List<User> getAllUsers() throws SQLException {
            return userDao.getAllUsers();
        }

        public void cleanUsersTable() {
            userDao.cleanUsersTable();
        }

      ////  @Override
      //  public void removeUserById() {
      //      userDao.removeUserById();
      //  }
    };
*/
    @Override
    public void createUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.execute("CREATE TABLE IF NOT EXISTS users " +
                    "(id BIGINT AUTO_INCREMENT PRIMARY KEY," +

                    "name VARCHAR(255), lastName VARCHAR(255), age INT)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void dropUsersTable() {
        try (Statement statement = connection.createStatement()) {
            statement.executeUpdate("DROP TABLE IF EXISTS users");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        try (PreparedStatement
                     statement = connection.prepareStatement("INSERT INTO users(name, lastName,age)" +
                " VALUES(?,?,?)")) {
            statement.setString(1, name);
            statement.setString(2, lastName);
            statement.setByte(3, age);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void removeUserById(long id) {
        try (PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id=?")) {
            statement.setLong(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<User> getAllUsers() throws SQLException {
        List<User> userList = new ArrayList<>();
        String sql = "SELECT * FROM users";
        Statement statement=null;
        try {
            statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getLong("id"));
                user.setName = (resultSet.getString("name"));
                user.setlastName = (resultSet.getString("lastName"));
                user.setAge = (resultSet.getByte("age"));
                userList.add(user);
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }
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
}


