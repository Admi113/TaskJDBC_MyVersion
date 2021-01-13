package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private final String USERNAME="root";
    private String PASSWORD="root";

    private Connection connection;

    public Util() {
        try {
            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);
            connection= DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
