package com.deloitte.ads.service;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.MariosRepository;
import com.deloitte.ads.repository.User;
import com.deloitte.ads.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashSet;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SomeService {

    private final MariosRepository mariosRepository;

    private final UserRepository userRepository;

    @Autowired
    public SomeService(MariosRepository mariosRepository, UserRepository userRepository) {
        this.mariosRepository = mariosRepository;
        this.userRepository = userRepository;
    }

    public Set<User> getUsers() {

        Iterable<User> users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toSet());
    }

    public Set<Marios> getMariosSet() {

        Iterable<Marios> mariosQueryResult = mariosRepository.findAll();

        return StreamSupport.stream(mariosQueryResult.spliterator(), false)
                .collect(Collectors.toSet());
    }


    public Set<Marios> getSentMariosSetOfUser(String email) {
        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getSentMariosSet();
        } else {
            return new HashSet<>();
        }
    }

    public Set<Marios> getReceivedMariosSetOfUser(String email) {

        Optional<User> userOptional = userRepository.findByEmail(email);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            return user.getReceivedMariosSet();
        } else {
            return new HashSet<>();
        }
    }

    public Marios addMarios(Marios.TypeEnum type, String comment, String fromEmail, Set<String> toEmails) {

        Optional<User> fromUserOptional= userRepository.findByEmail(fromEmail);

        Marios newMarios = Marios.createMarios(type, comment);

        if(fromUserOptional.isPresent()) {
            newMarios.setSender(fromUserOptional.get());
        } else {
            throw new IllegalArgumentException("Sender doesn't exist");
        }

        Set<User> set = toEmails.stream().map(email -> {
            Optional<User> toUserOptional = userRepository.findByEmail(email);
            return toUserOptional.orElse(null);
        }).filter(Objects::nonNull).collect(Collectors.toSet());

        if(set.isEmpty()){
            throw new IllegalArgumentException("None of receivers exist");
        }

        newMarios.setReceivers(set);
        mariosRepository.save(newMarios);

        return newMarios;
    }

    public User addUser(String email, String firstName, String lastName) {

        User newUser = User.createUser(email, firstName, lastName);
        userRepository.save(newUser);
        return newUser;
    }

/*    @PostConstruct
    public void aaa() {
        User user1 = addUser("xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = addUser("abcd@xyz.pl", "Luigi", "Luigiowski");
        Set<User> set = new HashSet<>();
        set.add(user2);

        addMarios(Marios.TypeEnum.HAPPY, "Hello", user1.getEmail(), set.stream().map(user -> user.getEmail()).collect(Collectors.toSet()));
    }*/
}
