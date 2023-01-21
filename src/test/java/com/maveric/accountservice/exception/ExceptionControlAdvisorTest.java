package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.mockito.Mockito.mock;


public class ExceptionControlAdvisorTest {
 ExceptionControlAdvisor controlAdvisor=new ExceptionControlAdvisor();
    @Test
    void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account Not found");
        ErrorDto error = controlAdvisor.handleAccountNotFoundException(exception);
        assertEquals("404",error.getCode());
    }

}
