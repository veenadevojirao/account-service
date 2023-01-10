package com.maveric.accountservice.repository;

import com.maveric.accountservice.entity.Account;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;
public interface AccountRepository extends MongoRepository<Account, String> {

    List<Account> findByCustomerId(String customerId);
    @Query("{customerId:?0}")
    Page<Account> findAllByCustomerId(Pageable pageable, String customerId);

    @Query("{'customerId':?0, '_id':?1}")
    Account findAccountByCustomerId(String customerId, String accountId);


}

