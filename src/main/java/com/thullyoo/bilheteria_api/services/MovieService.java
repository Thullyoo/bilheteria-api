package com.thullyoo.bilheteria_api.services;

import com.thullyoo.bilheteria_api.entities.movie.Movie;
import com.thullyoo.bilheteria_api.entities.movie.MovieRequest;
import com.thullyoo.bilheteria_api.entities.movie.MovieResponse;
import com.thullyoo.bilheteria_api.repositories.MovieRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    public MovieResponse registerMovie(MovieRequest movieRequest){
        Optional<Movie> optionalMovie = movieRepository.findByName(movieRequest.name());

        if (optionalMovie.isPresent()){
            throw new RuntimeException("O filme " + movieRequest.name() + " já está cadastrado");
        }

        if (movieRequest.description().length() > 100){
            throw new RuntimeException("A descrição do filme não pode passar de 100 caracteres");
        }

        Movie movie = movieRepository.save(new Movie(movieRequest));

        return movie.toMovieResponse(movie);
    }
}
