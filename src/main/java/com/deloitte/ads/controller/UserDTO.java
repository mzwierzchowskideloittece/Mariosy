package com.deloitte.ads.controller;

import com.deloitte.ads.repository.User;
import lombok.Getter;
import lombok.Setter;


public class UserDTO {
    @Getter @Setter
    String email;

    @Getter @Setter
    String firstName;

    @Getter @Setter
    String lastName;

    public UserDTO(User user) {
        this.email = user.getEmail();
        this.firstName = user.getFirstName();
        this.lastName = user.getLastName();
    }
}
