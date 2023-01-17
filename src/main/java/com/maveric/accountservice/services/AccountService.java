package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface AccountService {
    List<Account> getAccountById(String customerId);
    public List<AccountDto> getAccountByUserId(Integer page, Integer pageSize, String customerId)throws CustomerIdMissmatch;

//    public List<AccountDto> getAccounts(String customerId);
//    public List<AccountDto> getAccountsById(String customerId)throws AccountNotFoundException;
}
