package org.example.exception;

import lombok.Getter;

@Getter
public class PaymentParamException extends RuntimeException{
    String message;
    public PaymentParamException(String message) {
        super(message);
        this.message = message;
    }

}
