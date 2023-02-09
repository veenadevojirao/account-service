package com.maveric.accountservice.feignclient;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestHeader;

@FeignClient(value = "transaction", url = "http://localhost:3020/api/v1")
@Service
public interface TransactionServiceConsumer {
    @DeleteMapping("accounts/{accountId}/transactions")
    public ResponseEntity<String>deleteAllTransactionsByAccountId(@PathVariable("accountId") String accountId,
                                                               @RequestHeader(value = "userid") String headerUserId);

}
