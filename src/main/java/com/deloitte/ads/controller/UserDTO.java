package com.deloitte.ads.controller;

import com.deloitte.ads.repository.User;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;


public class UserDTO {

    @Getter @Setter
    UUID externalId;

    @Getter @Setter
    String userName;

    public UserDTO() {}

    public UserDTO(User user) {
        this.userName = user.getUserName();
        this.externalId = user.getExternalId();
    }
}
