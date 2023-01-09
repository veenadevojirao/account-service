package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AccountRepository extends MongoRepository<Account, String> {
    @Query(value = "{customerId:'?0'}")
    List<AccountDto> findAll(String customerId);
}

