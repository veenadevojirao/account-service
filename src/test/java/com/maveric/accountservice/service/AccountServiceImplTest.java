package com.maveric.accountservice.service;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Type;
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
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.Tuple;
import java.util.Arrays;
import java.util.List;

import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccountDto;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
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
    public void testGetAccounts() {
        Page<Account> pagedResponse = new PageImpl(Arrays.asList(getAccount(),getAccount()));
        when(repository.findByCustomerId(any(Pageable.class),any())).thenReturn(pagedResponse);
        when(mapper.mapToDto(any())).thenReturn(Arrays.asList(getAccountDto(),getAccountDto()));
        List<AccountDto> accountDtos= service.getAccountByUserId(1, 2,"1");
        assertTrue(accountDtos.size()==2);
    }



}
