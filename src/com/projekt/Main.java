package com.projekt;

import java.sql.SQLException;

public class Main {

    public static void main(String[] args) {
        DatabaseConnector dbConnector = DatabaseConnector.getInstance();
        try {
            dbConnector.connect();
        } catch (SQLException e) {
            System.out.println("Error while connecting to the database: " + e.getMessage());
        }
    }
}
