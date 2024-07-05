package com.thullyoo.bilheteria_api.entities.session;

public record SessionRequest(String room, Integer capacity, String day, String time) {
}
