package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import java.util.List;

import static jm.task.core.jdbc.util.Util.getSessionFactory;


public class UserDaoHibernateImpl implements UserDao {

    private final SessionFactory sessionFactory = Util.getSessionFactory();

    public UserDaoHibernateImpl() {
    }

    public void createUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            String sql = "CREATE TABLE IF NOT EXISTS users (id BIGINT NOT NULL " +
                    "AUTO_INCREMENT PRIMARY KEY," +
                    "name VARCHAR (255) NOT NULL,lastName VARCHAR (255) NOT NULL, age INT NOT NULL)";
            session.createSQLQuery(sql).executeUpdate();
            transaction.commit();
            System.out.println("Created table in DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {

            if (session != null) {
                session.close();
            }
        }
    }

    public void dropUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            String sql = "DROP TABLE IF EXISTS users";
            session.createSQLQuery(sql).executeUpdate();
            System.out.println("Deleted table in DB");
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = getSessionFactory().openSession();
            transaction = session.beginTransaction();
            User user = new User(name, lastName, age);
            session.save(user);
            transaction.commit();
            System.out.println("Create user: "+ name + lastName + age);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public void removeUserById(long id) {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            User user = session.get(User.class, id);
            if (user != null) {
                session.delete(user);
                transaction.commit();
                System.out.println("Deleted user of table in DB");
            }

        } catch (NullPointerException e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

    public List<User> getAllUsers() {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            List<User> users = session.createQuery("FROM User", User.class).getResultList();
            return users;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
        return null;
    }

    public void cleanUsersTable() {
        Session session = null;
        Transaction transaction = null;
        try {
            session = sessionFactory.openSession();
            transaction = session.beginTransaction();
            session.createQuery("DELETE FROM User").executeUpdate();
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null) {
                session.close();
            }
        }
    }

}
