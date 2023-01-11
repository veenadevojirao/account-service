package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account getAccountByAccId(String customerId, String accountId) {
        Account account = new Account();
        Iterable<Account> accnt=accountRepository.findAllById(Arrays.asList(customerId,accountId));

        for (Account acc:accnt){
            account=acc;
        }

        return account;
    }
}

