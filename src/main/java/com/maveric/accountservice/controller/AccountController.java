package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
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


import com.maveric.accountservice.exception.CustomerIdMissmatch;

import com.maveric.accountservice.entity.Account;

import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import java.util.List;



@RestController

@RequestMapping("/api/v1")
public class AccountController {
    @Autowired

    AccountService accountService;
//    private AccountService accountService;
    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public Optional<Account> getAccount(@PathVariable("customerId") String customerId, @Valid
    @PathVariable("accountId") String accountId) throws AccountNotFoundException {
        Optional<Account> accounts=accountService.getAccountByAccId(customerId,accountId);


        return accounts;

    }



    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "customerId") String customerId, @Valid @PathVariable(name = "accountId") String accountId, @RequestBody Account account) {
        Account AccountDto = accountService.updateAccount(customerId, accountId, account);

        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("message",

                "Account successfully updated");


        return new ResponseEntity<>(AccountDto, responseHeaders, HttpStatus.OK);

    }

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountByCustomerId(@PathVariable String customerId, @Valid @RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") @Valid Integer pageSize)throws CustomerIdMissmatch {
List<AccountDto> accountDtoResponse = accountService.getAccountByUserId(page, pageSize, customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);

    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("customerId") String customerId,@Valid
    @PathVariable("accountId") String accountId){
        String AccountDto=accountService.deleteAccount(accountId);
        HttpHeaders responseHeaders = new HttpHeaders();

        responseHeaders.add("message",
                "account deleted sucessfully");
        return new ResponseEntity<AccountDto>(HttpStatus.OK);

    }

    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount (@PathVariable String customerId, @Valid @RequestBody AccountDto
            accountDto){

        AccountDto accountDtoResponse = accountService.createAccount(customerId, accountDto);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);


    }

}


