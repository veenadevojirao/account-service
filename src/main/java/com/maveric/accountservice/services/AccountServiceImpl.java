package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    AccountRepository accountRepository;
    @Autowired
    private AccountMapper mapper;


    public List<Account> getAccountById(String customerId) {
        return null;
    }

    @Override
    public List<AccountDto> getAccountByUserId(Integer page, Integer pageSize, String userId) throws CustomerIdMissmatch {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<Account> pageResult = accountRepository.findByCustomerId(paging,userId);
        if(pageResult.hasContent()) {
            List<Account> account = pageResult.getContent();
            log.info("Retrieved list of accounts from DB");
            return mapper.mapToDto(account);
        } else {
            throw new CustomerIdMissmatch("Customer Id Missmatch");

        }

    }
}



