package com.deloitte.ads.repository;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    @Getter
    private Long id;

    @Column(name = "email", unique = true)
    @Getter @Setter
    private String email;

    @Column(name = "first_name")
    @Getter @Setter
    private String firstName;

    @Column(name = "last_name")
    @Getter @Setter
    private String lastName;

    @ManyToMany(mappedBy = "receivers")
    @Getter
    private Set<Marios> receivedMariosSet;

    @OneToMany(mappedBy="sender")
    @Getter
    private Set<Marios> sentMariosSet;

    public User() {}

    private User(String email, String firstName, String lastName) {
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sentMariosSet = new HashSet<>();
        this.receivedMariosSet = new HashSet<>();
    }


    public static User createUser(String email, String firstName, String lastName) {

        if (email == null) throw new IllegalArgumentException("No email");
        if(firstName == null) throw new IllegalArgumentException("No first name");
        if(lastName == null) throw new IllegalArgumentException("No last name");
        if(!Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$")
                .matcher(email).matches()) throw new IllegalArgumentException("Wrong email");

        return new User(email, firstName, lastName);
    }
}
