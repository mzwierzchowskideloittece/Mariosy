package com.deloitte.ads.controller;

import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Set<UserDTO>> getAllUsers() {

        try {

            return new ResponseEntity<>(someService.getUsers().stream().map(UserDTO::new).collect(Collectors.toSet()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("{externalId}/latest")
    public ResponseEntity<List<OutputMariosDTO>> get9LatestMariosListOfUser(@PathVariable UUID externalId) {

        try {


            List<OutputMariosDTO> latestMariosListOfUser = someService.get9LatestMariosListOfUser(externalId).stream().map(marios -> {
                if(marios.getSender().getUserName().equals(someService.getUser(externalId).getUserName())) { return new OutputMariosDTO(marios, "sent"); }
                else { return new OutputMariosDTO(marios, "received"); }

            }).collect(Collectors.toList());

            return new ResponseEntity<>(latestMariosListOfUser, HttpStatus.OK);
        } catch (Exception e) {

            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("{externalId}/sent")
    public ResponseEntity<List<OutputMariosDTO>> getSentMariosSetOfUser(@PathVariable UUID externalId) {

        try {

            return new ResponseEntity<>(someService.getSentMariosListOfUser(externalId).stream().map(marios -> new OutputMariosDTO(marios, "sent")).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{externalId}/received")
    public ResponseEntity<List<OutputMariosDTO>> getReceivedMariosSetOfUser(@PathVariable UUID externalId) {

        try {

            return new ResponseEntity<>(someService.getReceivedMariosListOfUser(externalId).stream().map(marios -> new OutputMariosDTO(marios, "received")).collect(Collectors.toList()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
