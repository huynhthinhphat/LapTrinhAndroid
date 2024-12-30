package com.example.gplxhanga.entities;

import java.io.Serializable;

public class ItemQuestion implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String questionText; // Nội dung câu hỏi

    private String option1; // Lựa chọn 1
    private String option2; // Lựa chọn 2
    private String option3; // Lựa chọn 3
    private String option4; // Lựa chọn 4

    private String correctAnswer; // Lựa chọn đúng (1, 2, 3, hoặc 4)

    private String questionType; // Loại câu hỏi
    private String explanation; // Giải thích

    private String imageUrl;

    public ItemQuestion( String questionText, String option1, String option2, String option3, String option4, String correctAnswer, String questionType, String explanation, String imageUrl) {

        this.questionText = questionText;
        this.option1 = option1;
        this.option2 = option2;
        this.option3 = option3;
        this.option4 = option4;
        this.correctAnswer = correctAnswer;
        this.questionType = questionType;
        this.explanation = explanation;
        this.imageUrl = imageUrl;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getOption1() {
        return option1;
    }

    public void setOption1(String option1) {
        this.option1 = option1;
    }

    public String getOption2() {
        return option2;
    }

    public void setOption2(String option2) {
        this.option2 = option2;
    }

    public String getOption3() {
        return option3;
    }

    public void setOption3(String option3) {
        this.option3 = option3;
    }

    public String getOption4() {
        return option4;
    }

    public void setOption4(String option4) {
        this.option4 = option4;
    }

    public String getCorrectAnswer() {
        return correctAnswer;
    }

    public void setCorrectAnswer(String correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    public String getQuestionType() {
        return questionType;
    }

    public void setQuestionType(String questionType) {
        this.questionType = questionType;
    }

    public String getExplanation() {
        return explanation;
    }

    public void setExplanation(String explanation) {
        this.explanation = explanation;
    }
}
