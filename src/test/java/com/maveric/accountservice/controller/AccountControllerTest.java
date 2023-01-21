package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
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
import org.springframework.web.util.NestedServletException;

//import static com.maveric.accountservice.AccountServiceApplicationTests.getUserDto;
import static com.maveric.accountservice.enums.Constants.ACCOUNT_DELETED_SUCCESS;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    @Test
    void deleteAccounts() throws Exception{
        ResponseEntity<AccountDto> responseEntity = new ResponseEntity<>(getAccountDto(), HttpStatus.OK);
        mock.perform(delete("/api/v1/customers/1/accounts/1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
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
    public void deleteAccount() throws Exception
    {
        mock.perform(delete("/api/v1/customers/78/accounts/").header("userId","1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }
    @Test
    public void nottodeleteAccount() throws Exception
    {
        when(accountService.deleteAccount(any(String.class))).thenReturn(ACCOUNT_DELETED_SUCCESS);
//        when(balanceServiceConsumer.deleteBalanceByAccountId(any(String.class))).thenReturn(null);
//        when(transactionServiceConsumer.deleteAllTransaction(any(String.class))).thenReturn(null);
        String apiV1 = new String();
        mock.perform(delete(apiV1+"/1234").header("userId","1234")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound())
                .andDo(print());
    }

}
