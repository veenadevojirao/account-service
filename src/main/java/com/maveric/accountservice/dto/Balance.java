package com.maveric.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Currency;
import java.util.Date;

public class Balance {
    @Getter
    @Setter

    @AllArgsConstructor
    @NoArgsConstructor
    public class balance {
        private String _id;
        private String amount;
        private Currency currency;
        private String accountId;
        @Temporal(TemporalType.TIMESTAMP)
        @Column(updatable = false)
        private Date createdAt=new Date();

        @Temporal(TemporalType.TIMESTAMP)
        @Column(updatable = true)
        private Date updatedAt =new Date();
    }
}
