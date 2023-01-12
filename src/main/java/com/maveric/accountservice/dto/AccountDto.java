package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)

public class AccountDto {
    @Id
    @NotBlank(message = "Id is mandatory")
    private String _id;

    private Type type;

    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;


//    private String Balance;
    private Date createdAt;
    private Date updatedAt;
//private  String Balance;

}
