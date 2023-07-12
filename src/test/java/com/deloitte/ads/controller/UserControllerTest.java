package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import com.google.common.collect.Sets;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;


@SpringBootTest
class UserControllerTest {

    @Autowired
    private MariosController mariosController;

    @Autowired
    private UserController userController;

    @Test
    void checkUserSentMarios() {

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setEmail("asd@asd.asd");
        userDTO1.setFirstName("Helga");
        userDTO1.setLastName("Kowalska");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setEmail("asda@asda.asda");
        userDTO2.setFirstName("Olek");
        userDTO2.setLastName("Olkowski");


        User user1 = userController.addUser(userDTO1);
        User user2 = userController.addUser(userDTO2);


        Set<Marios> sentMariosyBeforeSending = userController.getUserSentMarios(user1.getId());
        Integer count = sentMariosyBeforeSending.size();


        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setType(Marios.TypeEnum.HAPPY);
        mariosDTO.setComment("comment");
        mariosDTO.setFrom(user1);
        mariosDTO.setTo(Sets.newHashSet(user2));

        mariosController.sendMarios(mariosDTO);

        Set<Marios> sentMariosyAfterSending = userController.getUserSentMarios(user1.getId());

        Assertions.assertEquals(count+1, sentMariosyAfterSending.size());

    }

    @Test
    void checkUserReceivedMarios() {

        UserDTO userDTO1 = new UserDTO();
        userDTO1.setEmail("asd@asd.asd");
        userDTO1.setFirstName("Helga");
        userDTO1.setLastName("Kowalska");

        UserDTO userDTO2 = new UserDTO();
        userDTO2.setEmail("asda@asda.asda");
        userDTO2.setFirstName("Olek");
        userDTO2.setLastName("Olkowski");


        User user1 = userController.addUser(userDTO1);
        User user2 = userController.addUser(userDTO2);


        Set<Marios> receivedMariosyBeforeSending = userController.getUserReceivedMarios(user2.getId());
        Integer count = receivedMariosyBeforeSending.size();


        MariosDTO mariosDTO = new MariosDTO();
        mariosDTO.setType(Marios.TypeEnum.HAPPY);
        mariosDTO.setComment("comment");
        mariosDTO.setFrom(user1);
        mariosDTO.setTo(Sets.newHashSet(user2));

        mariosController.sendMarios(mariosDTO);

        Set<Marios> receivedMariosyAfterSending = userController.getUserReceivedMarios(user2.getId());

        Assertions.assertEquals(count+1, receivedMariosyAfterSending.size());

    }

    @Test
    void shouldAddUser() {

        Set<User> usersBeforeAdd = userController.getAllUsers();
        int count = usersBeforeAdd.size();

        UserDTO userDTO = new UserDTO();
        userDTO.setEmail("abc@abc.de");
        userDTO.setFirstName("Heinz");
        userDTO.setLastName("Keczup");

        userController.addUser(userDTO);

        Set<User> usersAfterAdd = userController.getAllUsers();

        Assertions.assertEquals(count+1, usersAfterAdd.size());

    }
}