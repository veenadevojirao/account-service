package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import org.springframework.stereotype.Service;

@Service

public interface AccountService {
    Account getAccountByAccId(String customerId, String accountId);
}
