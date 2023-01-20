package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ExceptionControlAdvisorTest {
    private ExceptionControlAdvisor controllerAdvisor = new ExceptionControlAdvisor();
    @Test
    void handleCustomerIdNotFoundException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer Not found");
        ErrorReponseDto error = controllerAdvisor.handleException(exception);
        assertEquals("404",error.getCode());
    }
    @Test
    void handleAccountNotFoundException() {
        AccountNotFoundException exception = new AccountNotFoundException("Account Not found");
        ErrorDto error = controllerAdvisor.handleAccountNotFoundException(exception);
        assertEquals("404",error.getCode());
    }

    @Test
    void handleHttpRequestMethodNotSupportedException()
    {
        HttpMessageNotReadableException exception = new HttpMessageNotReadableException("Exception");
        ErrorDto error = controllerAdvisor.handleMessageNotReadableException(exception);
        assertEquals("400 BAD_REQUEST",error.getCode());
    }
}
