package com.example.warehouse.exception;

public class ResourceNotFoundException extends RuntimeException{
    public ResourceNotFoundException(String e){
        super(e);
    }
}
