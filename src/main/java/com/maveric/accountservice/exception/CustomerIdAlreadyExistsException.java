package com.maveric.accountservice.exception;

public class CustomerIdAlreadyExistsException extends RuntimeException {


    public CustomerIdAlreadyExistsException(String message)
        {
            super(message);
        }
    }

