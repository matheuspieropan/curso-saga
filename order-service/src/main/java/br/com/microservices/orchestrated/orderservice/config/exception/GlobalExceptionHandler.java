package br.com.microservices.orchestrated.orderservice.config.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ValidationException.class)
    public ResponseEntity<ExceptionDetails> validationException(ValidationException ex) {

        var details = new ExceptionDetails(HttpStatus.BAD_REQUEST.value(), ex.getMessage());
        return new ResponseEntity<>(details, HttpStatus.BAD_REQUEST);
    }
}