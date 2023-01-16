package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_CODE;

@RestControllerAdvice
public class ExceptionControlAdvisor {
    @ExceptionHandler(AccountNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleAccountNotFoundException(AccountNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
}