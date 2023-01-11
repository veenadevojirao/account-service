package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    AccountService accountService;
    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> getAccount(@PathVariable("customerId") String customerId,
                                                 @PathVariable("accountId") String accountId){
        Account accounts=accountService.getAccountByAccId(customerId, accountId);
        return new ResponseEntity<AccountDto>((MultiValueMap<String, String>) accounts, HttpStatus.OK);
    }
}
