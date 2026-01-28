package com.nagyzasense.kennel.advice;

import java.time.LocalDateTime;
import java.util.List;

public record ValidationErrorResponse(
        LocalDateTime timestamp,
        int status,
        String message,
        List<FieldErrorDetail> errors
) {
    public record FieldErrorDetail(String field, String message) {}
}
