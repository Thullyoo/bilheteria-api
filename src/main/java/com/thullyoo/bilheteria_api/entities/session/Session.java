package com.thullyoo.bilheteria_api.entities.session;

import com.thullyoo.bilheteria_api.entities.ticket.Ticket;
import com.thullyoo.bilheteria_api.entities.movie.Movie;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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

    private LocalDate day;

    private Time time;

    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;

    @OneToMany(mappedBy = "session")
    private Set<Ticket> tickets;

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
