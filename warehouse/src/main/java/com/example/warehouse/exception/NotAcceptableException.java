package com.example.warehouse.exception;

public class NotAcceptableException extends RuntimeException{

    public NotAcceptableException(String e){
        super(e);
    }
    public NotAcceptableException(){
        super();
    }
}
