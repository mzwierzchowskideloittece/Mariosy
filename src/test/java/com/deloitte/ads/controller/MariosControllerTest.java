package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;


@SpringBootTest
class MariosControllerTest {

    @Autowired
    private MariosController mariosController;

    @Autowired
    private UserController userController;

    @Test
    void sendMarios() {

        Set<Marios> mariosyBeforeSending = mariosController.getMariosy();
        Integer count = mariosyBeforeSending.size();

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setEmail("asd@asd.asd");
        userDTO1.setFirstName("Helga");
        userDTO1.setLastName("Kowalska");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setEmail("asda@asda.asda");
        userDTO2.setFirstName("Olek");
        userDTO2.setLastName("Olkowski");

        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setType(Marios.TypeEnum.HAPPY);
        mariosDTO.setComment("comment");
        mariosDTO.setFrom(userController.addUser(userDTO1));
        mariosDTO.setTo(Sets.newHashSet(userController.addUser(userDTO2)));

        mariosController.sendMarios(mariosDTO);

        Set<Marios> mariosyAfterSending = mariosController.getMariosy();

        Assertions.assertEquals(count+1, mariosyAfterSending.size());

    }
}