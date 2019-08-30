package dev.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import dev.exception.MissionInvalideException;
import dev.exception.NatureInvalideException;
import dev.exception.MissionNonTrouveException;

@ControllerAdvice
public class RestResponseEntityExceptionHandler {

    @ExceptionHandler(value = MissionInvalideException.class)
    protected ResponseEntity<Object> handleConflict(MissionInvalideException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }


    @ExceptionHandler(value = NatureInvalideException.class)
    protected ResponseEntity<Object> handleConflict(NatureInvalideException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(value = MissionNonTrouveException.class)
    protected ResponseEntity<Object> handleConflict(MissionNonTrouveException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

}
