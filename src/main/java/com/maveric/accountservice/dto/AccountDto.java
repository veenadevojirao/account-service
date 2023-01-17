package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto{
    private String _id;
    @Column(nullable = false, length = 512, unique = true)
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;
    private Date createdAt;
    private Date updatedAt;

    private BalanceDto balance;



}