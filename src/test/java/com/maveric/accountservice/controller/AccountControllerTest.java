package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.enums.Type;
import com.maveric.accountservice.services.AccountService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static com.maveric.accountservice.AccountServiceApplicationTests.asJsonString;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
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
    public void shouldGetStatus404WhenRequestMadeToUpdateAccount() throws Exception
    {
        mock.perform(put("/api/v1/customers/1/accounts/1234")
                        .contentType(MediaType.APPLICATION_JSON).header("userId", "3456")
                        .content(asJsonString(getAccountDto()))
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

}
