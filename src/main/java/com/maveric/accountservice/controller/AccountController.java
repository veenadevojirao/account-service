package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AccountController {
    @Autowired
    AccountService accountService;

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<AccountDto> deleteAccount(@PathVariable("customerId") String customerId,
                                                    @PathVariable("accountId") String accountId){
        accountService.deleteAccount(customerId, accountId);
        return new ResponseEntity<AccountDto>(HttpStatus.OK);
    }

}
