package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;

import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AccountDto {
    private String _id;

    private Type type;


    private String customerId;



    private Date createdAt;
    private Date updatedAt;




}