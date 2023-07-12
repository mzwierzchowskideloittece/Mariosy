package com.deloitte.ads.service;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import com.google.common.collect.Sets;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.OptionalInt;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class App {

    private Set<User> users;
    private Set<Marios> mariosy;

    public App() {

        this.mariosy = new HashSet<>();
        this.users = new HashSet<>();

        User user1 = addUser("xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = addUser("abcd@xyz.pl", "Luigi", "Luigiowski");

        sendMarios(Marios.TypeEnum.HAPPY, "Hello", user1, Sets.newHashSet(user2));

    }

    public Set<User> getUsers() {
        return users;
    }

    public Set<Marios> getMariosy() {
        return mariosy;
    }


    public Set<Marios> getUserSentMariosy(int id) {

        return mariosy.stream().filter(marios -> marios.getFrom().getId() == id).collect(Collectors.toSet());

    }

    public Set<Marios> getUserReceivedMariosy(int id) {

        return mariosy.stream()
                .filter(marios -> !marios.getTo().stream()
                        .filter(user -> user.getId() == id)
                        .collect(Collectors.toSet())
                        .isEmpty())
                .collect(Collectors.toSet());

    }

    public void sendMarios(Marios.TypeEnum type, String comment, User from, Set<User> to) {

        int id;
        OptionalInt idOptional = this.mariosy.stream().map(marios -> marios.getId()).mapToInt(Integer::intValue).max();

        if (idOptional.isPresent()) {
            id = idOptional.getAsInt() + 1;
        } else {
            id = 0;
        }

        Marios marios = new Marios(id, type, comment, from, to);

        this.mariosy.add(marios);

    }

    public User addUser(String email, String firstName, String lastName) {

        int id;

        OptionalInt idOptional = this.users.stream().map(user -> user.getId()).mapToInt(Integer::intValue).max();

        if (idOptional.isPresent()) {
            id = idOptional.getAsInt() + 1;
        } else {
            id = 0;
        }

        try {
            User newUser = new User(id, email, firstName, lastName);
            this.users.add(newUser);
            return newUser;
        } catch (Exception e) {
            throw e;
        }
    }
}
