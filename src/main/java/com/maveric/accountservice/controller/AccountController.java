package com.maveric.accountservice.controller;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Optional;

@RestController

@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public Optional<Account> getAccount(@PathVariable("customerId") String customerId, @Valid
                              @PathVariable("accountId") String accountId) throws AccountNotFoundException {
        Optional<Account> accounts=accountService.getAccountByAccId(customerId,accountId);


        return accounts;

    }
}