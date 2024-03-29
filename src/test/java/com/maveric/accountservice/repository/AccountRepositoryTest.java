package com.maveric.accountservice.repository;

import com.maveric.accountservice.entity.Account;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;


import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;



@DataMongoTest

class AccountRepositoryTest {
    @Autowired
    AccountRepository repository;


    @Test
    public void findByCustomerId() {
        Account account = repository.save(getAccount());
        Assert.assertEquals("1234",account.getCustomerId());
    }



}
