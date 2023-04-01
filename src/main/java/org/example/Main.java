package org.example;

import org.example.model.DaoQuestion;
import org.example.model.Question;
import org.example.model.Response;

import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //config file
        String url = "jdbc:mysql://localhost:3306/quiz_app";
        String username = "root";
        String password = "";

        try {
            Connection connection = DriverManager.getConnection(url, username, password);
            DaoQuestion daoQuestion = new DaoQuestion(connection);
            Response response1 = new Response(1, "Mars", false);
            Response response2 = new Response(2, "Jupiter", true);
            Response response3 = new Response(3, "Earth", false);
            Response response4 = new Response(4, "Pluto", false);
            List<Response> responses = new ArrayList<>();
            responses.add(response1);
            responses.add(response2);
            responses.add(response3);
            responses.add(response4);
            Question q1 = new Question(1, "physics", 5, "Which planet has the strongest gravitational force ?", responses);
            daoQuestion.saveQuestion(q1);

            daoQuestion.close();

        } catch (Exception e) {
            System.out.println(e);

        }
    }
}