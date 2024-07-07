package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.movie.MovieGetResponse;
import com.thullyoo.bilheteria_api.entities.movie.MovieRequest;
import com.thullyoo.bilheteria_api.entities.movie.MovieResponse;
import com.thullyoo.bilheteria_api.exceptions.global.ExceptionResponse;
import com.thullyoo.bilheteria_api.services.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping( produces = {"application/json"})
@Tag(name = "movie-api")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @Operation(summary = "Registra filmes", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Filme resgistrado com sucesso.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = "422", description = "Filme já registrado.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "411", description = "Número inválido de caracteres na descrição.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na validação dos campos necessários.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> registerMovie(@RequestBody @Valid MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.registerMovie(movieRequest));
    }


    @Operation(summary = "Retorna uma lista de todos os filmes registrados", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Lista de filmes retornada com sucesso.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class, subTypes = { MovieGetResponse.class }))),
            @ApiResponse(responseCode = "404", description = "Não há filmes registrados.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/movies")
    public ResponseEntity<List<MovieGetResponse>> getAllMovies(){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies());
    }

    @Operation(summary = "Retorna um filme pelo seu ID", method = "GET")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme retornado com sucesso.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = MovieGetResponse.class))),
            @ApiResponse(responseCode = "404", description = "Esse filme registrado não está registrado.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieGetResponse> getMovie(@PathVariable("id") Long id){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovie(id));
    }

    @Operation(summary = "Atualiza dados de um filme", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Filme atualizado com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = MovieResponse.class))),
            @ApiResponse(responseCode = "404", description = "Esse filme registrado não está registrado.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na validação dos campos necessários.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/movies/{id}")
    public ResponseEntity<MovieResponse> putMovie(@PathVariable("id") Long id, @RequestBody @Valid MovieRequest movieRequest){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.putMovie(id, movieRequest));
    }

    @Operation(summary = "Remove um filme registrado", method = "DELETE")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Filme removido com sucesso.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = void.class))),
            @ApiResponse(responseCode = "404", description = "Esse filme registrado não está registrado.",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "400", description = "Não é possível remover filme por causa que há sessões existentes.",
                    content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable("id") Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
