package com.example.gplxhanga.entities;

public class HistoryLearn {
    private int Id_Question;
    private String Answer_Select;
    private String Typequestion;

    public HistoryLearn(int id_Question, String answer_Select, String typequestion) {
        Id_Question = id_Question;
        Answer_Select = answer_Select;
        Typequestion = typequestion;
    }

    public int getId_Question() {
        return Id_Question;
    }

    public void setId_Question(int id_Question) {
        Id_Question = id_Question;
    }

    public String getAnswer_Select() {
        return Answer_Select;
    }

    public void setAnswer_Select(String answer_Select) {
        Answer_Select = answer_Select;
    }

    public String getTypequestion() {
        return Typequestion;
    }

    public void setTypequestion(String typequestion) {
        Typequestion = typequestion;
    }
}
