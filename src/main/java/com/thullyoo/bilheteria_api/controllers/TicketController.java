package com.thullyoo.bilheteria_api.controllers;

import com.thullyoo.bilheteria_api.entities.ticket.TicketRequest;
import com.thullyoo.bilheteria_api.entities.ticket.TicketResponse;
import com.thullyoo.bilheteria_api.services.TicketService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TicketController {

    private final TicketService ticketService;

    public TicketController(TicketService ticketService) {
        this.ticketService = ticketService;
    }

    @PostMapping("/movies/{movie_id}/sessions/{session_id}/tickets")
    public ResponseEntity<TicketResponse> registerTicket(@PathVariable("session_id") Long session_id, @RequestBody TicketRequest ticketRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(ticketService.registerTicket(session_id, ticketRequest));
    }
}
