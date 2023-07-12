package com.deloitte.ads.controller;

import com.deloitte.ads.service.App;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/mariosy/")
public class MariosController {

    @Autowired
    private App app;

    @PostMapping
    public void sendMarios(@RequestBody MariosDTO mariosDTO) {
        app.sendMarios(mariosDTO.getType(), mariosDTO.getComment(), mariosDTO.getFrom(), mariosDTO.getTo());
    }
}
