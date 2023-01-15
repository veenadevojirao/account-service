package com.maveric.accountservice.services;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.NoSuchCustomerExistsException;
import com.maveric.accountservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
@Service
public class AccountServiceImpl implements AccountService {
    @Autowired
    private AccountRepository accountRepository;


    @Override
    public Account updateAccount(String customerId, String accountId, Account account) {
        Account existingAccount = accountRepository.findById(account.getCustomerId())
                .orElse(null);
        if (existingAccount == account)
            throw new NoSuchCustomerExistsException(
                    "No Such Customer exists!!");
        else {
            existingAccount.setCustomerId(account.getCustomerId());
            existingAccount.setType(account.getType());
            existingAccount.setUpdatedAt(account.getUpdatedAt());
            existingAccount.setCreatedAt(account.getCreatedAt());
            Account accountUpdated = accountRepository.save(existingAccount);
//            return mapper.map(accountUpdated);
           return accountRepository.save(existingAccount);

//            MongoIterableImpl mapper = null;
//            return mapper.map(accountUpdated);
        }
//        return accountRepository.save(account);
    }
}