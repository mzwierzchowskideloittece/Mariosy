package com.deloitte.ads.controller;

import com.deloitte.ads.repository.MariosType;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class MariosTypeDTO {

    @Getter @Setter
    private UUID externalId;

    @Getter @Setter
    private String type;


    public MariosTypeDTO(MariosType mariosType) {

        this.externalId = mariosType.getExternalId();
        this.type = mariosType.getType();

    }
}
