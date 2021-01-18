package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {


//        Session currentSession = new Util().getSessionFactory().getCurrentSession();
//        Transaction transaction = currentSession.beginTransaction();
//        String sql = "SELECT * FROM USERS";
//        Query query = currentSession.createSQLQuery(sql).addEntity(User.class);
//        List<User> userList = query.list();
//        System.out.println(userList);


        UserService userService = new UserServiceImpl();
        userService.dropUsersTable();

        userService.createUsersTable();

        userService.saveUser("Иван", "Иванов", (byte) 10);
        userService.saveUser("Петр", "Иванов", (byte) 20);
        userService.saveUser("Сергей", "Иванов", (byte) 30);
        userService.saveUser("Дмитрий", "Иванов", (byte) 40);

        List<User> allUsers = userService.getAllUsers();
        for(User user:allUsers){
            System.out.println(user);
        }
        userService.cleanUsersTable();

        userService.dropUsersTable();

    }
}
