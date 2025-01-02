package com.example.gplxhanga.entities;

public class ItemTopic {
    private String numberTopic;
    private String numberQuestion;
    private String time;
    private int ispass;

    public ItemTopic(String numberTopic, String numberQuestion, String time, int ispass) {
        this.numberTopic = numberTopic;
        this.numberQuestion = numberQuestion;
        this.time = time;
        this.ispass = ispass;
    }

    public String getNumberTopic() {
        return numberTopic;
    }

    public void setNumberTopic(String numberTopic) {
        this.numberTopic = numberTopic;
    }

    public String getNumberQuestion() {
        return numberQuestion;
    }

    public void setNumberQuestion(String numberQuestion) {
        this.numberQuestion = numberQuestion;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int isIspass() {
        return ispass;
    }

    public void setIspass(int ispass) {
        this.ispass = ispass;
    }
}
