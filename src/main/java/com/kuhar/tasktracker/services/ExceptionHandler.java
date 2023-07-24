package com.kuhar.tasktracker.services;

import com.kuhar.tasktracker.exceptions.NoDeadlinesException;
import com.kuhar.tasktracker.exceptions.ProjectException;
import com.kuhar.tasktracker.responses.ExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = {NoDeadlinesException.class})
    public ResponseEntity<Object> handleException(ProjectException e) {
        HttpStatus badRequest = HttpStatus.BAD_REQUEST;
        ExceptionResponse exceptionResponse = ExceptionResponse.builder()
                .message(e.getMessage())
                .httpStatus(badRequest)
                .timestamp(ZonedDateTime.now(ZoneId.systemDefault()))
                .build();
        return new ResponseEntity<>(exceptionResponse,badRequest);
    }
}
