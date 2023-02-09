package com.maveric.accountservice.dto;


import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private String  id;
    private String email;
}
