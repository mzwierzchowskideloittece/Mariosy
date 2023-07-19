package com.deloitte.ads.repository;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

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
    @JsonIgnore
    @Column(name = "marios_id")
    @Getter
    private Long id;

    @Column(name = "type")
    private String type;

    @Column(name = "comment")
    @Getter @Setter
    private String comment;

    @Column(name = "creation_date")
    @Getter
    private Instant creationDate;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "user_data_received_marios",
            joinColumns = @JoinColumn(name = "marios_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Getter @Setter
    private Set<User> receivers;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    @Getter @Setter
    private User sender;

    public Marios() {}

    private Marios(String type, String comment) {

        this.type = type;
        this.comment = comment;
        this.creationDate = Instant.now();

    }

    public TypeEnum getType() { return TypeEnum.valueOf(type.toUpperCase()); }

    public void setType(TypeEnum type) {
        this.type = type.name();
    }


    public static Marios createMarios(TypeEnum type, String comment) {

        if(comment == null) comment = "";

        return new Marios(type.name(), comment);

    }
}
