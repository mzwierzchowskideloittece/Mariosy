package com.deloitte.ads.controller;

import com.deloitte.ads.repository.User;
import lombok.Getter;
import lombok.Setter;



public class UserDTO {

    @Getter @Setter
    String userName;

    public UserDTO() {}

    public UserDTO(User user) {
        this.userName = user.getUserName();
    }
}
