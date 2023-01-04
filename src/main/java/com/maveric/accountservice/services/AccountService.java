package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
public class AccountService {
    @Autowired
    private AccountRepository accountRepository;

    public Account createAccount(String customerId, Account account)
    {
        return accountRepository.save(account);
    }
}
