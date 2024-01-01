package dev.careeropz.cvmanagerservice.controller;

import dev.careeropz.cvmanagerservice.exception.IncorrectRequestDataException;
import dev.careeropz.cvmanagerservice.exception.ResourceNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
@RestControllerAdvice
@Slf4j
public class CustomControllerAdvice {

    @ExceptionHandler(ResourceNotFoundException.class)
    ResponseEntity<String> resourceNotFoundHandler(ResourceNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    @ExceptionHandler(IncorrectRequestDataException.class)
    ResponseEntity<String> incorrectRequestDataHandler(IncorrectRequestDataException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleException(Exception ex) {

        log.error("An unexpected error occurred", ex);
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
    }
}
