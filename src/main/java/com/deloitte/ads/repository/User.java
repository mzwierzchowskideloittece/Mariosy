package com.deloitte.ads.repository;

import lombok.Getter;

import javax.persistence.*;
import java.util.*;

@Entity(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    private Long id;

    @Column(name = "user_externalId", unique = true)
    @Getter
    private UUID externalId;

    @Column(name = "user_name", unique = true)
    @Getter
    private String userName;

    @ManyToMany(mappedBy = "receivers")
    @Getter
    private List<Marios> receivedMariosList;

    @OneToMany(mappedBy="sender")
    @Getter
    private List<Marios> sentMariosList;

    public User() {}

    private User(String userName) {
        this.externalId = UUID.randomUUID();
        this.userName = userName;
        this.sentMariosList = new ArrayList<>();
        this.receivedMariosList = new ArrayList<>();
    }

    public static User createUser(String userName) {

        if(userName == null || userName.equals("")) throw new IllegalArgumentException("No user name");

        return new User(userName);
    }
}
