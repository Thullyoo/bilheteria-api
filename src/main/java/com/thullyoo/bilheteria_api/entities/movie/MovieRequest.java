package com.thullyoo.bilheteria_api.entities.movie;

import java.util.List;

public record MovieRequest(String image, String name, String description, List<String> actors, String genre, String release_date) {
}
