package com.example.mymarvel.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class ObjectNotFoundException extends RuntimeException {
    public ObjectNotFoundException() {
        super();
    }
    public ObjectNotFoundException(String message) {
        super(message);
    }
}