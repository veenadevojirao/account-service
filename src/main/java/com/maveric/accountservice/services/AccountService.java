package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service

public interface AccountService {
    Optional<Account> getAccountByAccId(String customerId, String accountId) throws AccountNotFoundException;
//    Optional<Account> getAccountByCusId(String customerId, String accountId) throws AccountIDNotfoundException;
}
