package com.maveric.accountservice.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.PathParamsVsInputParamsMismatchException;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionControlAdvisor.class);

    @ExceptionHandler(AccountNotFoundException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex) {
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

    }
    @ExceptionHandler //for enum
    public ResponseEntity<ErrorReponseDto> invalidValueException(InvalidFormatException ex) {

        //HttpMessageNotReadableException.
        ErrorReponseDto responseDto = new ErrorReponseDto();
        responseDto.setCode("400");
        if (ex.getMessage().contains("enum")) {
            responseDto.setMessage("Cannot have values other than enum [SAVINGS,CURRENT]");
        } else
            responseDto.setMessage(ex.getMessage());
        return new ResponseEntity<>(responseDto, HttpStatus.BAD_REQUEST);
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
