package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;

import java.util.Set;

public class UserDTO {
    String email;
    String firstName;
    String lastName;
    Set<Marios> sentMariosy;
    Set<Marios> receivedMariosy;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    public Set<Marios> getSentMariosy() {
        return sentMariosy;
    }

    public void setSentMariosy(Set<Marios> sentMariosy) {
        this.sentMariosy = sentMariosy;
    }

    public Set<Marios> getReceivedMariosy() {
        return receivedMariosy;
    }

    public void setReceivedMariosy(Set<Marios> receivedMariosy) {
        this.receivedMariosy = receivedMariosy;
    }
}
