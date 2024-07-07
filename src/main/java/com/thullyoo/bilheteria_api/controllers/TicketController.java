package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.ticket.TicketRequest;
import com.thullyoo.bilheteria_api.entities.ticket.TicketResponse;
import com.thullyoo.bilheteria_api.exceptions.global.ExceptionResponse;
import com.thullyoo.bilheteria_api.services.TicketService;
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

@RestController
@RequestMapping( produces = {"application/json"})
@Tag(name = "ticket-api")
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @Operation(summary = "Registra uma sessão", method = "POST")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ticket registrado com sucesso", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = TicketResponse.class))),
            @ApiResponse(responseCode = "404" , description = "Sessão não registrada.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "409" , description = "Cadeira já ocupada.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class))),
            @ApiResponse(responseCode = "422", description = "Erro na validação dos campos necessários.", content = @Content(mediaType = "application/json",
                    schema = @Schema(implementation = ExceptionResponse.class)))
    })
    @PostMapping("/movies/{movie_id}/sessions/{session_id}/tickets")
    public ResponseEntity<TicketResponse> registerTicket(@PathVariable("session_id") Long session_id, @RequestBody @Valid TicketRequest ticketRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.registerTicket(session_id, ticketRequest));
    }
}
