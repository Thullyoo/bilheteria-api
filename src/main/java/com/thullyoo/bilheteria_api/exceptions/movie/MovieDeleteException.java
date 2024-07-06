package com.thullyoo.bilheteria_api.exceptions.movie;

public class MovieDeleteException extends RuntimeException{
    public MovieDeleteException(String message) {
        super(message);
    }
}
