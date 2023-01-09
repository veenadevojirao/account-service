package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends MongoRepository<AccountDto, String> {
    Optional<AccountDto> findById(String accountId);

    List<Account> findAll(String customerId);

    AccountDto save(AccountDto account);
}

