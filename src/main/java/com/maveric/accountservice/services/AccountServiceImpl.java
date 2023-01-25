package com.maveric.accountservice.services;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;

import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.mapper.AccountMapper;

import com.maveric.accountservice.repository.AccountRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import static com.maveric.accountservice.enums.Constants.ACCOUNT_DELETED_SUCCESS;
import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_MESSAGE;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class AccountServiceImpl implements AccountService {

    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccountServiceImpl.class);

    @Autowired

    AccountRepository accountRepository;

    @Autowired
    private AccountMapper mapper;
    @Override
    public Optional<Account> getAccountByAccId(String customerId, String accountId) throws AccountNotFoundException {

        if (accountRepository.findById(accountId).isPresent()) {
            Account account = new Account();
            Iterable<Account> accnt = accountRepository.findAllById(Arrays.asList(customerId, accountId));
            return accountRepository.findById(accountId);
        }

        //for (Account acc:accnt){
        // account=acc;
        //}
        else {
            throw new AccountNotFoundException("Account details not found");
        }
    }

    @Override
    public String deleteAccount(String accountId) {
        if (!accountRepository.findById(accountId).isPresent()) {
            throw new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE + accountId);
        }

        accountRepository.deleteById(accountId);
        return ACCOUNT_DELETED_SUCCESS;

    }


    @Override
    public Account updateAccount(String customerId, String accountId, Account account) {
        Account accountResult = accountRepository.findById(accountId).orElseThrow(() -> new AccountNotFoundException(ACCOUNT_NOT_FOUND_MESSAGE + accountId));
//        accountResult=accountRepository.findById(customerId).orElseThrow(()-> new CustomerIDNotFoundExistsException(BAD_REQUEST_MESSAGE+customerId);
        if (!customerId.equals(account.getCustomerId())) {
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


    @Override
    public AccountDto createAccount(String customerId, AccountDto accountDto) {
        if (customerId.equalsIgnoreCase(accountDto.getCustomerId())) {
            //Adding Time
//            accountDto.setCreatedAt(accountDto.getCreatedAt());
//            accountDto.setUpdatedAt(accountDto.getCreatedAt());

            Account account = mapper.map(accountDto);
            Account accountResult = accountRepository.save(account);
            return mapper.map(accountResult);
        } else {
            log.error("Customer not found! Cannot create Account.");
            throw new PathParamsVsInputParamsMismatchException("Customer Id-" + accountDto.getCustomerId() + " not found. Cannot create account.");
        }

    }

    @Override
    public List<AccountDto> getAccountByUserId(Integer page, Integer pageSize, String customerId) throws
            CustomerIdMissmatch {
        Pageable paging = PageRequest.of(page, pageSize);
        Page<Account> pageResult = accountRepository.findByCustomerId(paging, customerId);
//        if(!customerId.equals(accountRepository.findByCustomerId(paging,customerId))){
//            throw new CustomerIDNotFoundExistsException("Customer Id should not be empty");
//        }
        if (pageResult.hasContent()) {
            List<Account> account = pageResult.getContent();
            log.info("Retrieved list of accounts from DB");
            return mapper.mapToDto(account);
        } else {
            throw new CustomerIdMissmatch("Customer Id Missmatch");

        }

    }

    public List<Account> getAccountById(String customerId) {
        return null;

    }

}

