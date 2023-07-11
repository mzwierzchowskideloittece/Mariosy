package com.deloitte.ads;

import java.util.Set;

public class Marios {

    public enum TypeEnum {
        HAPPY, SAD, MAD, AMUSED, FUNNY, FOOTBALL
    }

    private TypeEnum type;
    private String comment;
    private User from;
    private Set<User> to;


    public Marios(TypeEnum type, String comment, User from, Set<User> to) {
        if(comment == null) comment = "";
        this.type = type;
        this.comment = comment;
        this.from = from;
        this.to = to;
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

    public User getFrom() {
        return from;
    }

    public Set<User> getTo() {
        return to;
    }

}
