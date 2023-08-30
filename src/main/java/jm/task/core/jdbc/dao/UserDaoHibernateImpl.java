package jm.task.core.jdbc.dao;
import org.hibernate.query.Query;
import jm.task.core.jdbc.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.SessionBuilder;
import org.hibernate.QueryException;

import java.sql.Connection;
import java.util.List;


public class UserDaoHibernateImpl implements UserDao {
    private  SessionFactory sessionFactory;

    public UserDaoHibernateImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }


    public void createUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
         String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL" +
                "AUTO_INCREMENT PRIMARY KEY," +
                "name VARCHAR (255) NOT NULL,lastName VARCHAR (255) NOT NULL, age INT NOT NULL)";
        session.createSQLQuery(sql).executeUpdate();
        transaction.commit();
        session.close();
    }

    public void dropUsersTable() {
        Session session = sessionFactory().openSession();
        Transaction transaction = session.beginTransaction();
        String sql = "DROP TABLE IF EXISTS users";
        session.createSQLQuery(sql).executeUpdate();
            transaction().commit();
        session.close();
    }



    public void saveUser(String name, String lastName, byte age) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        User user = new User(name, lastName, age);
        session.save(user);
        transaction().commit();
        session.close();
    }

    public void removeUserById(long id) {
        Session session = sessionFactory.openSession();
         Transaction transaction = session.beginTransaction();
        User user = session.get(User.class, id);
        if (user != null) {
            session.delete(user);
        }
        transaction().commit();
        session.close();

    }




    public List<User> getAllUsers() {
            Session session = sessionFactory.openSession();
            List<User> users ;
            try {
            users = session.createHQLQuery("FROM User", User.class).getResultList();
        } finally {
            session.close();
        }
        return users;
    }

    public void cleanUsersTable() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();
        session.createQuery("DELETE FROM User").executeUpdate();
        transaction.commit();
        session.close();

    }
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
}
