package com.maveric.accountservice.feignclient;


import com.maveric.accountservice.dto.BalanceDto;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@FeignClient(value = "balance", url = "http://localhost:3015/api/v1")
public interface BalanceServiceConsumer {

    @GetMapping("accounts/{accountId}/balances")
    public ResponseEntity <BalanceDto> getBalances(@PathVariable String accountId, @RequestHeader(value = "userid") String headerUserId);

    @DeleteMapping("accounts/{accountId}/balances")
    public ResponseEntity<String> deleteBalanceByAccountId(@PathVariable("accountId") String accountId,
                                                           @RequestHeader(value = "userid") String headerUserId);
}

