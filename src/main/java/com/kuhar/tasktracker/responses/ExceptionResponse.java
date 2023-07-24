package com.kuhar.tasktracker.responses;

import com.kuhar.tasktracker.exceptions.ProjectException;
import lombok.Builder;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Getter
@Setter
@Builder
@RequiredArgsConstructor
public class ExceptionResponse {
    private final String message;
    private final HttpStatus httpStatus;
    private final ZonedDateTime timestamp;
}
