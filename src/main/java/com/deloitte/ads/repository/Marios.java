package com.deloitte.ads.repository;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;

@Entity(name = "marios")
public class Marios {

    public enum TypeEnum {
        HAPPY, SAD, MAD, AMUSED, FUNNY, FOOTBALL
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "marios_id")
    private Long id;

    @Column(name = "type")
    private TypeEnum type;

    @Column(name = "comment")
    private String comment;

    @Column(name = "creation_date")
    private Instant creationDate;

    @ManyToMany(mappedBy = "receivedMariosy")
    private Set<User> receivers;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User sender;

    public Marios() {}

    public Marios(TypeEnum type, String comment) {

        this.type = type;
        this.comment = comment;
        this.creationDate = Instant.now();
    }

    public Long getId() { return id; }

    public TypeEnum getType() { return type; }

    public void setType(TypeEnum type) {
        this.type = type;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Instant getCreationDate() { return creationDate; }

    public Set<User> getReceivers() {
        return receivers;
    }

    public void setReceivers(Set<User> receivers) {
        this.receivers = receivers;
    }

    public User getSender() {
        return sender;
    }

    public void setSender(User sender) {
        this.sender = sender;
    }
}
