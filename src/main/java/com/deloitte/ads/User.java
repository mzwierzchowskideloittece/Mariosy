package com.deloitte.ads;

import java.util.HashSet;
import java.util.Set;
import java.util.regex.Pattern;

public class User {

    private String email;
    private String firstName;
    private String lastName;
    private Set<Marios> receivedMariosy;
    private Set<Marios> sentMariosy;

    public User(String email, String firstName, String lastName) {

        if(email == null || firstName == null || lastName == null || !Pattern.compile("^(?=.{1,64}@)[\\p{L}0-9_-]+(\\.[\\p{L}0-9_-]+)*@"
                + "[^-][\\p{L}0-9-]+(\\.[\\p{L}0-9-]+)*(\\.[\\p{L}]{2,})$").matcher(email).matches()) throw new IllegalArgumentException("Wrong input");

        this.email = email;
        this.firstName = firstName;
        this.lastName = lastName;
        this.receivedMariosy = new HashSet<>();
        this.sentMariosy = new HashSet<>();
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

    public Set<Marios> getReceivedMarios() {
        return receivedMariosy;
    }

    public void setReceivedMarios(Set<Marios> ReceivedMariosy) {
        this.receivedMariosy = ReceivedMariosy;
    }

    public void addReceivedMarios(Marios receivedMarios) {
        this.receivedMariosy.add(receivedMarios);
    }

    public void deleteReceivedMarios(Marios receivedMarios) {
        this.receivedMariosy.remove(receivedMarios);
    }

    public Set<Marios> getSentMarios() {
        return sentMariosy;
    }

    public void addSentMarios(Marios sentMarios) {
        this.sentMariosy.add(sentMarios);
    }

    public void deleteSentMarios(Marios sentMarios) {
        this.sentMariosy.remove(sentMarios);
    }

    public void displayReceivedMariosy(){
        int numerate = 0;
        for(Marios receivedMarios: this.getReceivedMarios()) {
            System.out.println(++numerate);
            System.out.println("Type: " + receivedMarios.getType());
            System.out.print("Sent to: ");
            receivedMarios.getTo().stream().forEach(user -> System.out.print(user.getFirstName() + user.getLastName() + "\n"));
            System.out.print("Comment: " + receivedMarios.getComment());
        }
    }
    public void displaySentMariosy() {
        int numerate = 0;
        for (Marios sentMarios : this.getSentMarios()) {
            System.out.println(++numerate);
            System.out.println("Type: " + sentMarios.getType());
            System.out.print("Sent to: ");
            sentMarios.getTo().stream().forEach(user -> System.out.print(user.getFirstName() + " " + user.getLastName() + "\n"));
            System.out.print("Comment: " + sentMarios.getComment());
        }
    }
}
