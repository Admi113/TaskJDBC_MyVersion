package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class UserDaoHibernateImpl implements UserDao {
    Session currentSession;
    Transaction transaction;

    public UserDaoHibernateImpl() {
    }


    @Override
    public void createUsersTable() {
        openTransactionAndSession();
        String sql = "CREATE TABLE `testdb`.`users` (\n" +
                "`id` INT NOT NULL AUTO_INCREMENT," +
                "`name` VARCHAR(45) NOT NULL DEFAULT 'Иван'," +
                "`lastName` VARCHAR(45) NOT NULL DEFAULT 'Иванов' ," +
                "`age` INT(3) NOT NULL DEFAULT 33 ," +
                "PRIMARY KEY (`id`));";

        Query query = currentSession.createSQLQuery(sql).addEntity(User.class);
        query.executeUpdate();

        closeTransactionAndSession();

    }

    @Override
    public void dropUsersTable() {
        openTransactionAndSession();
        String sql = "DROP TABLE `testdb`.`users`;";
        try {
            Query query = currentSession.createSQLQuery(sql).addEntity(User.class);
            query.executeUpdate();
        }catch (Exception ignore){
        }
        closeTransactionAndSession();
    }

    @Override
    public void saveUser(String name, String lastName, byte age) {
        openTransactionAndSession();
        User user = new User(name, lastName, age);
        currentSession.save(user);
        closeTransactionAndSession();

    }

    @Override
    public void removeUserById(long id) {
        openTransactionAndSession();
        String sql = "SELECT * FROM users WHERE ID = :id";
        Query query = currentSession.createSQLQuery(sql)
                .addEntity(User.class);
        closeTransactionAndSession();
    }

    @Override
    public List<User> getAllUsers() {
        openTransactionAndSession();
        String sql = "SELECT * FROM users";
        Query query = currentSession.createSQLQuery(sql)
                .addEntity(User.class);
        List<User> userList = query.list();
        closeTransactionAndSession();
        return userList;
    }

    @Override
    public void cleanUsersTable() {
        openTransactionAndSession();
        String sql = "DELETE  FROM USERS";
        Query query = currentSession.createSQLQuery(sql)
                .addEntity(User.class);
        query.executeUpdate();
        closeTransactionAndSession();
    }

    public void openTransactionAndSession() {
        currentSession = new Util().getSessionFactory()
                .openSession();
        transaction = currentSession.beginTransaction();
    }


    public void closeTransactionAndSession() {
        transaction.commit();
    }
}
