package com.deloitte.ads.service;

import com.deloitte.ads.repository.*;
import com.google.common.collect.Sets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
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



    public User getUser(UUID externalId) {

        Optional<User> userOptional = userRepository.findByExternalId(externalId);

        if(userOptional.isPresent()) {
            return userOptional.get();
        }  else {
            throw new NoSuchElementException("No such user");
        }
    }

    public Set<User> getUsers() {

        Iterable<User> users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toSet());
    }

    public List<Marios> get9LatestMariosListOfUser(UUID externalId) {

        Stream<Marios> sentMariosStream = getReceivedMariosSetOfUser(externalId).stream();
        Stream<Marios> recivedMariosStream = getSentMariosSetOfUser(externalId).stream();
        List<Marios> latestMariosList = Stream.concat(sentMariosStream, recivedMariosStream).sorted(Marios::compare).collect(Collectors.toList());

        return latestMariosList.subList(0, Math.min(9, latestMariosList.size()));

    }

    public List<Marios> getSentMariosSetOfUser(UUID externalId) {

        return getUser(externalId).getSentMariosList().stream().sorted(Marios::compare).collect(Collectors.toList());

    }

    public List<Marios> getReceivedMariosSetOfUser(UUID externalId) {

        return getUser(externalId).getReceivedMariosList().stream().sorted(Marios::compare).collect(Collectors.toList());

    }

    public List<MariosType> getMariosTypes() {

        Iterable<MariosType> mariosTypes = mariosTypeRepository.findAll();

        return StreamSupport.stream(mariosTypes.spliterator(), false)
                .collect(Collectors.toList());

    }


    public User addUser(String userName) {

        User newUser = User.createUser(userName);
        userRepository.save(newUser);
        return newUser;
    }


    public Marios addMarios(UUID typeExternalId, String title, String comment, UUID externalIdOfSender, Set<UUID> externalIdsOfReceivers) {

        Marios newMarios = Marios.createMarios(title, comment);

        Optional<MariosType> mariosTypeOptional= mariosTypeRepository.findByExternalId(typeExternalId);

        if(mariosTypeOptional.isPresent()) {
            newMarios.setType(mariosTypeOptional.get());
        } else {
            throw new NoSuchElementException("Marios type doesn't exist");
        }



        Optional<User> fromUserOptional= userRepository.findByExternalId(externalIdOfSender);

        if(fromUserOptional.isPresent()) {
            newMarios.setSender(fromUserOptional.get());
        } else {
            throw new NoSuchElementException("Sender doesn't exist");
        }



        Set<User> set = externalIdsOfReceivers.stream().map(externalId -> {
            Optional<User> toUserOptional = userRepository.findByExternalId(externalId);
            return toUserOptional.orElse(null);
        }).filter(Objects::nonNull).collect(Collectors.toSet());

        if(set.isEmpty()){
            throw new NoSuchElementException("None of receivers exist");
        }

        newMarios.setReceivers(set);


        mariosRepository.save(newMarios);

        return newMarios;
    }


    public MariosType addMariosType(String type) {

        MariosType newMariosType = MariosType.createMariosType(type);
        mariosTypeRepository.save(newMariosType);
        return newMariosType;
    }

    @PostConstruct
    public void aaa() {

        MariosType newMariosType = addMariosType("WOW!");

        User user1 = this.addUser("Mario_Mariowski");

        User user2 = this.addUser("Luigi_Luigiowski");

        Set<UUID> set = new HashSet<>();
        set.add(user2.getExternalId());

       this.addMarios(newMariosType.getExternalId(), "Title", "Comment", user1.getExternalId(), set);

       }
}
