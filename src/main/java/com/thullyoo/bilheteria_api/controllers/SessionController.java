package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.session.SessionRequest;
import com.thullyoo.bilheteria_api.entities.session.SessionResponse;
import com.thullyoo.bilheteria_api.services.SessionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }


    @PostMapping("/movies/{movie_id}/sessions")
    public ResponseEntity<SessionResponse> registrerSession(@PathVariable("movie_id") Long movie_id, @RequestBody SessionRequest sessionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.registerSession(movie_id,sessionRequest));
    }

}
