package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;

import com.maveric.accountservice.exception.AccountNotFoundException;

import com.maveric.accountservice.exception.CustomerIdMissmatch;

import com.maveric.accountservice.dto.AccountDto;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import net.bytebuddy.matcher.ElementMatcher;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service


public interface AccountService {

    Optional<Account> getAccountByAccId(String customerId, String accountId) throws AccountNotFoundException;
//    Optional<Account> getAccountByCusId(String customerId, String accountId) throws AccountIDNotfoundException;


    List<Account> getAccountById(String customerId);
    public List<AccountDto> getAccountByUserId(Integer page, Integer pageSize, String customerId)throws CustomerIdMissmatch;

    Account updateAccount(String customerId, String accountId, Account account) throws CustomerIDNotFoundExistsException;

    Object updateAccount(Object any);


    public String deleteAccount(String accountId);


    public AccountDto createAccount(String customerId, AccountDto accountDto);

}


