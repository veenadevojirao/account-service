package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import org.springframework.stereotype.Service;

@Service

public interface AccountService {
//    AccountDto updateAccount(String customerId, String accountId, Account accountDto);

    Account updateAccount(Account account);
}
