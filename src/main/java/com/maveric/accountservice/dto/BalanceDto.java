package com.maveric.accountservice.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.Date;

@Getter
@Builder
@AllArgsConstructor
public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private String currency;
    private Date createdAt;
    private Date updatedAt;
}
