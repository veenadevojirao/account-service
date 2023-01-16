package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;

import javax.validation.constraints.NotBlank;
import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto{
    private String _id;

    private Type type;

    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;



    private Date createdAt;
    private Date updatedAt;




}