package com.nagyzasense.kennel.advice;

import java.time.LocalDateTime;

public record DogNotFoundErrorResponse(LocalDateTime timestamp,
                                       int status,
                                       String message) {
}
