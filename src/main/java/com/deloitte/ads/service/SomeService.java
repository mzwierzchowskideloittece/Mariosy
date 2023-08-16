package com.deloitte.ads.service;

import com.deloitte.ads.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
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



    public Set<User> getUsers() {

        Iterable<User> users = userRepository.findAll();

        return StreamSupport.stream(users.spliterator(), false)
                .collect(Collectors.toSet());
    }

    public List<Marios> get9LatestMariosListOfUser(User user) {

        Stream<Marios> sentMariosStream = getReceivedMariosSetOfUser(user).stream();
        Stream<Marios> recivedMariosStream = getSentMariosSetOfUser(user).stream();
        List<Marios> latestMariosList = Stream.concat(sentMariosStream, recivedMariosStream).sorted(Marios::compare).collect(Collectors.toList());

        return latestMariosList.subList(0, Math.min(9, latestMariosList.size()));

    }

    public List<Marios> getSentMariosSetOfUser(User user) {

        return user.getSentMariosList().stream().sorted(Marios::compare).collect(Collectors.toList());

    }

    public List<Marios> getReceivedMariosSetOfUser(User user) {

        return user.getReceivedMariosList().stream().sorted(Marios::compare).collect(Collectors.toList());

    }

    public List<MariosType> getMariosTypes() {

        Iterable<MariosType> mariosTypes = mariosTypeRepository.findAll();

        return StreamSupport.stream(mariosTypes.spliterator(), false)
                .collect(Collectors.toList());

    }


    public User getUserAndAddIfDoesNotExist(String userName) {

        Optional<User> userOptional = userRepository.findByUserName(userName);

        return userOptional.orElseGet(() -> addUser(userName));

    }


    public User addUser(String userName) {

        User newUser = User.createUser(userName);
        userRepository.save(newUser);
        return newUser;
    }


    public Marios addMarios(UUID typeExternalId, String title, String comment, String userNameOfSender, Set<String> userNamesOfReceivers) {

        Marios newMarios = Marios.createMarios(title, comment);

        Optional<MariosType> mariosTypeOptional= mariosTypeRepository.findByExternalId(typeExternalId);

        if(mariosTypeOptional.isPresent()) {
            newMarios.setType(mariosTypeOptional.get());
        } else {
            throw new NoSuchElementException("Marios type doesn't exist");
        }



        Optional<User> fromUserOptional= userRepository.findByUserName(userNameOfSender);

        if(fromUserOptional.isPresent()) {
            newMarios.setSender(fromUserOptional.get());
        } else {
            throw new NoSuchElementException("Sender doesn't exist");
        }



        Set<User> set = userNamesOfReceivers.stream().map(userName -> {
            Optional<User> toUserOptional = userRepository.findByUserName(userName);
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

/*    @PostConstruct
    public void aaa() {

        MariosType newMariosType = addMariosType("WOW!");

       }*/
}
