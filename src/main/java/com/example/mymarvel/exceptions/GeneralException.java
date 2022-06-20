package com.example.mymarvel.exceptions;

public class GeneralException extends RuntimeException {
    public GeneralException(String message) {
        super(message);
    }

    public GeneralException() {
        super();
    }
}
