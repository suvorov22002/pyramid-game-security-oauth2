package com.pyramid.tech.core.exception;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

/**
 * Created by Suvorov Vassilievitch
 * Date: 17/01/2025
 * Time: 14:13
 * Project Name: pyramid-game-security-oauth2
 */

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionDetails> handleGeneralExceptions(Exception ex) {
        return new ResponseEntity<>(getExceptionDetailsObject(ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleNotFoundExceptions(EntityNotFoundException ex) {
        return new ResponseEntity<>(getExceptionDetailsObject(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity<ExceptionDetails> handleUsernameNotFoundExceptions(UsernameNotFoundException ex) {
        return new ResponseEntity<>(getExceptionDetailsObject(ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    private ExceptionDetails getExceptionDetailsObject(String message) {

        return ExceptionDetails.builder()
                .message(message)
                .dateTime(LocalDateTime.now())
                .build();
    }
}
