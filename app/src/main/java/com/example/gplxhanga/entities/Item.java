package com.example.gplxhanga.entities;

public class Item {
    private final int icon;
    private final String title;
    private final String description;
    private final String paralysis_points;
    private final String quantity_question;
    public Item(int icon, String title, String description, String paralysisPoints, String quantityQuestion) {
        this.icon = icon;
        this.title = title;
        this.description = description;
        paralysis_points = paralysisPoints;
        quantity_question = quantityQuestion;
    }
    public String getParalysis_points() {
        return paralysis_points;
    }
    public String getQuantity_question() {
        return quantity_question;
    }
    public int getIcon() { return icon; }
    public String getTitle() { return title; }
    public String getDescription() { return description; }
}

