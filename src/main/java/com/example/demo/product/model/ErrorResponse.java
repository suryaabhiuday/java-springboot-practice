package com.example.demo.product.model;

import lombok.Getter;

@Getter
public class ErrorResponse {
    private String message;
    private Throwable cause;

    public ErrorResponse(String message, Throwable cause){
        this.message = message;
        this.cause = cause;
    }
}
