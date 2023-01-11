package com.maveric.accountservice.dto;

import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Currency;

public class Balance {
    @Getter
    @Setter

    @AllArgsConstructor
    @NoArgsConstructor
    public class balance {
        @Id
        private String _id;
        private String amount;
        private Currency currency;
        private String accountId;
    }
}