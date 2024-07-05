package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.movie.MovieGetResponse;
import com.thullyoo.bilheteria_api.entities.movie.MovieRequest;
import com.thullyoo.bilheteria_api.entities.movie.MovieResponse;
import com.thullyoo.bilheteria_api.services.MovieService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping("/movies")
    public ResponseEntity<List<MovieGetResponse>> getAllMovies(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieGetResponse> getMovie(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(id));
    }

    @PutMapping("/movies/{id}")
    public ResponseEntity<MovieResponse> putMovie(@PathVariable("id") Long id, @RequestBody MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.putMovie(id, movieRequest));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
