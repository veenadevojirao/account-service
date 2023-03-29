package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Currency;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@Builder
@AllArgsConstructor

public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private Currency currency;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt;


    public BalanceDto() {

    }
}
