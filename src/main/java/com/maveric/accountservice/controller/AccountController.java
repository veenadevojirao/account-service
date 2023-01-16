package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import com.maveric.accountservice.services.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountByCustomerId(@PathVariable String customerId, @RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") @Valid Integer pageSize)throws CustomerIdMissmatch {
List<AccountDto> accountDtoResponse = accountService.getAccountByUserId(page, pageSize, customerId);
        return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);

    }
}
