package org.example.model;

import java.util.List;

public class Question {
    private int id;
    private String topic;
    private int difficultyRank;
    private String content;
    private List<Response> responses;

    public Question(int id, String topic, int difficultyRank, String content, List<Response> responses) {
        this.id = id;
        this.topic = topic;
        this.difficultyRank = difficultyRank;
        this.content = content;
        this.responses = responses;
    }

    public int getId() {
        return id;
    }


    public String getTopic() {
        return topic;
    }


    public int getDifficultyRank() {
        return difficultyRank;
    }


    public String getContent() {
        return content;
    }


    public List<Response> getResponses() {
        return responses;
    }

}
