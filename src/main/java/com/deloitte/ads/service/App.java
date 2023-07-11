package com.deloitte.ads.service;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import com.google.common.collect.Sets;

import java.util.Set;

public class App {

    private Set<User> users;


    public App(){

        User user1 = new User(0,"xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = new User(1, "abcd@xyz.pl", "Luigi", "Luigiowski");

        this.users = Sets.newHashSet(user1, user2);

        Marios marios = new Marios(0, Marios.TypeEnum.HAPPY, "Hello",user1, Sets.newHashSet(user2));

        user1.addSentMarios(marios);
        user2.addReceivedMarios(marios);

        user1.displaySentMariosy();

    }
}
