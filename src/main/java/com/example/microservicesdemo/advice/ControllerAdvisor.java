package com.example.microservicesdemo.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.badRequest().body(getErrorMap(status, e));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        ;
        return ResponseEntity.status(status).body(getErrorMap(status, e));
    }

    private Map<String, String> getErrorMap(HttpStatus status, Exception e) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", Integer.toString(status.value()));
        body.put("error", status.getReasonPhrase());
        body.put("message", e.getMessage());
        return body;
    }
}
