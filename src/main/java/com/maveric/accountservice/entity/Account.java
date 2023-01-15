package com.maveric.accountservice.entity;

import com.maveric.accountservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Account {


    private String _id;

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