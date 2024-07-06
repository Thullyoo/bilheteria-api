package com.thullyoo.bilheteria_api.entities.ticket;

public record TicketResponse(Long id, Long session_id, String chair, Double value) {
}
