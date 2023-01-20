package com.maveric.accountservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.enums.Type;
//import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
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

//import static com.maveric.accountservice.AccountServiceApplicationTests.getUserDto;
import java.util.Date;

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
        when(accountService.updateAccount(any(AccountDto))).thenReturn(getAccountDto());
        mock.perform(MockMvcRequestBuilders.put("/api/v1/customers/1/accounts/1235")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto())))
                .andExpect(status().isOk())
                .andDo(print());
    }

    private Object any(Object accountDto) {
        return accountDto;
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
        when(accountService.updateAccount(any(AccountDto))).thenReturn(getAccountDto());
        mock.perform(MockMvcRequestBuilders.put("/api/v1/customers/2/accounts/3456")
                        .contentType(MediaType.APPLICATION_JSON).content(mapper.writeValueAsString(getAccountDto())))
                .andExpect(status().isOk())
                .andDo(print());
    }

}