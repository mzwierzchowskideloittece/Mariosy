package com.deloitte.ads;

public class Marios {

    public enum TypeEnum {
        HAPPY, SAD, MAD, AMUSED, FUNNY, FOOTBALL
    }

    private int id;
    private TypeEnum type;
    private String comment;
    private int from;
    private int to;


    public Marios(int id, TypeEnum type, String comment, int from, int to) {
        this.id = id;
        this.type = type;
        this.comment = comment;
        this.from = from;
        this.to = to;
    }

    public int getId() {
        return id;
    }

    public TypeEnum getType(){
        return type;
    }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public int getFrom() {
        return from;
    }

    public void setFrom(int from) {
        this.from = from;
    }

    public int getTo() {
        return to;
    }

    public void setTo(int to) {
        this.to = to;
    }
}
