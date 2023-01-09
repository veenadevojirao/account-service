package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository repository;

    @Override
    public AccountDto updateAccount(String customerId, String accountId, Account accountDto) {

        AccountDto account = repository.findById(accountId).get();


        account.setType(accountDto.getType());


        account.setCustomerId(accountDto.getCustomerId());


        return repository.save(account);


    }
}

