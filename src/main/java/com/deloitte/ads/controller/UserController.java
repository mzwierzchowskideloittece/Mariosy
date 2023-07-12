package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import com.deloitte.ads.service.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

    @Autowired
    private App app;

    @GetMapping()
    public Set<User> getAllUsers() {
        return app.getUsers();
    }

    @GetMapping("{id}/sent")
    public Set<Marios> getUserSentMarios(@PathVariable int id) {
        return app.getUserSentMariosy(id);
    }

    @GetMapping("{id}/received")
    public Set<Marios> getUserReceivedMarios(@PathVariable int id) {
        return app.getUserReceivedMariosy(id);
    }

    @PostMapping
    public void addUser(@RequestBody UserDTO userDTO) {
        app.addUser(userDTO.getEmail(), userDTO.getFirstName(), userDTO.getLastName());
    }


}
