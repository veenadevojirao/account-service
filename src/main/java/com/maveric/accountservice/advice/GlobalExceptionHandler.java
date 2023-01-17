package com.maveric.accountservice.advice;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.CustomerIDNotFoundExistsException;
import com.maveric.accountservice.exception.CustomerIdMissmatch;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.maveric.accountservice.enums.Constants.NOT_FOUND;


@RestControllerAdvice

    public class GlobalExceptionHandler {


        @ExceptionHandler(AccountNotFoundException.class)
        public ResponseEntity<ErrorReponseDto> handllingException(AccountNotFoundException ex) {
            ErrorReponseDto response = new ErrorReponseDto();
            response.setCode("404");
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);

        }
    @ExceptionHandler({CustomerIDNotFoundExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerIDNotFoundExistsException(CustomerIDNotFoundExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(NOT_FOUND);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }
        @ExceptionHandler(CustomerIdMissmatch.class)
        public ResponseEntity<ErrorReponseDto> handllingException(CustomerIdMissmatch ex) {
            ErrorReponseDto response = new ErrorReponseDto();
            response.setCode("400");
            response.setMessage(ex.getMessage());
            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);

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

    }

