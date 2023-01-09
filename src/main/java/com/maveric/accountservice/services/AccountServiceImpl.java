package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {

    @Autowired
    AccountRepository repository;

    @Override
    public String deleteAccount(String customerId, String accountId) {
        repository.deleteById(accountId);
        return "Account deleted successfully.";

    }

    private AccountDto getAccountByAccId(String customerId, String accountId) {
        return AccountDto.builder().build();
    }


}


