package com.maveric.accountservice.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.UserDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Currency;
import com.maveric.accountservice.enums.Type;
import com.maveric.accountservice.feignclient.BalanceServiceConsumer;
import com.maveric.accountservice.feignclient.TransactionServiceConsumer;
import com.maveric.accountservice.feignclient.UserServiceConsumer;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountService;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
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
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.maveric.accountservice.AccountServiceApplicationTests.*;
import static com.maveric.accountservice.AccountServiceApplicationTests.getBalanceDto;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ContextConfiguration(classes=AccountController.class)
//@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@WebMvcTest(AccountController.class)


public class AccountControllerTest {

    public static final String API_V1_ACCOUNTS ="/api/v1/customers/1234/accounts";


    @Autowired
    MockMvc mvc;
    @Autowired
    ObjectMapper mapper;
    @Mock
    private List<Account> account;
    @MockBean
    private AccountService accountService;
    @MockBean
    private BalanceServiceConsumer balanceServiceConsumer;

    @MockBean
    private TransactionServiceConsumer transactionServiceConsumer;

    @MockBean
    private UserServiceConsumer userServiceConsumer;
    @MockBean
    private AccountRepository accountRepository;


    @Autowired
    private MockMvc mock;


    @Mock
    ResponseEntity<BalanceDto> balanceDto;

    @Test
    void getAccountByCustomerId() throws Exception {
        when(userServiceConsumer.getUserById(any(String.class), any(String.class))).thenReturn(getUserDto());
        mock.perform(get(apiV1+ "?page=0&pageSize=2")
                        .contentType(MediaType.APPLICATION_JSON).header("userid", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void getAccountByCustomerId_failure() throws Exception {
        mock.perform(get(invalidApiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }
    @Test
 void shouldGetStatus400WhenRequestMadeTogetAccounts() throws Exception
    {
        mock.perform(get(invalidApiV1)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    @Test
    void getAccountsByAccId() throws Exception {
        when(userServiceConsumer.getUserById(anyString(), anyString())).thenReturn(getUserDto());
        when(accountService.getAccountByAccId(anyString(),anyString())).thenReturn(getAccountDto());
        when(balanceServiceConsumer.getBalances(anyString(),anyString())).thenReturn((getBalanceDto()));
        mvc.perform(get(API_V1_ACCOUNTS+"/1234"+"?page=0&pageSize=2")
                        .contentType(MediaType.APPLICATION_JSON).header("userid", "1234"))
                .andExpect(status().isOk())
                .andDo(print());


    }
    @Test
    void shouldnotgetAccountsByAccId() throws Exception {
        when(userServiceConsumer.getUserById(anyString(), anyString())).thenReturn(getUserDto());
        when(accountService.getAccountByAccId(anyString(),anyString())).thenReturn(getAccountDto());
        when(balanceServiceConsumer.getBalances(anyString(),anyString())).thenReturn((getBalanceDto()));
        mvc.perform(get(invalidApiV1)
                        .contentType(MediaType.APPLICATION_JSON).header("userid", "1234"))
                .andExpect(status().isOk())
                .andDo(print());


    }


    @Test
    void deleteAccount() throws Exception {
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(getUserDto());
        when(accountRepository.findById(anyString())).thenReturn(Optional.of(getAccount()));
        when(balanceServiceConsumer.getBalances(anyString(),anyString())).thenReturn((getBalanceDto()));
        mock.perform(delete(apiV1 + "/accountId1").header("userid", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void deleteAccount_failure() throws Exception {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(responseEntity);
        when(balanceServiceConsumer.deleteBalanceByAccountId(any(String.class),any())).thenReturn(null);
        when(transactionServiceConsumer.deleteAllTransactionsByAccountId(any(String.class),any())).thenReturn(null);
        Throwable error = assertThrows(NestedServletException.class, () -> mock.perform(delete(invalidApiV1)
                .contentType(MediaType.APPLICATION_JSON)
                .header("userid", "1234")).andReturn());
    }

    @Test
    void updateAccount() throws Exception {

        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(getUserDto());
        mock.perform(put(apiV1 + "/accountId1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                        .header("userid", "1234")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void updateAccount_failure() throws Exception {
        ResponseEntity<UserDto> responseEntity = new ResponseEntity<>(new UserDto(), HttpStatus.OK);
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(responseEntity);
        Throwable error = assertThrows(NestedServletException.class, () -> mock.perform(put(apiV1 + "/accountId1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(getAccountDto()))
                .header("userid", "1234")
        ).andReturn());
    }

    @Test
    void createAccount() throws Exception{
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(getUserDto());
        when(accountService.createAccount(any(), any(AccountDto.class))).thenReturn(getAccountDto1());
        mock.perform(post("/api/v1/customers/1234/accounts")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(getAccountDto()))
                        .header("userid", "1234")
                )
                .andExpect(status().isCreated())
                .andDo(print());
    }


    public AccountDto getAccountDto() {
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomerId("1234");
        accountDto.setBalance(balanceDto, 1234);
        accountDto.setType(Type.SAVINGS);
        accountDto.set_id("1");
        return accountDto;
    }

    @Test
    void createAccounts_failure() throws Exception {
        when(userServiceConsumer.getUserById(any(String.class),any())).thenReturn(getUserDto());
        when(accountService.createAccount(any(), any(AccountDto.class))).thenReturn(getAccountDto1());
        mock.perform(MockMvcRequestBuilders.post(API_V1_ACCOUNTS)
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto1())))
                .andExpect(status().isBadRequest())
                .andDo(print());
    }

    public AccountDto getAccountDto1() {
        AccountDto accountDto = new AccountDto();
        accountDto.setCustomerId("1");
        accountDto.setBalance(balanceDto, 1234);
        return accountDto;
    }

    public ResponseEntity<List<Account>> getSampleAccount() {

        List<Account> accountList = new ArrayList<>();
        Account account = new Account();
        account.setCustomerId("1");


        Account account1 = new Account();
        account1.setCustomerId("2");


        accountList.add(account1);
        accountList.add(account);
        return ResponseEntity.status(HttpStatus.OK).body(accountList);
    }
}