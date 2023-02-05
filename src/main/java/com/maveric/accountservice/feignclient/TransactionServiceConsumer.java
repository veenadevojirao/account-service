package com.maveric.accountservice.feignclient;

import com.maveric.accountservice.dto.BalanceDto;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(name="transaction-service")
@Service
public interface TransactionServiceConsumer {
    @GetMapping("accounts/{accountId}/balances")
    public ResponseEntity <BalanceDto> getTransactionByAccountId(@PathVariable String accountId, String headerUserId);
    @DeleteMapping("accounts/{accountId}/transactions")
    public ResponseEntity<String> deleteTransactionByAccountId(@PathVariable("accountId") String accountId,
                                                               @RequestHeader(value = "userid") String headerUserId);

}
