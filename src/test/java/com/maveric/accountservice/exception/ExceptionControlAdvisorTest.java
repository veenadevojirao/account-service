package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionControlAdvisorTest {
    private  ExceptionControlAdvisor controllerAdvisor = new ExceptionControlAdvisor();
    @Test
    void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account Not found");
        ErrorDto error = controllerAdvisor.handleAccountNotFoundException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleCustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer Not found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("Nosuch customerId is present!",error.getCode());
    }
}
