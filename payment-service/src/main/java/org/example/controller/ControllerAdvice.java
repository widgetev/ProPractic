package org.example.controller;

import org.example.dto.ErrorResponse;
import org.example.exception.*;
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

    @ExceptionHandler(PaymentParamException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerPaymentParamException(PaymentParamException exception) {
        return new ErrorResponse(HttpStatus.BAD_REQUEST.name() , exception.getMessage());
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorResponse handlerProductNotFoundException(ProductNotFoundException exception) {
        return new ErrorResponse(HttpStatus.NOT_FOUND.name() , exception.getMessage());
    }
    @ExceptionHandler(RequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorResponse handlerRequestException(RequestException exception) {
        return new ErrorResponse(exception.getHttpStatus().name(), exception.getMessage());
    }
}
