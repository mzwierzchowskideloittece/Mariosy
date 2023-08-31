package com.deloitte.ads.service;

import com.deloitte.ads.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.AdditionalAnswers;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.lang.reflect.Field;
import java.util.*;
import java.util.stream.StreamSupport;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;


@ExtendWith(MockitoExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class SomeServiceTest {


    MariosRepository mariosRepository;


    UserRepository userRepository;


    MariosTypeRepository mariosTypeRepository;


    SomeService someService;

    String userNameExpected = "Mario Mariowski";
    String userNameExpected2 = "Luigi Luigiowski";
    String titleExpected = "Title";
    String commentExpected = "Comment";
    String typeNameExpected = "Type";
    String typeNameExpected2 = "Type2";

    Iterable<MariosType> mariosTypesExpected;
    Iterable<User> usersExpected;

    UUID goodUUID = UUID.fromString("a445e170-4782-11ee-be56-0242ac120002");
    UUID badUUID = UUID.fromString("b200f80e-4782-11ee-be56-0242ac120002");


    @BeforeAll
    public void setUp() {

        mariosRepository = mock(MariosRepository.class);
        userRepository = mock(UserRepository.class);
        mariosTypeRepository = mock(MariosTypeRepository.class);

        someService = new SomeService(mariosRepository, userRepository, mariosTypeRepository);


        Mockito.when(mariosRepository.save(any(Marios.class))).then(AdditionalAnswers.returnsFirstArg());





        usersExpected = Arrays.asList(User.createUser(userNameExpected), User.createUser(userNameExpected2));

        Mockito.when(userRepository.save(any(User.class))).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(userRepository.findAll()).thenReturn(usersExpected);
        Mockito.when(userRepository.findByUserName(any(String.class))).thenReturn(Optional.of(User.createUser(AdditionalAnswers.returnsFirstArg().toString())));
        Mockito.when(userRepository.findByExternalId(goodUUID)).thenReturn(Optional.of(createUserAndSetExternalId(goodUUID, userNameExpected)));
        Mockito.when(userRepository.findByExternalId(badUUID)).thenReturn(Optional.empty());





        mariosTypesExpected = Arrays.asList(MariosType.createMariosType(typeNameExpected), MariosType.createMariosType(typeNameExpected2));

        Mockito.when(mariosTypeRepository.save(any(MariosType.class))).then(AdditionalAnswers.returnsFirstArg());
        Mockito.when(mariosTypeRepository.findAll()).thenReturn(mariosTypesExpected);
        Mockito.when(mariosTypeRepository.findByExternalId(goodUUID)).thenReturn(Optional.of(createMariosTypeAndSetExternalId(goodUUID, typeNameExpected)));

    }



    User createUserAndSetExternalId(UUID externalId, String userName) {
        User user = User.createUser(userName);

        try {
            Field field = user.getClass().getDeclaredField("externalId");
            field.setAccessible(true);
            field.set(user, externalId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        return user;

    }

    MariosType createMariosTypeAndSetExternalId(UUID externalId, String typeName) {
        MariosType type = MariosType.createMariosType(typeName);

        try {
            Field field = type.getClass().getDeclaredField("externalId");
            field.setAccessible(true);
            field.set(type, externalId);
        }
        catch (Exception e){
            throw new RuntimeException(e);
        }

        return type;

    }


    @Test
    void shouldGetUser() {
        User userActual = someService.getUser(goodUUID);

        Assertions.assertEquals(userNameExpected, userActual.getUserName());
        Assertions.assertEquals(goodUUID, userActual.getExternalId());
    }

    @Test
    void shouldGetUsers() {
        Set<User> usersActual = someService.getUsers();

        for(User user: usersActual) {

            Assertions.assertTrue(userNameExpected.equals(user.getUserName()) || userNameExpected2.equals(user.getUserName()));
        }

    }

    @Test
    void shouldGetMariosTypes() {

        List<MariosType> mariosTypesActual = someService.getMariosTypes();

        Assertions.assertEquals(typeNameExpected, mariosTypesActual.get(0).getType());
        Assertions.assertEquals(typeNameExpected2, mariosTypesActual.get(1).getType());
        Assertions.assertEquals((int) StreamSupport.stream(mariosTypesExpected.spliterator(), false).count(), mariosTypesActual.size());

    }

    @Test
    void shouldAddUser() {

        User userActual = someService.addUser(userNameExpected);

        Assertions.assertEquals(userNameExpected, userActual.getUserName());
    }

    @Test
    void shouldAddMarios() {

        Marios mariosActual = someService.addMarios(goodUUID, titleExpected, commentExpected, goodUUID, Set.of(goodUUID));

        Assertions.assertEquals(titleExpected, mariosActual.getTitle());
        Assertions.assertEquals(commentExpected, mariosActual.getComment());

    }

    @Test
    void shouldAddMariosType() {

        MariosType type = someService.addMariosType(typeNameExpected);

        Assertions.assertEquals(typeNameExpected, type.getType());

    }

}