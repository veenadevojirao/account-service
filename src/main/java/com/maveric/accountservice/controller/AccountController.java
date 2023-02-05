package com.maveric.accountservice.controller;

import com.maveric.accountservice.dto.AccountDto;
import com.maveric.accountservice.dto.BalanceDto;
import com.maveric.accountservice.dto.UserDto;
import com.maveric.accountservice.entity.Account;
import com.maveric.accountservice.enums.Constants;
import com.maveric.accountservice.exception.*;
import com.maveric.accountservice.feignclient.BalanceServiceConsumer;
import com.maveric.accountservice.feignclient.TransactionServiceConsumer;
import com.maveric.accountservice.feignclient.UserServiceConsumer;
import com.maveric.accountservice.repository.AccountRepository;
import com.maveric.accountservice.services.AccountService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

import static com.maveric.accountservice.enums.Constants.AUTH_HEADER_ERROR_MESSAGE;


@RestController

@RequestMapping("/api/v1")
public class AccountController {
    private static final Logger log = org.slf4j.LoggerFactory.getLogger(AccountController.class);
    @Autowired

    AccountService accountService;
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    BalanceServiceConsumer balanceServiceConsumer;

    @Autowired
    TransactionServiceConsumer transactionServiceConsumer;

    @Autowired
    UserServiceConsumer userServiceConsumer;


    @GetMapping("customers/{customerId}/accounts/{accountId}")
    public AccountDto getAccount(@PathVariable("customerId") String customerId,
                                 @PathVariable("accountId") String accountId, @RequestHeader(value = "userid") String headerUserId) throws AccountNotFoundException,CustomerIDNotFoundExistsException{
        log.info("API call to retrieve account details and its corresponding balance details for valid Account Id and Customer Id");
        ResponseEntity<UserDto> responseEntityUserDto = userServiceConsumer.getUserById(customerId,headerUserId);
        UserDto userDto = responseEntityUserDto.getBody()==null?new UserDto():responseEntityUserDto.getBody(); //NOSONAR


        if(userDto.getId().equalsIgnoreCase(headerUserId)) {  //NOSONAR
            AccountDto accountDtoResponse = accountService.getAccountByAccId(customerId,accountId);
            ResponseEntity<BalanceDto> balanceDto = balanceServiceConsumer.getBalances(accountId,headerUserId);
            accountDtoResponse.setBalance(balanceDto.getBody());
            log.info("Retrieved account details.");
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK).getBody();
        }
        else
        {
            log.error(AUTH_HEADER_ERROR_MESSAGE);
            throw new UnAuthorizedException(AUTH_HEADER_ERROR_MESSAGE);
        }


    }

    @PutMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<Account> updateAccount(@PathVariable(name = "customerId") String customerId,
                                                 @Valid @PathVariable(name = "accountId") String accountId,
                                                 @RequestBody Account account,@RequestHeader(value = "userid") String headerUserId) {
        log.info("API call to update account details");
        ResponseEntity<UserDto> responseEntityUserDto = userServiceConsumer.getUserById(customerId,headerUserId);
        UserDto userDto = responseEntityUserDto.getBody()==null?new UserDto():responseEntityUserDto.getBody(); //NOSONAR
        if(userDto.getId().equalsIgnoreCase(headerUserId)) { //NOSONAR
            Account AccountDto = accountService.updateAccount(customerId, accountId, account);

            HttpHeaders responseHeaders = new HttpHeaders();

            responseHeaders.add("message",

                    "Account successfully updated");


            return new ResponseEntity<>(AccountDto, responseHeaders, HttpStatus.OK);
        }
        else
        {
            log.error(AUTH_HEADER_ERROR_MESSAGE);
            throw new UnAuthorizedException(AUTH_HEADER_ERROR_MESSAGE);
        }

    }
    @GetMapping("customers/{customerId}/accounts")
    public ResponseEntity<List<AccountDto>> getAccountByCustomerId(@PathVariable String customerId,
                                                                   @Valid @RequestParam(defaultValue = "0") Integer page,
                                                                   @RequestParam(defaultValue = "10") @Valid Integer pageSize,
                                                                   @RequestHeader(value = "userid") String headerUserId) throws CustomerIdMissmatchException {

        if(headerUserId.equals(customerId)) {
            List<AccountDto> accountDtoResponse = accountService.getAccountByUserId(page, pageSize, customerId);
            return new ResponseEntity<>(accountDtoResponse, HttpStatus.OK);
        } else {
            throw new CustomerIdMissmatchException("You are not an authorized user");
        }
    }

    @DeleteMapping("customers/{customerId}/accounts/{accountId}")
    public ResponseEntity<String> deleteAccount(@PathVariable String customerId,
                                                @PathVariable String accountId,
                                                @RequestHeader(value = "userid") String headerUserId) {
        log.info("API call to delete account details");
        ResponseEntity<UserDto> responseEntityUserDto = userServiceConsumer.getUserById(customerId,headerUserId);
        UserDto userDto = responseEntityUserDto.getBody()==null?new UserDto():responseEntityUserDto.getBody(); //NOSONAR
        if(userDto.getId().equalsIgnoreCase(headerUserId)) { //NOSONAR
            String result = accountService.deleteAccount(accountId,customerId);
            if (result != null) {
                balanceServiceConsumer. deleteBalanceByAccountId(accountId,headerUserId);
                transactionServiceConsumer.deleteTransactionByAccountId(accountId,headerUserId);
            }
            log.info("Account deleted along with its corresponding balance and transaction details");
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
        else
        {
            log.error(AUTH_HEADER_ERROR_MESSAGE);
            throw new UnAuthorizedException(AUTH_HEADER_ERROR_MESSAGE);
        }

    }


    @PostMapping("customers/{customerId}/accounts")
    public ResponseEntity<AccountDto> createAccount (@PathVariable String customerId, @Valid @RequestBody AccountDto
            accountDto, @RequestHeader(value = "userid") String headerUserId){

        if(headerUserId.equals(customerId)) {
            UserDto userDto = userServiceConsumer.getUserById(customerId, headerUserId).getBody();
            if(userDto.getId().equals(accountDto.getCustomerId())) {
                AccountDto accountDtoResponse = accountService.createAccount(customerId, accountDto);
                return new ResponseEntity<>(accountDtoResponse, HttpStatus.CREATED);
            } else {
                throw new CustomerIDNotFoundExistsException("Customer does not exist");
            }
        } else {
            throw new CustomerIdMissmatchException(Constants.NOT_AUTHORIZED_USER);
        }


    }

}