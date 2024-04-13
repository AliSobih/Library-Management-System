package com.library.exceptions;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class CustomValidationExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<CustomValidationExceptionResponse> emailValidation (ConstraintViolationException e) {
        Map<String, String> errors = new HashMap<>();
        for (ConstraintViolation<?> violation : e.getConstraintViolations()) {
            String propertyPath = violation.getPropertyPath().toString();
            String message = violation.getMessage();
            if (errors.containsKey(propertyPath)) {
                message = errors.get(propertyPath) + ", " + message;
            }
            errors.put(propertyPath, message);
        }
        CustomValidationExceptionResponse apiException = new CustomValidationExceptionResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                errors);
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
