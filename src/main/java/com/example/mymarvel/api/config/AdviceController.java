package com.example.mymarvel.api.config;

import com.example.mymarvel.api.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    public ResponseEntity<Object> handleConflict(String message) {
        return new ResponseEntity<>(message,HttpStatus.NOT_FOUND);
    }
}
