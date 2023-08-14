package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.MariosType;
import com.deloitte.ads.repository.User;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

public class OutputMariosDTO {

    @Getter @Setter
    private UUID externalId;

    @Getter @Setter
    private String sentOrReceived;

    @Getter @Setter
    private MariosType type;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String comment;

    @Getter @Setter
    private String userNameOfSender;

    @Getter @Setter
    private Set<String> userNamesOfReceivers;

    @Getter @Setter
    private Instant creationDate;

    public OutputMariosDTO(){}

    public OutputMariosDTO(Marios marios, String sentOrReceived) {

        this.externalId = marios.getExternalId();
        this.sentOrReceived = sentOrReceived;
        this.title = marios.getTitle();
        this.type = marios.getType();
        this.comment = marios.getComment();
        this.userNameOfSender = marios.getSender().getUserName();
        this.userNamesOfReceivers = marios.getReceivers().stream()
                .map(User::getUserName)
                .collect(Collectors.toSet());
        this.creationDate = marios.getCreationDate();

    }

}
