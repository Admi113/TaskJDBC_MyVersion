package jm.task.core.jdbc.util;

import com.mysql.fabric.jdbc.FabricMySQLDriver;
import jm.task.core.jdbc.model.User;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;

import javax.imageio.spi.ServiceRegistry;
import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private final String HOST = "jdbc:mysql://localhost:3306/testdb?useSSL=false";
    private final String USERNAME="root";
    private String PASSWORD="root";
    private static SessionFactory sessionFactory;
    private Connection connection;

    public Util() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Configuration config = new Configuration();
            config.addAnnotatedClass(User.class);
            Properties prop = new Properties();
            prop.put(Environment.DRIVER,"com.mysql.cj.jdbc.Driver");
            prop.put(Environment.URL,HOST);
            prop.put(Environment.USER,USERNAME);
            prop.put(Environment.PASS,PASSWORD);
            prop.put(Environment.DIALECT,"org.hibernate.dialect.MySQL5Dialect");
            prop.put(Environment.SHOW_SQL,"true");
            prop.put(Environment.HBM2DDL_AUTO,"create-drop");
            prop.put(Environment.CURRENT_SESSION_CONTEXT_CLASS,"thread");
            config.setProperties(prop);
            StandardServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
                    .applySettings(config.getProperties()).build();
            sessionFactory = config.buildSessionFactory(serviceRegistry);


            Driver driver = new FabricMySQLDriver();
            DriverManager.registerDriver(driver);

            connection= DriverManager.getConnection(HOST,USERNAME,PASSWORD);
        } catch (SQLException | ClassNotFoundException throwables) {
            throwables.printStackTrace();
        }
    }

    public  SessionFactory getSessionFactory() {

        return sessionFactory;
    }

    public Connection getConnection() {
        return connection;
    }
    // реализуйте настройку соеденения с БД
}
