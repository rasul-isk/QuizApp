package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Main {
    private static final String url = System.getenv("DB_URL");
    private static final String username = System.getenv("DB_USER");
    private static final String password = System.getenv("DB_PASSWORD");
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            Connection connection = DriverManager.getConnection(url, username, password);

            if (connection != null) {
                System.out.println("Connection with MySQL DB established.");
            }
        } catch (ClassNotFoundException e) {
            System.out.println("MySQL JDBC driver not found.");
            e.printStackTrace();
        } catch (SQLException e) {
            System.out.println("Connection failed.");
            e.printStackTrace();
        }
    }
}