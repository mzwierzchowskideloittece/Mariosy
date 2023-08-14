package com.deloitte.ads.repository;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;
import java.util.Set;
import java.util.UUID;

@Entity(name = "marios")
public class Marios {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    @Column(name = "marios_id")
    @Getter
    private Long id;

    @Getter @Setter
    private UUID externalId;

    @ManyToOne
    @JoinColumn(name="marios_type_id")
    @Getter @Setter
    private MariosType type;

    @Column(name = "title")
    @Getter @Setter
    private String title;

    @Column(name = "comment")
    @Getter @Setter
    private String comment;

    @Column(name = "creation_date")
    @Getter
    private Instant creationDate;

    @ManyToMany
    @JsonBackReference
    @JoinTable(
            name = "user_data_received_marios",
            joinColumns = @JoinColumn(name = "marios_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id"))
    @Getter @Setter
    private Set<User> receivers;

    @ManyToOne
    @JsonBackReference
    @JoinColumn(name="user_id")
    @Getter @Setter
    private User sender;

    public Marios() {}

    private Marios(String title, String comment) {

        this.externalId = UUID.randomUUID();
        this.title = title;
        this.comment = comment;
        this.creationDate = Instant.now();

    }

    public static Marios createMarios(String title, String comment) {

        if(comment == null) comment = "";

        return new Marios(title, comment);

    }

    public static int compare(Marios marios1, Marios marios2) { return -marios1.getCreationDate().compareTo(marios2.getCreationDate()); }
}
