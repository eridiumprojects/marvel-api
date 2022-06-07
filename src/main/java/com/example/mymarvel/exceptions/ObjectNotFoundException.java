package com.example.mymarvel.exceptions;

public class ObjectNotFoundException extends GeneralException {
    public ObjectNotFoundException() {
        super();
    }
    public ObjectNotFoundException(String message) {
        super(message);
    }
}