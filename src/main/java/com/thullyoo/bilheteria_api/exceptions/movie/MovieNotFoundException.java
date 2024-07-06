package com.thullyoo.bilheteria_api.exceptions.movie;

public class MovieNotFoundException extends RuntimeException{
    public MovieNotFoundException(String message) {
        super(message);
    }
}
