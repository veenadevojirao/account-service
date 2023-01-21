package com.maveric.accountservice.services;

import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_DELETED_SUCCESS;
import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_MESSAGE;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    AccountRepository accountRepository;

    @Override
    public String deleteAccount(String accountId) {
        if(!accountRepository.findById(accountId).isPresent())
        {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId);
        }

        accountRepository.deleteById(accountId);
        return ACCOUNT_DELETED_SUCCESS;
    }


}
