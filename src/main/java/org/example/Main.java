package org.example;

import org.example.model.DaoQuestion;
import org.example.model.Question;
import org.example.model.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        try {
            Class.forName("com.mysql.cj.jdbc.Driver");

            //config file
            String url = "jdbc:mysql://localhost:3306/quiz_app";
            String username = "root";
            String password = "";

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