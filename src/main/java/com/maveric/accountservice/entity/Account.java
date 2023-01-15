package com.maveric.accountservice.entity;

import com.maveric.accountservice.enums.Type;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account")

public class Account{
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotBlank(message = "Type should be mandatory")
    private Type type;
    //    private String Balance;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();


}