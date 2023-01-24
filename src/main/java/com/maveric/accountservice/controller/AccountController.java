package com.maveric.accountservice.controller;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    private AccountService accountService;


    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "customerId") String customerId, @Valid @PathVariable(name = "accountId") String accountId, @RequestBody Account account) {
        Account AccountDto = accountService.updateAccount(customerId, accountId, account);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("message",

                "Account successfully updated");


        return new ResponseEntity<>(AccountDto, responseHeaders, HttpStatus.OK);

    }

        @PostMapping("customers/{customerId}/accounts")
        public ResponseEntity<AccountDto> createAccount (@PathVariable String customerId, @Valid @RequestBody AccountDto
        accountDto){

            AccountDto accountDtoResponse = accountService.createAccount(customerId, accountDto);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);


        }
    }
