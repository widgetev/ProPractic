package org.example.task_4.controller;

import org.example.task_4.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerRuntimeException(RuntimeException exception) {
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name() , exception.getMessage());
    }
}
