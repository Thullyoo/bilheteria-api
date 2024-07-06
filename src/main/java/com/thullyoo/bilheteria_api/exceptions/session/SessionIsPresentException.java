package com.thullyoo.bilheteria_api.exceptions.session;

public class SessionIsPresentException extends RuntimeException {
    public SessionIsPresentException(String message) {
        super(message);
    }
}
