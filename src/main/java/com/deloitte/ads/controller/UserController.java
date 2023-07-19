package com.deloitte.ads.controller;

import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
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

    @GetMapping("{email}/sent")
    public ResponseEntity<Set<MariosDTO>> getSentMariosSetOfUser(@PathVariable String email) {

        try {
            return new ResponseEntity<>(someService.getSentMariosSetOfUser(email).stream().map(MariosDTO::new).collect(Collectors.toSet()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("{email}/received")
    public ResponseEntity<Set<MariosDTO>> getReceivedMariosSetOfUser(@PathVariable String email) {

        try {
            return new ResponseEntity<>(someService.getReceivedMariosSetOfUser(email).stream().map(MariosDTO::new).collect(Collectors.toSet()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<UserDTO> addUser(@RequestBody UserDTO userDTO) {

        try {
            return new ResponseEntity<>(new UserDTO(someService.addUser(userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
