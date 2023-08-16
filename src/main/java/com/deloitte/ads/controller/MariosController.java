package com.deloitte.ads.controller;

import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/marios")
public class MariosController {

    private final SomeService someService;

    @Autowired
    public MariosController(SomeService someService) {

        this.someService = someService;
    }

    @PostMapping
    public ResponseEntity<OutputMariosDTO> addMarios(@RequestBody InputMariosDTO inputMariosDTO) {

        try {

            return new ResponseEntity<>(new OutputMariosDTO(someService.addMarios(inputMariosDTO.getTypeExternalId(), inputMariosDTO.getTitle(), inputMariosDTO.getComment(), inputMariosDTO.getExternalIdOfSender(), inputMariosDTO.getExternalIdsOfReceivers()), "sent"), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
