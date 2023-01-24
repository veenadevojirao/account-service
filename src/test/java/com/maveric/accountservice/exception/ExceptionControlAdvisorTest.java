package com.maveric.accountservice.exception;


import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.accountservice.advice.GlobalExceptionHandler;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.core.MethodParameter;

import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_CODE;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;
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
