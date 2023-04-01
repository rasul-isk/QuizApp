package org.example.model;

public record Response(int id, String text, boolean correct) {

    @Override
    public String toString() {
        return "Response{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", correct=" + correct +
                '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof Response response)) {
            return false;
        }
        return id == response.id() && text.equals(response.text()) && correct == response.correct;
    }
}
