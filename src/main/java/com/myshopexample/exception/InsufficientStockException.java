package com.myshopexample.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class InsufficientStockException extends RuntimeException{
    public InsufficientStockException(String err){
        super(err);
    }
}
