package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.exception.AccountNotFoundException;
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
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class AccountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl accountService;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImpl mapper;

    @Mock
    private Page pageResult;
    @Test
    void getAccountDetailsById() throws AccountNotFoundException {
        when(repository.findById(any(String.class))).thenReturn(Optional.empty());
        assertThrows(AccountNotFoundException.class,()->{
            accountService.getAccountByAccId("1","1");
        });
    }
}
