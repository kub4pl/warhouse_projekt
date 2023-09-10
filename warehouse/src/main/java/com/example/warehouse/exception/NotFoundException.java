package com.example.warehouse.exception;

public class NotFoundException extends RuntimeException {

    public NotFoundException(String e){
        super(e);
    }
    public NotFoundException(){
        super();
    }
}
