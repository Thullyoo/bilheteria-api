package com.thullyoo.bilheteria_api.entities.movie;

import com.thullyoo.bilheteria_api.entities.session.Session;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Set;
import java.util.stream.Collectors;

@Entity
@Table(name = "TB_MOVIES")
@Getter
@Setter
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String image;

    @Column
    private String name;

    private String  description;

    private Set<String> actors;

    private String genre;

    private LocalDate release_date;


    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<Session> sessions;

    public Movie(){

    }

    public Movie(Long id, String image, String name, String description, Set<String> actors, String genre, String release_date, Set<Session> sessions) {
        this.id = id;
        this.image = image;
        this.name = name;
        this.description = description;
        this.actors = actors;
        this.genre = genre;
        this.release_date = LocalDate.parse(release_date, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
        this.sessions = sessions;
    }

    public Movie(MovieRequest movieRequest) {
        this.image = movieRequest.image();
        this.name = movieRequest.name();
        this.description = movieRequest.description();
        this.actors = movieRequest.actors().stream().collect(Collectors.toSet());
        this.genre = movieRequest.genre();
        this.release_date = LocalDate.parse(movieRequest.release_date(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
    }

    public MovieResponse toMovieResponse(Movie movie){
        return new MovieResponse(movie.getId(), movie.getImage(), movie.getName(), movie.getDescription(), movie.getActors().stream().toList(), movie.getGenre(), movie.getRelease_date().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }
}
