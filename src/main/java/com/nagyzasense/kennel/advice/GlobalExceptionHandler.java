package com.nagyzasense.kennel.advice;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
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
    private static final String THE_BREED_REQUEST_PARAMETER_IS_MANDATORY = "The 'breed' request parameter is mandatory";

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
                HttpStatus.BAD_REQUEST.value(),
                VALIDATION_FAILED,
                errors
        );

        return ResponseEntity.badRequest().body(response);
    }

    @ExceptionHandler(DogNotFoundException.class)
    public ResponseEntity<DogErrorResponse> handleDogNotFoundExceptions(DogNotFoundException e) {
        DogErrorResponse response = new DogErrorResponse(
                LocalDateTime.now(),
                HttpStatus.NOT_FOUND.value(),
                e.getMessage()
        );
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    public ResponseEntity<DogErrorResponse> handleMissingServletRequestParameterException(MissingServletRequestParameterException e) {
        DogErrorResponse response = new DogErrorResponse(
                LocalDateTime.now(),
                HttpStatus.BAD_REQUEST.value(),
                THE_BREED_REQUEST_PARAMETER_IS_MANDATORY
        );
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}