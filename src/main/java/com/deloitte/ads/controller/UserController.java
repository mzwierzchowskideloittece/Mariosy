package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private SomeService someService;


    @GetMapping()
    public Set<User> getAllUsers() {
        return someService.getUsers();
    }

    @GetMapping("{id}/sent")
    public Set<Marios> getUserSentMarios(@PathVariable long id) {
        return someService.getUserSentMariosy(id);
    }

    @GetMapping("{id}/received")
    public Set<Marios> getUserReceivedMarios(@PathVariable long id) {
        return someService.getUserReceivedMariosy(id);
    }

    @PostMapping
    public User addUser(@RequestBody UserDTO userDTO) {
        return someService.addUser(userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName());
    }


}
