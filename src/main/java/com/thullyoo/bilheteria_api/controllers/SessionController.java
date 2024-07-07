package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.session.Session;
import com.thullyoo.bilheteria_api.entities.session.SessionRequest;
import com.thullyoo.bilheteria_api.entities.session.SessionResponse;
import com.thullyoo.bilheteria_api.exceptions.global.ExceptionResponse;
import com.thullyoo.bilheteria_api.services.SessionService;
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
@Tag(name = "session-api")
public class SessionController {

    private final SessionService sessionService;

    public SessionController(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Operation(summary = "Atualiza uma sessão", method = "PUT")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão atualizada com sucesso.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SessionResponse.class))),
            @ApiResponse(responseCode = "404", description = "Sessão não registrada.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409" , description = "Sessão não relacionada ao filme.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na validação dos campos necessários.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PutMapping("/movies/{movie_id}/sessions/{session_id}")
    public ResponseEntity<SessionResponse> updateSession(@PathVariable("movie_id") Long movie_id, @PathVariable("session_id") Long session_id, @RequestBody @Valid SessionRequest sessionRequest){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.updateSession(movie_id, session_id,sessionRequest));
    }

    @Operation(summary = "Registra uma sessão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Sessão registrada com sucesso.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = SessionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Sessão já registrada.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "404" , description = "Filme não registrado.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na validação dos campos necessários.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/movies/{movie_id}/sessions")
    public ResponseEntity<SessionResponse> registrerSession(@PathVariable("movie_id") Long movie_id, @RequestBody @Valid SessionRequest sessionRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(sessionService.registerSession(movie_id,sessionRequest));
    }

    @Operation(summary = "Registra uma sessão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Sessão resgatada com sucesso.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = List.class, subTypes = {Session.class}))),
            @ApiResponse(responseCode = "404" , description = "Não há sessões registradas.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @GetMapping("/movies/{movie_id}/sessions")
    public ResponseEntity<List<Session>> getAllSessionsByMovie(@PathVariable("movie_id") Long movie_id){
        return ResponseEntity.status(HttpStatus.OK).body(sessionService.getAllSessionsByMovie(movie_id));
    }

}
