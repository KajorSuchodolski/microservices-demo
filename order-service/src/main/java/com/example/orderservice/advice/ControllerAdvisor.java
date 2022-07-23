package com.example.orderservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.handler.ResponseStatusExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseStatusExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.status(status).body(getErrorMap(status, e.getMessage()));
    }

    private Map<String, String> getErrorMap(HttpStatus status, String message) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", Integer.toString(status.value()));
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }
}
