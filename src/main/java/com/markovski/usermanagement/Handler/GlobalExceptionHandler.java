package com.markovski.usermanagement.Handler;

import com.markovski.usermanagement.Exception.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException ex) {
        HttpStatus status = HttpStatus.NOT_FOUND;
        String errorMessage = ex.getMessage();
        return ResponseEntity.status(status).body(errorMessage);
    }
}
