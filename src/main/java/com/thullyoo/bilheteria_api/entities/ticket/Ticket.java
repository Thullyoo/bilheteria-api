package com.thullyoo.bilheteria_api.entities.ticket;

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

    @ManyToOne
    @JoinColumn(name = "session_id")
    private Session session;
}
