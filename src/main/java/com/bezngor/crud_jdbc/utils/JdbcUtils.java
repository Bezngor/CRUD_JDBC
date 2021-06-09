package com.bezngor.crud_jdbc.utils;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class JdbcUtils {
    public static final String PATH_TO_PROPERTIES = "src/main/resources/application.properties";
    private static String driver;
    private static String url;
    private static String user;
    private static String password;

    private static Connection connection;

    static {
        try (FileInputStream fis = new FileInputStream(PATH_TO_PROPERTIES))
        {
            Properties prop = new Properties();
            prop.load(fis);

            driver = prop.getProperty("db.driver");
            url = prop.getProperty("db.url");
            user = prop.getProperty("db.user");
            password = prop.getProperty("db.password");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public JdbcUtils() {
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url, user, password);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() {
        return connection;
    }
}
