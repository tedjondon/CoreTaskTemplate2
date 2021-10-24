package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    public UserDaoHibernateImpl() {

    }


    @Override
    public void createUsersTable() {
        Session session = null;
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "CREATE TABLE IF NOT EXISTS user ("
                    + "id INT PRIMARY KEY AUTO_INCREMENT, "
                    + "name VARCHAR(30), "
                    + "lastName VARCHAR(30), "
                    + "age INT);";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
    }

    @Override
    public void dropUsersTable() {
        Session session = null;
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "DROP TABLE IF EXISTS user;";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        Session session = null;
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            session.save(new User(name, lastName, age));
            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
    }

    @Override
    public void removeUserById(long id) {
        Session session = null;
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            User user = session.load(User.class, id);
            session.delete(user);
            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
    }

    @Override
    public List<User> getAllUsers() {
        Session session = null;
        List<User> result = new ArrayList<>();
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            result = session.createCriteria(User.class).list();
            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
        return result;
    }

    @Override
    public void cleanUsersTable() {
        Session session = null;
        try {
            session = Util.sessionFactory().openSession();
            Transaction transaction = session.beginTransaction();

            String sql = "TRUNCATE TABLE user;";
            Query query = session.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();

            transaction.commit();
        }catch (Exception e) {
            System.out.println("Исключение" + e);
        }finally {
            if (session != null) {
                try {
                    session.close();
                }catch (Exception ignored) {

                }
            }
        }
    }
}
