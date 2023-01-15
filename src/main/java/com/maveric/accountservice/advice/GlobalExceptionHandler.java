package com.maveric.accountservice.advice;

import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.NoSuchCustomerExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.security.auth.login.AccountNotFoundException;

public class GlobalExceptionHandler {
    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(value
            = NoSuchCustomerExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorReponseDto
    handleException(NoSuchCustomerExistsException ex)
    {
        return new ErrorReponseDto(
                HttpStatus.NOT_FOUND.value(), ex.getMessage());
    }
}
