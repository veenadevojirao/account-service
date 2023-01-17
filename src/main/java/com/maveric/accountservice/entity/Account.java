package com.maveric.accountservice.entity;

import com.maveric.accountservice.enums.Type;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import java.util.Date;
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Account {


    private String _id;
    @NotNull(message = "Customer Id is mandatory")
    private String customerId;

    private Type type;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();


//    public static Object builder() {
//        return builder();
//    }
}