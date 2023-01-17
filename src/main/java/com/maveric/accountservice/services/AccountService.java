package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import org.springframework.stereotype.Service;

@Service

public interface AccountService {
    //    AccountDto updateAccount(String customerId, String accountId, Account accountDto);
//public AccountDto updateAccountDetails(String customerId, String accountId, AccountDto accountDto);
    Account updateAccount(String customerId, String accountId, Account account) throws CustomerIDNotFoundExistsException;
}