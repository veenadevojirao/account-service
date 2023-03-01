package com.maveric.accountservice.repository;

import com.maveric.accountservice.entity.Account;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static org.junit.jupiter.api.Assertions.assertNotNull;


@DataMongoTest

public class AccountRepositoryTest {
    @Autowired
    AccountRepository repository;


    @Test
    public void testSave() {
        Account account = repository.save(getAccount());
        Assert.assertEquals("1234",account.getCustomerId());
    }


    @Test

    public void testFindAll() {
        List<Account> account = repository.findAll();
        assertNotNull(account);
        assert(account.size()>0);
    }

}


