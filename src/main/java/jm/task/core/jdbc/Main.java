package jm.task.core.jdbc;

import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.service.UserService;
import jm.task.core.jdbc.service.UserServiceImpl;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) throws SQLException {

        UserService userService = new UserServiceImpl();
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
