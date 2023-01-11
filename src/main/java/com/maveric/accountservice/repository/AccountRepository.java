package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface AccountRepository extends MongoRepository<Account,String> {

        @Query(value = "{customerId:'?0'}")
        List<AccountDto> findAll(String customerId);

}

