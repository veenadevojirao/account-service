package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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
    @Test
    public void getAccounts() throws Exception
    {

        mock.perform(get("/api/v1/customers/1234/accounts")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "1234"))
                .andExpect(status().isOk())
                .andDo(print());
    }
    @Test
    public void notgetAccounts() throws Exception
    {
//        String invalidApiV1 = new String();
        mock.perform(get("/api/v1/customers/12346/accounts")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andDo(print());
    }


}
