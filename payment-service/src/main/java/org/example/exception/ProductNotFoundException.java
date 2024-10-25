package org.example.exception;

import lombok.Getter;

@Getter
public class ProductNotFoundException extends RuntimeException{
    String message;

    public ProductNotFoundException(String message) {
        super(message);
        this.message = message;
    }

}
