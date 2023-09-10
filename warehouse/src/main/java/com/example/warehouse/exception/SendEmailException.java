package com.example.warehouse.exception;

public class SendEmailException extends RuntimeException {
    public SendEmailException(String e) {
        super(e);
    }
}
