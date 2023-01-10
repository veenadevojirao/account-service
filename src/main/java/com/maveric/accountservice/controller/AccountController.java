package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "customerId") String customerId, @PathVariable(name = "accountId") String accountId, @RequestBody Account account) {
        AccountDto updateAccounts = accountService.updateAccount(customerId, accountId, account);
System.out.println("accountId");
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("message",

                "Account successfully updated");

        Account AccountDto = new Account();
        return new ResponseEntity<>(AccountDto, responseHeaders, HttpStatus.OK);

    }
}
