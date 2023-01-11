package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;

import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)

public class AccountDto {
    @Id
    private String _id;

    private Type type;


    private String customerId;


//    private String Balance;
    private Date createdAt;
    private Date updatedAt;
//private  String Balance;

}
