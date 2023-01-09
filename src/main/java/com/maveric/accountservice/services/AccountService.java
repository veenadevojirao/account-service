package com.maveric.accountservice.services;

import org.springframework.stereotype.Service;

@Service

public interface AccountService {

    public String deleteAccount(String customerId, String accountId);


}
