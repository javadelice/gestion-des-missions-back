package dev.controller;

import dev.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

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

    @ExceptionHandler(value = NatureUtiliseeException.class)
    protected ResponseEntity<Object> handleConflict(NatureUtiliseeException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La nature est utilisée dans des missions en cours ou à venir, et ne peux donc pas être suprimée");
    }

    @ExceptionHandler(value = NatureIntrouvableException.class)
    protected ResponseEntity<Object> handleConflict(NatureIntrouvableException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("La nature à supprimer est introuvable");
    }
}
