package com.maveric.accountservice.exception;

public class AccountNotFoundException extends Exception{
    public AccountNotFoundException(String message)
    {
        super(message);
    }
}
