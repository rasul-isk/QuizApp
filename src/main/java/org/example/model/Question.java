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

    public void addResponse(Response response) {
        this.responses.add(response);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", topic='" + topic + '\'' +
                ", difficultyRank=" + difficultyRank +
                ", content='" + content + '\'' +
                ", responses=" + responses +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Question otherQuestion)) {
            return false;
        }
        return topic.equals(otherQuestion.getTopic()) &&
                difficultyRank == otherQuestion.getDifficultyRank() &&
                content.equals(otherQuestion.getContent()) &&
                responses.equals(otherQuestion.getResponses());
    }

}
