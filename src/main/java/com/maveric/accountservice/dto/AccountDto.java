package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String _id;
    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;
    @NotBlank(message = "Customer Id is mandatory")

    private String customerId;



    private Date createdAt;
    private Date updatedAt;

}