package com.thullyoo.bilheteria_api.exceptions.movie;

public class MovieIsPresentException extends RuntimeException{
    public MovieIsPresentException(String message) {
        super(message);
    }
}
