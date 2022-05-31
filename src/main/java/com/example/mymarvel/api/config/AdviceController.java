package com.example.mymarvel.api.config;

import com.example.mymarvel.api.exceptions.ObjectNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = {ObjectNotFoundException.class})
    protected ResponseEntity<Object> handleConflict(ObjectNotFoundException ex) {
        return new ResponseEntity(ex.getMessage(),HttpStatus.NOT_FOUND);
    }
}
