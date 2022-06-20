package com.example.mymarvel.exceptions;

public class CharacterNotFoundException extends ObjectNotFoundException {
    public CharacterNotFoundException() {
        super();
    }

    public CharacterNotFoundException(String message) {
        super(message);
    }
}
