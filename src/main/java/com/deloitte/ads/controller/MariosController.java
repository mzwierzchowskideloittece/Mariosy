package com.deloitte.ads.controller;

import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/marios")
public class MariosController {

    private final SomeService someService;

    @Autowired
    public MariosController(SomeService someService) {

        this.someService = someService;
    }

    @GetMapping()
    public ResponseEntity<Set<MariosDTO>> getMariosSet() {

        try {
            return new ResponseEntity<>(someService.getMariosSet().stream().map(MariosDTO::new).collect(Collectors.toSet()), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping
    public ResponseEntity<MariosDTO> addMarios(@RequestBody MariosDTO mariosDTO) {

        try {
            return new ResponseEntity<>(new MariosDTO(someService.addMarios(mariosDTO.getType(), mariosDTO.getComment(), mariosDTO.getFromEmail(), mariosDTO.getToEmails())), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
