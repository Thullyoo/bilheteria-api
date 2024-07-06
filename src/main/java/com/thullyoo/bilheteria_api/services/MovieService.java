package com.thullyoo.bilheteria_api.services;

import com.thullyoo.bilheteria_api.entities.movie.Movie;
import com.thullyoo.bilheteria_api.entities.movie.MovieGetResponse;
import com.thullyoo.bilheteria_api.entities.movie.MovieRequest;
import com.thullyoo.bilheteria_api.entities.movie.MovieResponse;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieDeleteException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieDescriptionException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieIsPresentException;
import com.thullyoo.bilheteria_api.exceptions.movie.MovieNotFoundException;
import com.thullyoo.bilheteria_api.repositories.MovieRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovieService {

    private final MovieRepository movieRepository;

    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }

    @Transactional
    public MovieResponse registerMovie(MovieRequest movieRequest){
        Optional<Movie> optionalMovie = movieRepository.findByName(movieRequest.name());

        if (optionalMovie.isPresent()){
            throw new MovieIsPresentException("O filme " + movieRequest.name() + " já está cadastrado");
        }

        if (movieRequest.description().length() > 100){
            throw new MovieDescriptionException("A descrição do filme não pode passar de 100 caracteres");
        }

        Movie movie = movieRepository.save(new Movie(movieRequest));

        return movie.toMovieResponse(movie);
    }

    public List<MovieGetResponse> getAllMovies(){
        List<Movie> movieList = movieRepository.findAll();

        if (movieList.isEmpty()){
            throw new MovieNotFoundException("Não existe nenhum filme registrado");
        }

        List<MovieGetResponse> movieGetResponse = movieList.stream().map(movie -> movie.toMovieGetResponse(movie)).toList();

        return movieGetResponse;

    }

    public MovieGetResponse getMovie(Long id){
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()){
            throw new MovieNotFoundException("Filme não registrado");
        }

        return movie.get().toMovieGetResponse(movie.get());
    }

    public MovieResponse putMovie(Long id, MovieRequest movieRequest){
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()){
            throw new MovieNotFoundException("Filme não registrado");
        }

        BeanUtils.copyProperties(movieRequest, movie.get());

        movie.get().setRelease_date(LocalDate.parse(movieRequest.release_date(), DateTimeFormatter.ofPattern("dd/MM/yyyy")));
        movie.get().setActors(movieRequest.actors().stream().collect(Collectors.toSet()));
        movieRepository.save(movie.get());

        return movie.get().toMovieResponse(movie.get());

    }

    public void deleteMovie(Long id){
        Optional<Movie> movie = movieRepository.findById(id);

        if (movie.isEmpty()){
            throw new MovieNotFoundException("Filme não registrado");
        }
        if (movie.get().getSessions().size() > 0){
            throw new MovieDeleteException("Não é possível excluir o filme " + movie.get().getName() + " pois há sessões disponíveis.");
        }

        movieRepository.deleteById(id);
    }
}
