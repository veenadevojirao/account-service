package com.maveric.accountservice.advice;

import com.maveric.accountservice.dto.ErrorDto;
import com.maveric.accountservice.dto.ErrorReponseDto;
import com.maveric.accountservice.exception.AccountNotFoundException;
import com.maveric.accountservice.exception.NoSuchCustomerExistsException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.maveric.accountservice.enums.Constants.ACCOUNT_NOT_FOUND_CODE;

public class GlobalExceptionHandler {
//    private static final Logger log = org.slf4j.LoggerFactory.getLogger(GlobalExceptionHandler.class);
@ExceptionHandler(AccountNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public final ErrorDto handleAccountNotFoundException(AccountNotFoundException exception) {
    ErrorDto errorDto = new ErrorDto();
    errorDto.setCode(ACCOUNT_NOT_FOUND_CODE);
    errorDto.setMessage(exception.getMessage());
    return errorDto;
}
    @ExceptionHandler(value
            = NoSuchCustomerExistsException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ErrorReponseDto
    handleException(NoSuchCustomerExistsException ex)
    {
        ErrorReponseDto response = new ErrorReponseDto();
        response.setCode("404");
        response.setMessage(ex.getMessage());
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST).getBody();

    }

}
