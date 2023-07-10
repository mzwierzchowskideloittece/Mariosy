package com.deloitte.ads;

import java.util.HashSet;
import java.util.Set;

public class User {

    private int id;
    private String firstName;
    private String lastName;
    private Set<Integer> receivedMariosy;
    private Set<Integer> sentMariosy;

    public User(int id, String firstName, String lastName, Set<Integer> receivedMariosy, Set<Integer> sentMariosy) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.receivedMariosy = receivedMariosy;
        this.sentMariosy = sentMariosy;
    }

    public int getId() {
        return id;
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

    public Set<Integer> getReceivedMarios() {
        return receivedMariosy;
    }

    public void addReceivedMarios(Integer receivedMarios) {
        this.receivedMariosy.add(receivedMarios);
    }

    public void deleteReceivedMarios(Integer receivedMarios) {
        this.receivedMariosy.remove(receivedMarios);
    }

    public Set<Integer> getSentMarios() {
        return sentMariosy;
    }

    public void addSentMarios(Integer sentMarios) {
        this.sentMariosy.add(sentMarios);
    }

    public void deleteSentMarios(Integer sentMarios) {
        this.sentMariosy.remove(sentMarios);
    }
}
