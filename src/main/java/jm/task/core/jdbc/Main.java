package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDao;
import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) throws SQLException {
     UserDao userDao= new UserDaoJDBCImpl();
     userDao.createUsersTable();
     userDao.saveUser("Ivan","Ivanov",(byte) 25);
     userDao.saveUser("Petr","Petrov",(byte) 35);
     userDao.saveUser("Sidor","Sidorov",(byte) 45);
     userDao.saveUser("Fedor","Pirogov",(byte) 55);
     userDao.getAllUsers();
     userDao.cleanUsersTable();
     userDao.dropUsersTable();
    }
}
