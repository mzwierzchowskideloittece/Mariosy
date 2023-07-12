package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;

import java.util.Set;

public class MariosDTO {

    private Marios.TypeEnum type;
    private String comment;
    private User from;
    private Set<User> to;

    public Marios.TypeEnum getType() {
        return type;
    }

    public void setType(Marios.TypeEnum type) {
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

    public void setFrom(User from) {
        this.from = from;
    }

    public Set<User> getTo() {
        return to;
    }

    public void setTo(Set<User> to) {
        this.to = to;
    }
}
