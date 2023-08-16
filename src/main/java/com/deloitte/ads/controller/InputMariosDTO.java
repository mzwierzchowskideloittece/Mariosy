package com.deloitte.ads.controller;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.UUID;


public class InputMariosDTO {

    @Getter @Setter
    private UUID typeExternalId;

    @Getter @Setter
    private String title;

    @Getter @Setter
    private String comment;

    @Getter @Setter
    private UUID externalIdOfSender;

    @Getter @Setter
    private Set<UUID> externalIdsOfReceivers;

    public InputMariosDTO(){}



}
