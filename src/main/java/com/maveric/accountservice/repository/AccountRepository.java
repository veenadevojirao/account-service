package com.maveric.accountservice.repository;

import com.maveric.accountservice.dto.AccountDto;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface AccountRepository extends MongoRepository<AccountDto, String> {

}

