package com.maveric.accountservice.service;

import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.mapper.AccountMapperImplTest;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.domain.Page;

import java.util.Optional;
import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
//import static com.maveric.accountservice.AccountServiceApplicationTests.getAccount;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
//@RunWith(SpringRunner.class)
public class AcoountServiceImplTest {
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    @Mock
    private AccountMapperImplTest mapper;

    @Mock
    private Page pageResult;
    private String AccountDto;


    @Test
    void deleteAccount() {
        when(repository.findById("123")).thenReturn(Optional.of(getAccount()));
        willDoNothing().given(repository).deleteById("123");

        String accounDto = service.deleteAccount("123");

        assertSame( "Account deleted sucessfully",accounDto);

    }
    @Test
    void deleteAccount_failure() {
        Throwable error = assertThrows(AccountNotFoundException.class,()->service.deleteAccount("1234"));
        assertEquals("Account not found for Id-1234",error.getMessage());
    }


}
