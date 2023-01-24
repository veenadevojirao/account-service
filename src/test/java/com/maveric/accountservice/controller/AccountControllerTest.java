package com.maveric.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;

import com.maveric.accountservice.enums.Type;
//import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;

import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Type;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import com.maveric.accountservice.mapper.AccountMapper;
import com.maveric.accountservice.mapper.AccountMapperImpl;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountService;
import com.maveric.accountservice.services.AccountServiceImpl;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.stubbing.OngoingStubbing;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;

import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

//import static com.maveric.accountservice.AccountServiceApplicationTests.getUserDto;
import java.util.Date;

import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.util.NestedServletException;

import java.net.URI;
import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.*;
//import static com.maveric.accountservice.AccountServiceApplicationTests.getUserDto;
import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.http.RequestEntity.get;
import static org.springframework.http.RequestEntity.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
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
    @Mock
    ResponseEntity<BalanceDto> balanceDto;

    @Autowired
    ObjectMapper mapper;
    @Test

    void updateAccount() throws Exception{
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Object AccountDto = new Object();
        when(accountService.updateAccount(any())).thenReturn(getAccountDto());
        mock.perform(MockMvcRequestBuilders.put("/api/v1/customers/1/accounts/1235")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto())))
                .andExpect(status().isOk())
                .andDo(print());
    }



@Test
    void createAccounts() throws Exception{
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(getAccountDto(), HttpStatus.OK);
        when(accountService.createAccount(any(), any(AccountDto.class))).thenReturn(getAccountDto());
        mock.perform(MockMvcRequestBuilders.post("/api/v1/customers/1/accounts")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto())))
                .andExpect(status().isCreated())
                .andDo(print());
    }


    public AccountDto getAccountDto(){
        AccountDto accountDto=new AccountDto();
        accountDto.setCustomerId("1");
        accountDto.setType(Type.SAVINGS);
        accountDto.set_id("1");
        return accountDto;
    }


    @Test
    void NotupdateAccount() throws Exception{
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(HttpStatus.OK);
        Object AccountDto = new Object();
        when(accountService.updateAccount(any())).thenReturn(getAccountDto());
        mock.perform(MockMvcRequestBuilders.put("/api/v1/customers/2/accounts/3456")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto())))
                .andExpect(status().isOk())
                .andDo(print());
    }



//    private Object any(Object accountDtozzzzzzzzzz) {
//    }


    @Test
    void createAccounts_failure() throws Exception{
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(getAccountDto1(), HttpStatus.OK);
       when(accountService.createAccount(any(), any(AccountDto.class))).thenReturn(getAccountDto1());
        mock.perform(MockMvcRequestBuilders.post("/api/v1/customers/1/accounts")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto1())))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    public AccountDto getAccountDto1(){
        AccountDto accountDto=new AccountDto();
        accountDto.setCustomerId("1");
        return accountDto;
    }

//    private Object any(Object accountDto) {
//        return accountDto;
//    }
}

