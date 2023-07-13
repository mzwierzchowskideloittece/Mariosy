package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.service.SomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@RestController
@RequestMapping("/api/v1/mariosy/")
public class MariosController {

    @Autowired
    private SomeService someService;

    @GetMapping()
    public Set<Marios> getMariosy() {
        return someService.getMariosy();
    }

    @PostMapping
    public void sendMarios(@RequestBody MariosDTO mariosDTO) {
        someService.addMarios(mariosDTO.getType(), mariosDTO.getComment(), mariosDTO.getFrom(), mariosDTO.getTo());
    }
}
