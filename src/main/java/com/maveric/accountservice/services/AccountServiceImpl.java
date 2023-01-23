package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccountServiceImpl.class);
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
//   private AccountMapper accountMapper;
//    private AccountMapper accountMapper;
    private AccountMapper mapper;

    @Override
    public AccountDto createAccount(String customerId,AccountDto accountDto) {
        if(customerId.equalsIgnoreCase(accountDto.getCustomerId())) {
            //Adding Time
//            accountDto.setCreatedAt(accountDto.getCreatedAt());
//            accountDto.setUpdatedAt(accountDto.getCreatedAt());

            Account account = mapper.map(accountDto);
            Account accountResult = accountRepository.save(account);
            return mapper.map(accountResult);
        }
        else {
            log.error("Customer not found! Cannot create Account.");
            throw new PathParamsVsInputParamsMismatchException("Customer Id-"+accountDto.getCustomerId()+" not found. Cannot create account.");
        }
    }
}