package com.deloitte.ads.service;

import com.deloitte.ads.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Service
public class SomeService {

    private final MariosRepository mariosRepository;

    private final UserRepository userRepository;
    private final MariosTypeRepository mariosTypeRepository;

    @Autowired
    public SomeService(MariosRepository mariosRepository, UserRepository userRepository, MariosTypeRepository mariosTypeRepository) {
        this.mariosRepository = mariosRepository;
        this.userRepository = userRepository;
        this.mariosTypeRepository = mariosTypeRepository;
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

    public List<MariosType> getMariosTypes() {

        Iterable<MariosType> mariosTypes = mariosTypeRepository.findAll();

        return StreamSupport.stream(mariosTypes.spliterator(), false)
                .collect(Collectors.toList());

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


    public MariosType addMariosType(String type) {

        MariosType newMariosType = MariosType.createMariosType(type);
        mariosTypeRepository.save(newMariosType);
        return newMariosType;
    }



/*    @PostConstruct
    public void aaa() {

        MariosType newMariosType = addMariosType("WOW!");

        User user1 = addUser("xyz@abcd.pl", "Mario", "Mariowski");
        User user2 = addUser("abcd@xyz.pl", "Luigi", "Luigiowski");
        Set<User> set = new HashSet<>();
        set.add(user2);

        addMarios(newMariosType, "Hello", user1.getEmail(), set.stream().map(user -> user.getEmail()).collect(Collectors.toSet()));
    }*/
}
