package com.example.microservicesdemo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class InvalidCredentialsException extends ResponseStatusException {

    private static final HttpStatus HTTP_STATUS = HttpStatus.UNAUTHORIZED;
    private static final String MESSAGE = "Invalid credentials provided";

    public InvalidCredentialsException() {
        super(HTTP_STATUS, MESSAGE);
    }
}
