package com.example.mymarvel.exceptions;

public class ComicNotFoundException extends ObjectNotFoundException {
    public ComicNotFoundException() {
        super();
    }

    public ComicNotFoundException(String message) {
        super(message);
    }
}