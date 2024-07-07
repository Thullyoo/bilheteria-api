package com.thullyoo.bilheteria_api.services;

import com.thullyoo.bilheteria_api.entities.movie.Movie;
import com.thullyoo.bilheteria_api.entities.session.Session;
import com.thullyoo.bilheteria_api.entities.session.SessionRequest;
import com.thullyoo.bilheteria_api.entities.session.SessionResponse;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieNotFoundException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionIsPresentException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionNotFoundException;
import com.thullyoo.bilheteria_api.exceptions.session.SessionRelatedMovieException;
import com.thullyoo.bilheteria_api.repositories.MovieRepository;
import com.thullyoo.bilheteria_api.repositories.SessionRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.swing.text.DateFormatter;
import java.sql.Time;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class SessionService {

    private final SessionRepository sessionRepository;

    private final MovieRepository movieRepository;

    public SessionService(SessionRepository sessionRepository, MovieRepository movieRepository) {
        this.sessionRepository = sessionRepository;
        this.movieRepository = movieRepository;
    }

    @Transactional
    public SessionResponse registerSession(Long movie_id, SessionRequest sessionRequest){
        List<Session> sessionsByTimeAndRoom = sessionRepository.findByDateAndTimeAndRoom(LocalDate.parse(sessionRequest.day(), DateTimeFormatter.ofPattern("dd/MM/yyyy")), Time.valueOf(sessionRequest.time()),sessionRequest.room());

        if (sessionsByTimeAndRoom.size() > 0){
            throw new SessionIsPresentException("Já existe sessões utilizando a sala " + sessionRequest.room() + " para esse horário");
        }

        Optional<Movie> movie = movieRepository.findById(movie_id);

        if (movie.isEmpty()){
            throw new MovieNotFoundException("Filme não cadastrado");
        }

        Session session = new Session(sessionRequest);
        session.setMovie(movie.get());

        sessionRepository.save(session);

        return session.toSessionResponse(session);
    }

    public List<Session> getAllSessionsByMovie(Long movie_id){
        List<Session> sessions = sessionRepository.findAllByMovie(movie_id);

        if (sessions.size() < 0){
            throw new SessionNotFoundException("Não há sessões registradas");
        }

        return sessions;
    }

    @Transactional
    public SessionResponse updateSession(Long movie_id, Long session_id, SessionRequest sessionRequest ){
        Optional<Session> session = sessionRepository.findById(session_id);
        if (session.isEmpty()){
            throw new SessionNotFoundException("Sessão não registrada");
        }
        if (session.get().getMovie().getId() != movie_id){
            throw new SessionRelatedMovieException("Essa sessão não está relacionado a esse filme");
        }
        BeanUtils.copyProperties(sessionRequest, session.get());
        session.get().setDay(LocalDate.parse(sessionRequest.day(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        session.get().setTime(Time.valueOf(sessionRequest.time()));

        sessionRepository.save(session.get());

        return session.get().toSessionResponse(session.get());
    }

}
