CREATE DATABASE quiz_app;

USE quiz_app;

CREATE TABLE question (
    id INT PRIMARY KEY,
    topic VARCHAR(255),
    difficultyRank INT,
    content VARCHAR(255)
);

CREATE TABLE response (
    id INT PRIMARY KEY,
    question_id INT,
    text VARCHAR(255),
    correct BOOLEAN,
    FOREIGN KEY (question_id) REFERENCES question(id)
);
