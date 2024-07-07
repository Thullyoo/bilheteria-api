package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.session.Session;
import com.thullyoo.bilheteria_api.entities.session.SessionRequest;
import com.thullyoo.bilheteria_api.entities.session.SessionResponse;
import com.thullyoo.bilheteria_api.services.SessionService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @PutMapping("/movies/{movie_id}/sessions/{session_id}")
    public ResponseEntity<SessionResponse> updateSession(@PathVariable("movie_id") Long movie_id, @PathVariable("session_id") Long session_id, @RequestBody @Valid SessionRequest sessionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.updateSession(movie_id, session_id,sessionRequest));
    }

    @PostMapping("/movies/{movie_id}/sessions")
    public ResponseEntity<SessionResponse> registrerSession(@PathVariable("movie_id") Long movie_id, @RequestBody @Valid SessionRequest sessionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.registerSession(movie_id,sessionRequest));
    }

    @GetMapping("/movies/{movie_id}/sessions")
    public ResponseEntity<List<Session>> getAllSessionsByMovie(@PathVariable("movie_id") Long movie_id){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getAllSessionsByMovie(movie_id));
    }

}
