package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;

import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;

import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.assertSame;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private Page pageResult;
    @Test

    void updateAccountDetails() {


        AccountDto accountDto = service.updateAccount("1234","123",getAccountDto());

        assertSame(accountDto.getType(),getAccountDto().getType());
    }
    @Test
    void notupdateAccountDetails() {


        AccountDto accountDto = service.updateAccount("1234","",getAccountDto());

        assertSame(accountDto.getType(),getAccountDto().getType());
    }

@Test
    void createAccount() {
        when(mapper.map(any(AccountDto.class))).thenReturn(getAccount());
        when(mapper.map(any(Account.class))).thenReturn(getAccountDto());
        when(repository.save(any())).thenReturn(getAccount());

        AccountDto transactionDto = service.createAccount("1234",getAccountDto());

        assertSame(transactionDto.getCustomerId(), getAccount().getCustomerId());
    }

    @Test
    void createAccount_failure() {
        Throwable error = assertThrows(PathParamsVsInputParamsMismatchException.class,()->service.createAccount("1233",getAccountDto()));  //NOSONAR
        assertEquals("Customer Id-1234 not found. Cannot create account.",error.getMessage());
    }

}

