package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.junit.jupiter.api.Test;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;

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
        AccountIDNotfoundException exception = new AccountIDNotfoundException("Account Id Not found");
        ErrorReponseDto error = controllerAdvisor.handleAccountIDNotfoundException(exception);
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
    @Test
    void customerIdMismatchException() {
        CustomerIdMismatchException exception = new CustomerIdMismatchException("Customer Id Mismatch");
        ResponseEntity<ErrorReponseDto> error = controllerAdvisor.handllingException(exception);
        assertEquals("400", error.getBody().getCode());
    }
    @Test
    void customerIDNotFoundExistsException() {
        CustomerIDNotFoundExistsException exception = new CustomerIDNotFoundExistsException("Customer Id Not Found");
        ErrorDto error = controllerAdvisor.handleCustomerIDNotFoundExistsException(exception);
        assertEquals("404", error.getCode());
    }
    @Test
    void httpRequestMethodNotSupportedException() {
        HttpRequestMethodNotSupportedException exception = new HttpRequestMethodNotSupportedException("error");
        ErrorDto error = controllerAdvisor.handleHttpRequestMethodNotSupportedException(exception);
        assertEquals("405", error.getCode());
    }

    @Test
    void missingRequestHeaderException() {
        MissingRequestHeaderException exception = new MissingRequestHeaderException("error");
        ErrorDto error = controllerAdvisor.handleMissingRequestHeaderException(exception);
        assertEquals("400", error.getCode());
    }
}
