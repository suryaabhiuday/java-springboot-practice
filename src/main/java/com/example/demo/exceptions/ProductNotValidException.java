package com.example.demo.exceptions;

public class ProductNotValidException extends RuntimeException{
    public ProductNotValidException(){
        super(ErrorMessages.SOME_OTHER_ERROR.getMessage());
    }
}
