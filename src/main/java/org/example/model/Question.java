package org.example.model;

import java.util.List;

public record Question(int id, String topic, int difficultyRank, String content, List<Response> responses) {

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
        return topic.equals(otherQuestion.topic()) &&
                difficultyRank == otherQuestion.difficultyRank() &&
                content.equals(otherQuestion.content()) &&
                responses.equals(otherQuestion.responses());
    }

}
