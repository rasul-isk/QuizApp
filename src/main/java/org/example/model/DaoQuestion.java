package org.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQuestion {
    private Connection connection;

    public DaoQuestion(Connection connection) {
        this.connection = connection;
    }

    // Saves question to MySQL DB
    public void saveQuestion(Question question) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO question (id, topic, difficultyRank, content) VALUES (?, ?, ?, ?)"
        );
        statement.setInt(1, question.getId());
        statement.setString(2, question.getTopic());
        statement.setInt(3, question.getDifficultyRank());
        statement.setString(4, question.getContent());
        statement.executeUpdate();

        for (Response response : question.getResponses()) {
            PreparedStatement responseStatement = connection.prepareStatement(
                    "INSERT INTO response (id, question_id, text, correct) VALUES (?, ?, ?, ?)"
            );
            responseStatement.setInt(1, response.getId());
            responseStatement.setInt(2, question.getId());
            responseStatement.setString(3, response.getText());
            responseStatement.setBoolean(4, response.isCorrect());
            responseStatement.executeUpdate();
        }
    }

    // Updates question by replacing it
    public void updateQuestion(Question question) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE question SET topic=?, difficultyRank=?, content=? WHERE id=?"
        );
        statement.setString(1, question.getTopic());
        statement.setInt(2, question.getDifficultyRank());
        statement.setString(3, question.getContent());
        statement.setInt(4, question.getId());
        statement.executeUpdate();

        PreparedStatement deleteResponseStatement = connection.prepareStatement(
                "DELETE FROM response WHERE question_id=?"
        );
        deleteResponseStatement.setInt(1, question.getId());
        deleteResponseStatement.executeUpdate();

        for (Response response : question.getResponses()) {
            PreparedStatement responseStatement = connection.prepareStatement(
                    "INSERT INTO response (id, question_id, text, correct) VALUES (?, ?, ?, ?)"
            );
            responseStatement.setInt(1, response.getId());
            responseStatement.setInt(2, question.getId());
            responseStatement.setString(3, response.getText());
            responseStatement.setBoolean(4, response.isCorrect());
            responseStatement.executeUpdate();
        }
    }

    // Deletes question
    public void deleteQuestion(Question question) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM question WHERE id=?"
        );
        statement.setInt(1, question.getId());
        statement.executeUpdate();
    }

    // Searches question by topic
    public List<Question> searchQuestionByTopic(String topic) throws SQLException {
        List<Question> questions = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM question WHERE topic=?"
        );
        statement.setString(1, topic);
        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            int difficultyRank = resultSet.getInt("difficultyRank");
            String content = resultSet.getString("content");
            List<Response> responses = getResponsesForQuestion(id);
            Question question = new Question(id, topic, difficultyRank, content, responses);
            questions.add(question);
        }

        return questions;
    }

    // Gets responses for chosen question
    private List<Response> getResponsesForQuestion(int questionId) throws SQLException {
        List<Response> responses = new ArrayList<>();
        PreparedStatement statement = connection.prepareStatement(
                "SELECT * FROM response WHERE question_id=?"
        );
        statement.setInt(1, questionId);

        ResultSet resultSet = statement.executeQuery();

        while (resultSet.next()) {
            int id = resultSet.getInt("id");
            String text = resultSet.getString("text");
            boolean correct = resultSet.getBoolean("correct");
            Response response = new Response(id, text, correct);
            responses.add(response);
        }

        return responses;
    }

    // Closes database connection
    public void close() throws SQLException {
        connection.close();
    }
}