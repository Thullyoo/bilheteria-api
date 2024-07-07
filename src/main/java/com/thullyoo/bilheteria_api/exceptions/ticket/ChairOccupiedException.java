package com.thullyoo.bilheteria_api.exceptions.ticket;

public class ChairOccupiedException extends RuntimeException{
    public ChairOccupiedException(String message) {
        super(message);
    }
}
