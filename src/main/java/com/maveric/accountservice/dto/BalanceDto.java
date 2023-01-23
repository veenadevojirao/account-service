package com.maveric.accountservice.dto;

import java.util.Date;

public class BalanceDto {
    private String  _id;
    private String accountId;
    private Number amount;
    private String currency;
    private Date createdAt=new Date();
    private Date updatedA = new Date();
}
