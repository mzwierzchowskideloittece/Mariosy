package com.deloitte.ads;

import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) {

        Users users = new Users(new HashSet<>());

        User user1 = new User("xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = new User("abcd@xyz.pl", "Luigi", "Luigiowski");
        users.addUser(user1);
        users.addUser(user2);

        Set<User> sentTo= new HashSet<>();

        sentTo.add(user2);

        Marios marios = new Marios(Marios.TypeEnum.HAPPY, "Hello",user1, sentTo);

        user1.addSentMarios(marios);
        user2.addReceivedMarios(marios);

        user1.displaySentMariosy();

    }
}
