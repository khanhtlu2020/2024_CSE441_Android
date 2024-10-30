package com.example.drivingtestapp.models;

import java.io.Serializable;

public class Question implements Serializable {
    private int id;
    private String question;
    private String ideaA;
    private String ideaB;
    private String ideaC;
    private String ideaD;
    private int answer;  // Đáp án đúng (chỉ số của đáp án đúng, ví dụ 1 cho A, 2 cho B, ...)
    private int userAnswer = -1;  // Câu trả lời của người dùng, mặc định -1 (chưa trả lời)

    public Question(int id, String question, String ideaA, String ideaB, String ideaC, String ideaD, int answer) {
        this.id = id;
        this.question = question;
        this.ideaA = ideaA;
        this.ideaB = ideaB;
        this.ideaC = ideaC;
        this.ideaD = ideaD;
        this.answer = answer;
    }

    // Getter và setter cho userAnswer
    public int getUserAnswer() {
        return userAnswer;
    }

    public void setUserAnswer(int userAnswer) {
        this.userAnswer = userAnswer;
    }

    // Phương thức kiểm tra xem câu trả lời của người dùng có đúng không
    public boolean isCorrect() {
        return userAnswer == answer;
    }

    // Các getter và setter khác
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getIdeaA() {
        return ideaA;
    }

    public void setIdeaA(String ideaA) {
        this.ideaA = ideaA;
    }

    public String getIdeaB() {
        return ideaB;
    }

    public void setIdeaB(String ideaB) {
        this.ideaB = ideaB;
    }

    public String getIdeaC() {
        return ideaC;
    }

    public void setIdeaC(String ideaC) {
        this.ideaC = ideaC;
    }

    public String getIdeaD() {
        return ideaD;
    }

    public void setIdeaD(String ideaD) {
        this.ideaD = ideaD;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", question='" + question + '\'' +
                ", ideaA='" + ideaA + '\'' +
                ", ideaB='" + ideaB + '\'' +
                ", ideaC='" + ideaC + '\'' +
                ", ideaD='" + ideaD + '\'' +
                ", answer=" + answer +
                ", userAnswer=" + userAnswer +
                '}';
    }
}
