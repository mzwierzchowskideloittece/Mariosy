package com.deloitte.ads.repository;

import com.deloitte.ads.repository.Marios;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

@Entity(name = "user_data")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "email")
    private String email;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "last_name")
    private String lastName;

    @ManyToMany
    @JoinTable(
            name = "user_data_received_marios",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "marios_id"))
    private Set<Marios> receivedMariosy;

    @OneToMany(mappedBy="sender")
    private Set<Marios> sentMariosy;

    public User() {}

    public User(String email, String firstName, String lastName) {

        if (email == null || firstName == null || lastName == null || !Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(email).matches())
            throw new IllegalArgumentException("Wrong input");

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.sentMariosy = new HashSet<>();
        this.receivedMariosy = new HashSet<>();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.firstName = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<Marios> getReceivedMariosy() { return receivedMariosy; }

    public Set<Marios> getSentMariosy() { return sentMariosy; }

    public void addSentMarios(Marios sentMarios) {
        this.sentMariosy.add(sentMarios);
    }

    public void addReceivedMarios(Marios receivedMarios) {
        this.receivedMariosy.add(receivedMarios);
    }

}
