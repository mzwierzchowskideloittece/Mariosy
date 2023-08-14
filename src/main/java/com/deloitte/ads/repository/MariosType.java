package com.deloitte.ads.repository;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.UUID;

@Entity(name = "marios_type")
public class MariosType {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "marios_type_id")
    @Getter
    private Long id;

    @Getter @Setter
    private UUID externalId;

    @Getter @Setter
    private String type;

    public MariosType() {}

    private MariosType(String type) {

        this.type = type;
        this.externalId = UUID.randomUUID();

    }

    public static MariosType createMariosType(String type) {

        if(type == null) throw new IllegalArgumentException("Type is required");

        return new MariosType(type);

    }


}
