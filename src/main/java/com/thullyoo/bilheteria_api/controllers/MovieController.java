package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.movie.MovieRequest;
import com.thullyoo.bilheteria_api.entities.movie.MovieResponse;
import com.thullyoo.bilheteria_api.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> registerMovie(@RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.registerMovie(movieRequest));
    }
}
