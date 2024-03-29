package com.kuhar.tasktracker.controllers.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ExceptionResponse {
    private String message;
    private HttpStatus httpStatus;
    private ZonedDateTime timestamp;
}
