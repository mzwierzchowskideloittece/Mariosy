package com.deloitte.ads.service;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.MariosRepository;
import com.deloitte.ads.repository.User;
import com.deloitte.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SomeService {

    @Autowired
    private MariosRepository mariosRepository;

    @Autowired
    private UserRepository userRepository;

    public Set<User> getUsers() {

        Iterable<User> users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toSet());
    }

    public Set<Marios> getMariosy() {

        Iterable<Marios> mariosy = mariosRepository.findAll();

        return StreamSupport.stream(mariosy.spliterator(), false)
                .collect(Collectors.toSet());
    }


    public Set<Marios> getUserSentMariosy(long id) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getSentMariosy();
        }

        Set<Marios> set = new HashSet<>();

        return set;
    }

    public Set<Marios> getUserReceivedMariosy(long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getReceivedMariosy();
        }

        Set<Marios> set = new HashSet<>();

        return set;
    }

    public Marios addMarios(Marios.TypeEnum type, String comment, User from, Set<User> to) {

        try {
            Marios newMarios = new Marios(type, comment);
            newMarios.setSender(from);
            mariosRepository.save(newMarios);
            to.stream().forEach(user -> {
                user.addReceivedMarios(newMarios);
                userRepository.save(user);});
            //newMarios.setReceivers(to);
            return newMarios;
        } catch (Exception e) {
            throw e;
        }
    }

    public User addUser(String email, String firstName, String lastName) {

        try {
            User newUser = new User(email, firstName, lastName);
            userRepository.save(newUser);
            return newUser;
        } catch (Exception e) {
            throw e;
        }
    }

    @PostConstruct
    public void aaa() {
        User user1 = addUser("xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = addUser("abcd@xyz.pl", "Luigi", "Luigiowski");
        Set<User> set = new HashSet<>();
        set.add(user2);

        addMarios(Marios.TypeEnum.HAPPY, "Hello", user1, set);
    }
}
