package org.example.model;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQuestion {
    private final Connection connection;

    public DaoQuestion(Connection connection) {
        this.connection = connection;
    }

    public void saveQuestion(Question question) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "INSERT INTO question (id, topic, difficultyRank, content) VALUES (?, ?, ?, ?)"
        );
        statement.setInt(1, question.id());
        statement.setString(2, question.topic());
        statement.setInt(3, question.difficultyRank());
        statement.setString(4, question.content());
        statement.executeUpdate();

        for (Response response : question.responses()) {
            PreparedStatement responseStatement = connection.prepareStatement(
                    "INSERT INTO response (id, question_id, text, correct) VALUES (?, ?, ?, ?)"
            );
            responseStatement.setInt(1, response.id());
            responseStatement.setInt(2, question.id());
            responseStatement.setString(3, response.text());
            responseStatement.setBoolean(4, response.correct());
            responseStatement.executeUpdate();
        }
    }

    public void updateQuestion(Question question) throws SQLException {
        PreparedStatement statement = connection.prepareStatement(
                "UPDATE question SET topic=?, difficultyRank=?, content=? WHERE id=?"
        );
        statement.setString(1, question.topic());
        statement.setInt(2, question.difficultyRank());
        statement.setString(3, question.content());
        statement.setInt(4, question.id());
        statement.executeUpdate();

        PreparedStatement deleteResponseStatement = connection.prepareStatement(
                "DELETE FROM response WHERE question_id=?"
        );
        deleteResponseStatement.setInt(1, question.id());
        deleteResponseStatement.executeUpdate();

        for (Response response : question.responses()) {
            PreparedStatement responseStatement = connection.prepareStatement(
                    "INSERT INTO response (id, question_id, text, correct) VALUES (?, ?, ?, ?)"
            );
            responseStatement.setInt(1, response.id());
            responseStatement.setInt(2, question.id());
            responseStatement.setString(3, response.text());
            responseStatement.setBoolean(4, response.correct());
            responseStatement.executeUpdate();
        }
    }

    public void deleteQuestion(Question question) throws SQLException {
        PreparedStatement deleteResponseStatement = connection.prepareStatement(
                "DELETE FROM response WHERE question_id=?"
        );
        deleteResponseStatement.setInt(1, question.id());
        deleteResponseStatement.executeUpdate();

        PreparedStatement statement = connection.prepareStatement(
                "DELETE FROM question WHERE id=?"
        );
        statement.setInt(1, question.id());
        statement.executeUpdate();
    }

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

    public void deleteAllRows() throws SQLException {
        String sqlDeleteResponses = "DELETE FROM response";
        String sqlDeleteQuestions = "DELETE FROM question";

        PreparedStatement dropResponses = connection.prepareStatement(sqlDeleteResponses);
        PreparedStatement dropQuestions = connection.prepareStatement(sqlDeleteQuestions);
        dropResponses.executeUpdate();
        dropQuestions.executeUpdate();
    }

    public void close() throws SQLException {
        connection.close();
    }
}