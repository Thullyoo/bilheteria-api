package com.thullyoo.bilheteria_api.entities.ticket;

import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thullyoo.bilheteria_api.entities.session.Session;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "TB_TICKETS")
@Getter
@Setter
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double value;

    private String chair;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("session_id")
    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
