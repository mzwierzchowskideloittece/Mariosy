package com.deloitte.ads.controller;

import com.deloitte.ads.repository.Marios;
import com.deloitte.ads.repository.User;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;
import java.util.stream.Collectors;

public class MariosDTO {

    private String type;

    @Getter @Setter
    private String comment;

    @Getter @Setter
    private String fromEmail;

    @Getter @Setter
    private Set<String> toEmails;

    public MariosDTO(Marios marios) {

        this.type = marios.getType().name();
        this.comment = marios.getComment();
        this.fromEmail = marios.getSender().getEmail();
        this.toEmails = marios.getReceivers().stream()
                .map(User::getEmail)
                .collect(Collectors.toSet());

    }

    public Marios.TypeEnum getType() {
        return Marios.TypeEnum.valueOf(type.toUpperCase());
    }

    public void setType(Marios.TypeEnum type) {
        this.type = type.name();
    }

}
