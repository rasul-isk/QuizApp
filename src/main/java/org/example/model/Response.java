package org.example.model;

public class Response { //swap it to record if no modifications required
    private int id;
    private String text;
    private boolean correct;

    public Response(int id, String text, boolean correct) {
        this.id = id;
        this.text = text;
        this.correct = correct;
    }

    public int getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public boolean isCorrect() {
        return correct;
    }

}
