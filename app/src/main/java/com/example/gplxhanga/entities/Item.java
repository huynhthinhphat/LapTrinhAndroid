package com.example.gplxhanga.entities;

public class Item {
    private final int icon;
    private final String title;
    private final String description;
    private final String paralysis_points;
    private final int quantity_question;
    private final  int question_excute;
    public Item(int icon, String title, String description, String paralysisPoints, int question_excute, int quantityQuestion) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        this.paralysis_points = paralysisPoints;
        this.quantity_question = quantityQuestion;
        this.question_excute = question_excute;
    }
    public String getParalysis_points() {
        return paralysis_points;
    }
    public int getQuantity_question() {
        return quantity_question;
    }
    public int getIcon() { return icon; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }

    public int getQuestion_excute() {
        return question_excute;
    }
}

