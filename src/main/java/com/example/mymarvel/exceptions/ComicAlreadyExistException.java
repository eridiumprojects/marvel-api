package com.example.mymarvel.exceptions;

public class ComicAlreadyExistException extends ObjectAlreadyExistException {
    public ComicAlreadyExistException(String message) {
        super(message);
    }

    public ComicAlreadyExistException() {
        super();
    }
}
