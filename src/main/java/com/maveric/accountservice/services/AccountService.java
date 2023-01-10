package com.maveric.accountservice.services;

import org.springframework.stereotype.Service;

@Service

public interface AccountService {
    String deleteAccount(String customerId, String accountId);
}
