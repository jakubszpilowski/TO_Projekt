package com.projekt;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnector {
    private static DatabaseConnector instance;
    String jdbcUrl = "jdbc:mysql://localhost:3306/trains_management_db";
    String username = "root";
    String password = "password";

    private DatabaseConnector() {}

    public static DatabaseConnector getInstance() {
        if (instance == null) {
            instance = new DatabaseConnector();
        }
        return instance;
    }

    public void connect() throws SQLException {
        Connection connection = DriverManager.getConnection(jdbcUrl, username, password);
        System.out.println("Connected to the database");
    }
}
