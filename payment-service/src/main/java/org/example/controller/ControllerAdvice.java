package org.example.controller;

import org.example.dto.ErrorResponse;
import org.example.exception.Exception5xxDTO;
import org.example.exception.IntegrationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.net.URI;

@RestControllerAdvice
public class ControllerAdvice {
    @ExceptionHandler(IntegrationException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorResponse handlerIntegrtionException(IntegrationException exception) {
        Exception5xxDTO errDTO = exception.getException5xxDTO();
        String message = errDTO.getError() +  " for path = " + errDTO.getPath();
        return new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.name() , message);
    }
}
