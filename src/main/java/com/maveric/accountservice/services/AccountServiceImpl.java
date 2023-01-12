package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public Optional<Account> getAccountByAccId(String customerId, String accountId) throws AccountNotFoundException {

        if (accountRepository.findById(accountId).isPresent()) {
            Account account = new Account();
            Iterable<Account> accnt = accountRepository.findAllById(Arrays.asList(customerId, accountId));
            return accountRepository.findById(accountId) ;
        }

        //for (Account acc:accnt){
           // account=acc;
        //}
        else {
            throw new AccountNotFoundException("Account details not found");
        }



    }
}










