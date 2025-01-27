package org.ejatohvee.tasktrackerapi.controllers.advice;

import org.ejatohvee.tasktrackerapi.exceptions.ApiError;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.NoSuchElementException;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalControllerAdvice {

    @ExceptionHandler
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException e) {
        return ResponseEntity.badRequest().body("Not found: " + e.getMessage());
    }

    @ExceptionHandler
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body("Invalid input: " + e.getMessage());
    }

    @ExceptionHandler(value = BadCredentialsException.class)
    public ResponseEntity<ApiError> handleBadCredentialsException() {
        return new ResponseEntity<>(new ApiError(HttpStatus.UNAUTHORIZED.value(), "Wrong login or password"), HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        String message;

        if (e.getCause() instanceof org.hibernate.exception.ConstraintViolationException constraintViolationException) {
            message = "Value in " + constraintViolationException.getConstraintName() + " is not unique.";
        }
        else message = e.getMessage();
        return ResponseEntity.badRequest().body("Database error: " + message);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleValidationExceptions(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining(" "));
        return ResponseEntity.badRequest().body("Validation errors: " + errorMessage);
    }

    @ExceptionHandler
    public ResponseEntity<String> handleGeneralException(Exception e) {
        return ResponseEntity.badRequest().body("Unexpected error: " + e.getMessage());
    }
}
