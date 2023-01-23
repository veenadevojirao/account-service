package com.maveric.accountservice.controller;

import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=AccountController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)
public class AccountControllerTest {
    @Autowired
    private MockMvc mock;

    @MockBean
    private AccountService accountService;

    @MockBean
    AccountRepository repository;

    @MockBean
    private AccountMapper mapper;


    @Test
    public void shouldGetStatus200WhenRequestMadeToGetAccountDetails() throws Exception
    {
        when(accountService.getAccountByAccId(any(String.class),any(String.class))).thenReturn(Optional.empty());


        mock.perform(get("/api/v1/customers/1/accounts/1").header("userId","1"))
                .andExpect(status().isOk())
                .andReturn();
    }

}