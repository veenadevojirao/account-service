package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIdAlreadyExistsException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account createAccount(Account account) throws CustomerIdAlreadyExistsException {
        Account existingAccount
                = accountRepository.findById(account.get_id())
                .orElse(null);
        if (existingAccount == null) {
           return accountRepository.save(account);
//            return "Customer added successfully";
        }
        else
            throw new CustomerIdAlreadyExistsException(
                    "Customer already exists!!");
    }


}






