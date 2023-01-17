package com.maveric.accountservice.advice;

import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.ExceptionControlAdvisor;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionControlAdvisor.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex) {
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }

    @ExceptionHandler(PathParamsVsInputParamsMismatchException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(PathParamsVsInputParamsMismatchException ex) {
        {
            ErrorReponseDto response = new ErrorReponseDto();
            response.setCode("400");
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

    }
}
