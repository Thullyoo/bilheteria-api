package com.thullyoo.bilheteria_api.exceptions.session;

public class SessionNotFoundException extends RuntimeException{
    public SessionNotFoundException(String message) {
        super(message);
    }
}
