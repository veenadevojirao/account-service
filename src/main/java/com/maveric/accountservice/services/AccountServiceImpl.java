package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_MESSAGE;

@Repository
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountMapper mapper;

    public Account updateAccount(String customerId, String accountId, Account account) {
        Account accountResult=accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE+accountId));
//        accountResult=accountRepository.findById(customerId).orElseThrow(()-> new CustomerIDNotFoundExistsException(BAD_REQUEST_MESSAGE+customerId);
        if(!customerId.equals(account.getCustomerId())){
            throw new CustomerIDNotFoundExistsException("Customer Id should not be empty");
        }

        accountResult.set_id(accountResult.get_id());



        accountResult.setCustomerId(account.getCustomerId());
        accountResult.setType(account.getType());
        accountResult.setCreatedAt(accountResult.getCreatedAt());
        accountResult.setUpdatedAt(account.getUpdatedAt());
        Account accountUpdated = accountRepository.save(accountResult);
//        return mapper.map(accountUpdated);
        return accountRepository.save(accountUpdated);
    }

    @Override
    public Object updateAccount(Object any) {
        return any;
    }


    public AccountDto updateAccount(String customerId, String accountId, AccountDto accountDto) {
        return accountDto;
    }
}
