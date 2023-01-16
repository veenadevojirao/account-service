package com.maveric.accountservice.dto;

import com.maveric.accountservice.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor(access= AccessLevel.PUBLIC)

public class AccountDto {
    @Id
    private String _id;
    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;

    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;


    private String Balance;
    private Date createdAt;
    private Date updatedAt;


}
