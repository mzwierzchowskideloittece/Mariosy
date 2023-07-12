package com.deloitte.ads.repository;

import com.deloitte.ads.repository.Marios;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class User {

    private int id;
    private String email;
    private String firstName;
    private String lastName;

    public User(int id, String email, String firstName, String lastName) {

        if (email == null || firstName == null || lastName == null || !Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(email).matches())
            throw new IllegalArgumentException("Wrong input");

        this.id = id;
        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
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

}
