package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import org.springframework.http.HttpStatus;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.maveric.accountservice.enums.Constants.*;
@ControllerAdvice
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
    @ExceptionHandler(value
            = CustomerIdAlreadyExistsException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public final ErrorDto handleCustomerAlreadyExistsException(CustomerIdAlreadyExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(Customer_Already_exisists);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
//    public ErrorReponseDto
//    handleCustomerAlreadyExistsException(
//            CustomerIdAlreadyExistsException ex)
//    {
//        return new ErrorReponseDto(HttpStatus.CONFLICT.value(),
//                ex.getMessage());
//    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorDto handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(ex.getBindingResult().getAllErrors().get(0).getDefaultMessage());
        return errorDto;
    }
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    public ErrorDto handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(METHOD_NOT_ALLOWED_CODE);
        errorDto.setMessage(METHOD_NOT_ALLOWED_MESSAGE);
        return errorDto;
    }
}
