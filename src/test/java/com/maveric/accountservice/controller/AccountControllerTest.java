package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Type;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountService;
import com.maveric.accountservice.services.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ContextConfiguration(classes=AccountController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private AccountService accountService;



    @Mock
    ResponseEntity<BalanceDto> balanceDto;
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;
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
