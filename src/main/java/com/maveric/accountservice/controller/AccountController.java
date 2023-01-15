package com.maveric.accountservice.controller;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIdAlreadyExistsException;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping(value="api/v1/customers")
public class AccountController
{
    @Autowired
    private AccountService accountService;

    @PostMapping("/{customerId}/accounts")
    public ResponseEntity<Account> createAccount(@PathVariable(name ="customerId") String customerId, @Valid @RequestBody Account account) throws CustomerIdAlreadyExistsException {
        Account AccountDto = accountService.createAccount(account);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("message",

                "Account successfully created");


        return new ResponseEntity<>(AccountDto, responseHeaders, HttpStatus.OK);

    }
}