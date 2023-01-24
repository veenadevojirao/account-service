package com.maveric.accountservice.exception;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
import static com.maveric.accountservice.enums.Constants.*;

import static com.maveric.accountservice.enums.Constants.*;


@ControllerAdvice
@RestControllerAdvice
public class ExceptionControlAdvisor {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(ExceptionControlAdvisor.class);
    @ExceptionHandler(AccountNotFoundException.class)

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleAccountNotFoundException(AccountNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

    @ExceptionHandler({CustomerIDNotFoundExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerIDNotFoundExistsException(CustomerIDNotFoundExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(NOT_FOUND);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }


//    @ExceptionHandler(value
//            = CustomerIDNotFoundExistsException.class)
//    @ResponseStatus(HttpStatus.BAD_REQUEST)
//    public @ResponseBody ErrorReponseDto
//    handleException(CustomerIDNotFoundExistsException ex)
//    {
//        ErrorReponseDto response = new ErrorReponseDto();
//        response.setCode("404");
//        response.setMessage(ex.getMessage());
//        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST).getBody();
//
//    }
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public final ErrorDto handleMessageNotReadableException(HttpMessageNotReadableException exception) {

        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
        errorDto.setMessage("Type is mandatory - 'SAVINGS' or 'CURRENT'");
        return errorDto;
    }


    @ExceptionHandler(CustomerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerNotFoundException(CustomerNotFoundException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
        errorDto.setMessage(exception.getMessage());
        log.error("{}->{}",ACCOUNT_NOT_FOUND_CODE,exception.getMessage());
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



    @ExceptionHandler(PathParamsVsInputParamsMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public final ErrorDto handlePathParamsVsInputParamsMismatchException(PathParamsVsInputParamsMismatchException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(BAD_REQUEST_CODE);
        errorDto.setMessage(exception.getMessage());
        log.error("{}-{}",BAD_REQUEST_CODE,exception.getMessage());
        return errorDto;
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

    @ExceptionHandler({NoSuchCustomerExistsException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public final ErrorDto handleCustomerIDNotFoundExistsException(NoSuchCustomerExistsException exception) {
        ErrorDto errorDto = new ErrorDto();
        errorDto.setCode(NOT_FOUND);
        errorDto.setMessage(exception.getMessage());
        return errorDto;
    }

//    @ExceptionHandler(HttpMessageNotReadableException.class)
//    public final ErrorDto handleMessageNotReadableException() {
//        ErrorDto errorDto = new ErrorDto();
//        errorDto.setCode(String.valueOf(HttpStatus.BAD_REQUEST));
//        errorDto.setMessage("Type is mandatory - 'SAVINGS' or 'CURRENT'");
//        return errorDto;
//    }
}

