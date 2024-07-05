package com.thullyoo.bilheteria_api.entities.session;

public record SessionResponse(Long id, Long movie_id, String room, Integer capacity, String day, String time) {
}
