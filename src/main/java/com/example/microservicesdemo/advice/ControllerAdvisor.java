package com.example.microservicesdemo.advice;

import com.example.microservicesdemo.entity.Account;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.ws.rs.NotFoundException;
import java.time.Instant;
import java.util.*;

@RestControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = IllegalArgumentException.class)
    public ResponseEntity<Object> handleIllegalArgumentException(IllegalArgumentException e) {
        HttpStatus status = HttpStatus.BAD_REQUEST;
        return ResponseEntity.badRequest().body(getErrorMap(status, e.getMessage()));
    }

    @ExceptionHandler(value = NotFoundException.class)
    public ResponseEntity<Object> handleNotFoundException(NotFoundException e) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        return ResponseEntity.status(status).body(getErrorMap(status, e.getMessage()));
    }

    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        HttpStatus status = HttpStatus.CONFLICT;
        List<String> messages = new ArrayList<>();
        if (Objects.requireNonNull(e.getMessage()).contains(Account.LOGIN_CONSTRAINT_NAME)) {
            messages.add("Login already exists");
        } else {
            messages.add("Data integrity violation");
        }

        return ResponseEntity.status(status).body(getErrorMap(status, messages));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers, HttpStatus status, WebRequest request) {

        List<String> messages = e.getAllErrors().stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .toList();
        return ResponseEntity.status(status).body(getErrorMap(status, String.valueOf(messages)));
    }

    private Map<String, String> getErrorMap(HttpStatus status, String message) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", Integer.toString(status.value()));
        body.put("error", status.getReasonPhrase());
        body.put("message", message);
        return body;
    }

    private Map<String, String> getErrorMap(HttpStatus status, List<String> messages) {
        Map<String, String> body = new HashMap<>();
        body.put("timestamp", Instant.now().toString());
        body.put("status", Integer.toString(status.value()));
        body.put("error", status.getReasonPhrase());
        messages.forEach(message -> body.put("message", message));
        return body;
    }
}
