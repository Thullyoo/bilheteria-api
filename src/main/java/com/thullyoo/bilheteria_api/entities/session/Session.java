package com.thullyoo.bilheteria_api.entities.session;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.thullyoo.bilheteria_api.entities.ticket.Ticket;
import com.thullyoo.bilheteria_api.entities.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;

@Entity
@Table(name = "TB_SESSIONS")
@Getter
@Setter
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
public class Session {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String room;

    private Integer capacity;

    private LocalDate day;

    private Time time;

    @JsonIdentityReference(alwaysAsId = true)
    @JsonProperty("movie_id")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "session")
    private Set<Ticket> tickets;

    public Session() {
    }

    public Session(Long id, String room, Integer capacity, LocalDate day, Time time, Movie movie, Set<Ticket> tickets) {
        this.id = id;
        this.room = room;
        this.capacity = capacity;
        this.day = day;
        this.time = time;
        this.movie = movie;
        this.tickets = tickets;
    }

    public Session(SessionRequest sessionRequest) {
        this.capacity = sessionRequest.capacity();
        this.day = LocalDate.parse(sessionRequest.day(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.time = Time.valueOf(sessionRequest.time());
        this.room = sessionRequest.room();
    }

    public SessionResponse toSessionResponse(Session session) {
        return new SessionResponse(session.getId(), session.getMovie().getId(), session.getRoom(), session.getCapacity(), session.getDay().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")), session.getTime().toString());
    }
}
