package jm.task.core.jdbc.util;


import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Util {
    //    private final String HOST = "jdbc:mysql://localhost:3306/new_schema?useSSL=false";
    private final String HOST = "jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow";
    private final String USERNAME = "root";
    private String PASSWORD = "root";
    private static SessionFactory sessionFactory;
    private static Connection connection;

    public Util() {
        UserDAOJDBCConnection();
    }

    public void UserDAOJDBCConnection() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();
//            Driver driver = new FabricMySQLDriver();
//            DriverManager.registerDriver(driver);

            connection = DriverManager.getConnection(HOST, USERNAME, PASSWORD);
        } catch (SQLException | InstantiationException | ClassNotFoundException | IllegalAccessException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void UserDAOHibernateSessionImpl() {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.cj.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/testdb?useLegacyDatetimeCode=false&serverTimezone=Europe/Moscow");
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "none");
        configuration.setProperty("hibernate.current_session_context_class", "thread");
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
                .applySettings(configuration.getProperties());
        sessionFactory = configuration.buildSessionFactory(builder.build());
    }


    public static SessionFactory getSessionFactory() {
        UserDAOHibernateSessionImpl();
        System.out.println("Сессия получена");
        return sessionFactory;
    }


    public static Connection getConnection() {
        return connection;
    }

}
