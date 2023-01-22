package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionControlAdvisorTest {
    private ExceptionControlAdvisor controllerAdvisor = new ExceptionControlAdvisor();
    @Test
    void CustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer ID Not found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("Nosuch customerId is present!",error.getCode());
    }
}
