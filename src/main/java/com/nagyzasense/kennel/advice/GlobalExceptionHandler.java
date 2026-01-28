package com.nagyzasense.kennel.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.ResponseEntity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.nagyzasense.kennel.exception.DogNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final String VALIDATION_FAILED = "Validation Failed";

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationExceptions(MethodArgumentNotValidException ex) {

        List<ValidationErrorResponse.FieldErrorDetail> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(error -> new ValidationErrorResponse.FieldErrorDetail(
                        error.getField(),
                        error.getDefaultMessage()))
                .collect(Collectors.toList());

        ValidationErrorResponse response = new ValidationErrorResponse(
                LocalDateTime.now(),
                400,
                VALIDATION_FAILED,
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<DogNotFoundErrorResponse> handleDogNotFoundExceptions(DogNotFoundException e) {
        DogNotFoundErrorResponse response = new DogNotFoundErrorResponse(
                LocalDateTime.now(),
                404,
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }
}