package com.thullyoo.bilheteria_api.entities.session;

import com.thullyoo.bilheteria_api.entities.ticket.Ticket;
import com.thullyoo.bilheteria_api.entities.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Entity
@Table(name = "TB_SESSIONS")
@Getter
@Setter
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room;

    private Integer capacity;

    private String date;

    private String time;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "session")
    private Set<Ticket> tickets;
}
