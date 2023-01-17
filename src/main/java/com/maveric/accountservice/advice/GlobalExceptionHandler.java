package com.maveric.accountservice.advice;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountIDNotfoundException;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.maveric.accountservice.enums.Constants.BAD_REQUEST_CODE;
import static com.maveric.accountservice.enums.Constants.NOT_FOUND;


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
    @ExceptionHandler({CustomerIDNotFoundExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerIDNotFoundExistsException(CustomerIDNotFoundExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(NOT_FOUND);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ErrorDto handleMessageNotReadableException() {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        errorDto.setMessage("Type is mandatory - 'SAVINGS' or 'CURRENT'");
        return errorDto;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return errorDto;
    }
}
