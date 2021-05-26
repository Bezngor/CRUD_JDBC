package com.bezngor.crud_jdbc.repository;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBWorker {
    private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
    private static final String URL = "jdbc:mysql://localhost:3306/crud_jdbc";

    private static final String USER = "root";
    private static final String PASSWORD = "V2gpYplBQhgz";

    private Connection connection;

    public DBWorker() {
        try {
            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
