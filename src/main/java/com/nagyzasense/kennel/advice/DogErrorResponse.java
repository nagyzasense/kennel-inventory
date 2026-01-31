package com.nagyzasense.kennel.advice;

import java.time.LocalDateTime;

public record DogErrorResponse(LocalDateTime timestamp,
                               int status,
                               String message) {
}
