package com.example.mymarvel.api.exceptions;

public class CharacterNotFoundException extends ObjectNotFoundException {
   public CharacterNotFoundException() {
       super();
   }
   public CharacterNotFoundException(String message) {
       super(message);
   }
}
