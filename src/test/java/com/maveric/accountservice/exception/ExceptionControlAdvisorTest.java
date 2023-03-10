package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ExceptionControlAdvisorTest {
    private  ExceptionControlAdvisor controllerAdvisor = new ExceptionControlAdvisor();

    @Test
    void CustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer ID Not found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("404",error.getCode());
    }

    @Test
    void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account Not found");
        ErrorDto error = controllerAdvisor.handleAccountNotFoundException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleAccountIDNotfoundException() {
        AccountIDNotFoundException exception = new AccountIDNotFoundException("Account Not found");
        ErrorDto error = controllerAdvisor.handleAccountIDNotFoundException(exception);
        assertEquals("400",error.getCode());
    }
    @Test
    void handleCustomerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer ID Exists");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handlePathParamsVsInputParamsMismatchException() {
        PathParamsVsInputParamsMismatchException exception = new PathParamsVsInputParamsMismatchException("Input Not valid");
        ErrorDto error = controllerAdvisor.handlePathParamsVsInputParamsMismatchException(exception);
        assertEquals("400",error.getCode());
    }

}
