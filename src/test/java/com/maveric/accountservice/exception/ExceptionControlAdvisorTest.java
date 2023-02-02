package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.converter.HttpMessageNotReadableException;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionControlAdvisorTest {
    private  ExceptionControlAdvisor controllerAdvisor = new ExceptionControlAdvisor();

    @Test
    void CustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer ID Not found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("Nosuch customerId is present!",error.getCode());
    }

    @Test
    void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account Not found");
        ErrorDto error = controllerAdvisor.handleAccountNotFoundException(exception);
        assertEquals("404",error.getCode());
    }

    @Test
    void handleCustomerNotFoundException() {
        CustomerNotFoundException exception = new CustomerNotFoundException("Customer Not found");
        ErrorDto error = controllerAdvisor.handleCustomerNotFoundException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handlePathParamsVsInputParamsMismatchException() {
        PathParamsVsInputParamsMismatchException exception = new PathParamsVsInputParamsMismatchException("Input Not valid");
        ErrorDto error = controllerAdvisor.handlePathParamsVsInputParamsMismatchException(exception);
        assertEquals("400",error.getCode());
    }

}
