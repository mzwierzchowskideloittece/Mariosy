package com.deloitte.ads.controller;

import com.deloitte.ads.repository.User;
import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {
    private final SomeService someService;

    @Autowired
    public UserController(SomeService someService) {

        this.someService = someService;
    }


    @GetMapping()
    public ResponseEntity<Set<UserDTO>> getAllUsers(JwtAuthenticationToken authentication) {

        try {
            someService.getUserAndAddIfDoesNotExist(authentication.getName());

            return new ResponseEntity<>(someService.getUsers().stream().filter(user -> !authentication.getName().equals(user.getUserName())).map(UserDTO::new).collect(Collectors.toSet()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("latest")
    public ResponseEntity<List<OutputMariosDTO>> get9LatestMariosListOfUser(JwtAuthenticationToken authentication) {

        try {

            User user = someService.getUserAndAddIfDoesNotExist(authentication.getName());

            List<OutputMariosDTO> latestMariosListOfUser = someService.get9LatestMariosListOfUser(user).stream().map(marios -> {
                if(marios.getSender().getUserName().equals(user.getUserName())) { return new OutputMariosDTO(marios, "sent"); }
                else { return new OutputMariosDTO(marios, "received"); }

            }).collect(Collectors.toList());

            return new ResponseEntity<>(latestMariosListOfUser, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("sent")
    public ResponseEntity<List<OutputMariosDTO>> getSentMariosSetOfUser(JwtAuthenticationToken authentication) {

        try {

            User user = someService.getUserAndAddIfDoesNotExist(authentication.getName());

            return new ResponseEntity<>(someService.getSentMariosSetOfUser(user).stream().map(marios -> new OutputMariosDTO(marios, "sent")).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("received")
    public ResponseEntity<List<OutputMariosDTO>> getReceivedMariosSetOfUser(JwtAuthenticationToken authentication) {

        try {

            User user = someService.getUserAndAddIfDoesNotExist(authentication.getName());

            return new ResponseEntity<>(someService.getReceivedMariosSetOfUser(user).stream().map(marios -> new OutputMariosDTO(marios, "received")).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
