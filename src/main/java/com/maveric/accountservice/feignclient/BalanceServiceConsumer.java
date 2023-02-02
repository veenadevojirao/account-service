package com.maveric.accountservice.feignclient;


import com.maveric.accountservice.dto.BalanceDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="balance-service")
@Service
public interface BalanceServiceConsumer {

    @GetMapping("api/v1/accounts/{accountId}/balances")
    public ResponseEntity <BalanceDto> getBalances(@PathVariable String accountId);

    @DeleteMapping("api/v1/accounts/{accountId}/balances")
    public ResponseEntity<String> deleteBalanceByAccountId(@PathVariable String accountId);

}

