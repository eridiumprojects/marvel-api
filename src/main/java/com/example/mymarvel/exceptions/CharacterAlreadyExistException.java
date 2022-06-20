package com.example.mymarvel.exceptions;

public class CharacterAlreadyExistException extends ObjectAlreadyExistException {
    public CharacterAlreadyExistException(String message) {
        super(message);
    }

    public CharacterAlreadyExistException() {
        super();
    }
}
