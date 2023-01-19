package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;

public interface AccountService {


    public AccountDto createAccount(String customerId, AccountDto accountDto);

//    Object createAccount(T any);
}

