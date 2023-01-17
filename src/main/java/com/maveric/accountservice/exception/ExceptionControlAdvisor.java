package com.maveric.accountservice.exception;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_CODE;
import static com.maveric.accountservice.enums.Constants.BAD_REQUEST_CODE;
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
    @ExceptionHandler(CustomerIdAlreadyExistsException.class)
    public ResponseEntity<ErrorReponseDto> handllingException(CustomerIdAlreadyExistsException ex){
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("409");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response, HttpStatus.NOT_ACCEPTABLE);

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
