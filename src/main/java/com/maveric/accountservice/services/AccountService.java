package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIdAlreadyExistsException;

public interface AccountService{
    Account createAccount(Account account) throws CustomerIdAlreadyExistsException;
}