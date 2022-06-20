package com.example.mymarvel.exceptions;

public class ObjectAlreadyExistException extends GeneralException {
    public ObjectAlreadyExistException() {
        super();
    }

    public ObjectAlreadyExistException(String message) {
        super(message);
    }
}
