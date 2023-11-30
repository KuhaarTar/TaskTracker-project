package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.exceptions.PasswordsDoesNotMatchException;
import com.kuhar.tasktracker.exceptions.ProjectException;
import com.kuhar.tasktracker.exceptions.TokenException;
import com.kuhar.tasktracker.responses.ExceptionResponse;
import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.ConstraintViolationException;
import jakarta.validation.ValidationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    private static final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;
    private static final HttpStatus NOT_FOUND = HttpStatus.NOT_FOUND;

    @org.springframework.web.bind.annotation.ExceptionHandler(value = UsernameNotFoundException.class)
    public ResponseEntity<Object> exceptionHandle(UsernameNotFoundException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(NOT_FOUND)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = EntityNotFoundException.class)
    public ResponseEntity<Object> exceptionHandle(EntityNotFoundException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(NOT_FOUND)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        return new ResponseEntity<>(exceptionResponse, NOT_FOUND);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            TokenException.class,
            PasswordsDoesNotMatchException.class})
    public ResponseEntity<Object> exceptionHandle(ProjectException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {
            ConstraintViolationException.class})
    public ResponseEntity<Object> exceptionHandle(ValidationException e) {
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(BAD_REQUEST)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        return new ResponseEntity<>(exceptionResponse, BAD_REQUEST);
    }
}
