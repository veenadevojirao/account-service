package com.maveric.accountservice.exception;

public class CustomerIdMissmatch extends RuntimeException{
    public CustomerIdMissmatch(String message){
        super(message);
    }
}