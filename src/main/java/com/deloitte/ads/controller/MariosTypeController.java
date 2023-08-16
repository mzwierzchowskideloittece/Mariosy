package com.deloitte.ads.controller;

import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/api/v1/types")
public class MariosTypeController {

    private final SomeService someService;

    @Autowired
    public MariosTypeController(SomeService someService) {

        this.someService = someService;

    }


    @GetMapping()
    public ResponseEntity<List<MariosTypeDTO>> getMariosTypes(JwtAuthenticationToken authentication) {

        someService.getUserAndAddIfDoesNotExist(authentication.getName());

        return new ResponseEntity<>(someService.getMariosTypes().stream().map(MariosTypeDTO::new).collect(Collectors.toList()), HttpStatus.OK);

    }


    @PostMapping
    public ResponseEntity<MariosTypeDTO> addMariosType(@RequestBody MariosTypeDTO mariosTypeDTO, JwtAuthenticationToken authentication) {

        someService.getUserAndAddIfDoesNotExist(authentication.getName());

        return new ResponseEntity<>(new MariosTypeDTO(someService.addMariosType(mariosTypeDTO.getType())), HttpStatus.OK);

    }
}