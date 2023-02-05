package com.maveric.accountservice.dto;

import lombok.Data;

import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import java.lang.reflect.Type;
import java.time.LocalDateTime;
@Data
public class TransactionDto {
    private String _id;

    @NotEmpty(message = "Please enter account ID")
    private String accountId;

    @Min(value = 1, message = "Amount cannot be less then 1")
    private Number amount;

    @Enumerated(EnumType.STRING)
    private Type type;

    private LocalDateTime createdAt = LocalDateTime.now();
}
