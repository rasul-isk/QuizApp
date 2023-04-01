package org.example.model;

import org.junit.*;

import static org.junit.Assert.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DaoQuestionTest {

    private DaoQuestion daoQuestion;
    //config file
    private final String url = "jdbc:mysql://localhost:3306/quiz_app";
    private final String username = "root";
    private final String password = "";

    @Before
    public void setUp() throws SQLException {
        Connection connection = DriverManager.getConnection(url, username, password);
        daoQuestion = new DaoQuestion(connection);

        daoQuestion.deleteAllRows();
    }

    @Test
    public void testSaveQuestion() throws SQLException {
        Question question = new Question(1, "Physics", 4, "Which planet has the strongest gravitational force ?", new ArrayList<>());
        question.addResponse(new Response(1, "Mars", false));
        question.addResponse(new Response(2, "Jupiter", true));
        question.addResponse(new Response(3, "Earth", false));
        question.addResponse(new Response(4, "Pluto", false));

        daoQuestion.saveQuestion(question);

        Question foundQuestion = daoQuestion.searchQuestionByTopic(question.getTopic()).stream()
                .findFirst()
                .orElse(null);

        assertEquals(question, foundQuestion);
    }

    @Test
    public void testUpdateQuestion() throws SQLException {
        Question question = new Question(1, "Physics", 4, "Which planet has the strongest gravitational force ?", new ArrayList<>());
        question.addResponse(new Response(1, "Mars", false));
        question.addResponse(new Response(2, "Jupiter", true));
        question.addResponse(new Response(3, "Earth", false));
        question.addResponse(new Response(4, "Pluto", false));

        daoQuestion.saveQuestion(question);

        Question newQuestion = new Question(1, "Chemistry", 7, "Are two atoms of the same element identical?", new ArrayList<>());
        newQuestion.addResponse(new Response(3, "Yes, they are identical", false));
        newQuestion.addResponse(new Response(4, "No, they are not identical", true));
        newQuestion.addResponse(new Response(5, "None of the answers above", false));

        daoQuestion.updateQuestion(newQuestion);

        Question retrievedQuestion = daoQuestion.searchQuestionByTopic(newQuestion.getTopic()).stream()
                .findFirst()
                .orElse(null);

        assertEquals(newQuestion,retrievedQuestion);
    }

    @Test
    public void testDeleteQuestion() throws SQLException {
        Question question = new Question(2, "Physics", 4, "Which planet has the strongest gravitational force ?", new ArrayList<>());
        question.addResponse(new Response(1, "Mars", false));
        question.addResponse(new Response(2, "Jupiter", true));
        question.addResponse(new Response(3, "Earth", false));
        question.addResponse(new Response(4, "Pluto", false));

        daoQuestion.saveQuestion(question);

        daoQuestion.deleteQuestion(question);

        Question retrievedQuestion = daoQuestion.searchQuestionByTopic(question.getTopic()).stream()
                .findFirst()
                .orElse(null);

        assertNull(retrievedQuestion);
    }

    @Test
    public void testSearchQuestionByTopic() throws SQLException {
        // Create two questions with different topics
        Question question = new Question(5, "Space", 4, "Are there different types of black holes?", new ArrayList<>());
        question.addResponse(new Response(11, "No, all back holes are same.", false));
        question.addResponse(new Response(22, "Yes, all black holes are different.", true));
        question.addResponse(new Response(33, "Yes, but all black holes categorised by their types.", false));

        Question question2 = new Question(6, "Biology", 8, "Are there nuclear reactions going on in our bodies?", new ArrayList<>());
        question2.addResponse(new Response(44, "Yes, there are nuclear reactions.", true));
        question2.addResponse(new Response(55, "No, there are no reactions at all.", false));
        question2.addResponse(new Response(66, "No, there are no nuclear reactions.", false));

        daoQuestion.saveQuestion(question);
        daoQuestion.saveQuestion(question2);

        List<Question> questions = daoQuestion.searchQuestionByTopic("Biology");

        assertEquals(1, questions.size());
        assertEquals(question2,questions.get(0));
    }

    @After
    public void clean() throws SQLException {
        daoQuestion.deleteAllRows();
    }
}
