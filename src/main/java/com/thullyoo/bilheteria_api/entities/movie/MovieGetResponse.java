package com.thullyoo.bilheteria_api.entities.movie;

import com.thullyoo.bilheteria_api.entities.session.Session;


import java.util.Set;

public record MovieGetResponse(Long id, String image, String name, String description, Set<String> actors, String genre, String release_date, Set<Session> sessions) {

}
