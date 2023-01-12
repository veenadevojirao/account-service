package com.maveric.accountservice.advice;

import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountIDNotfoundException;
import com.maveric.accountservice.exception.AccountNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;


@RestControllerAdvice

public class GlobalExceptionHandler {


    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler(AccountIDNotfoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountIDNotfoundException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("400");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

    }
}
