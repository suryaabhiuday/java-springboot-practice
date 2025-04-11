package com.example.demo.exceptions;

public enum ErrorMessages {
    PRODUCT_NOT_FOUND("Product not found."),
    SOME_OTHER_ERROR("I DONT KNOW WHY THIS ERROR EXISTS");
    
    private final String message;

    ErrorMessages (String message){
        this.message = message;
    }

    public String getMessage(){
        return message;
    }
}
