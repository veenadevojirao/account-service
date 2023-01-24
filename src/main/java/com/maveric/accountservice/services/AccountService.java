package com.maveric.accountservice.services;


import com.maveric.accountservice.dto.AccountDto;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import net.bytebuddy.matcher.ElementMatcher;

import org.springframework.stereotype.Service;

@Service


public interface AccountService {
    //    AccountDto updateAccount(String customerId, String accountId, Account accountDto);
//public AccountDto updateAccountDetails(String customerId, String accountId, AccountDto accountDto);
    Account updateAccount(String customerId, String accountId, Account account) throws CustomerIDNotFoundExistsException;

    Object updateAccount(Object any);

//    Account deleteAccount(String accountId);


    public String deleteAccount(String accountId);


    public AccountDto createAccount(String customerId, AccountDto accountDto);


}


