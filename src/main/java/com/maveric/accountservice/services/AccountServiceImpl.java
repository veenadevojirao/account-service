package com.maveric.accountservice.services;

import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;
    @Override
    public String deleteAccount(String customerId, String accountId) {
        accountRepository.deleteById(accountId);
        return "Account deleted successfully.";
    }
}

