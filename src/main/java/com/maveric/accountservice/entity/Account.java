package com.maveric.accountservice.entity;

import com.maveric.accountservice.enums.Type;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "account")

public class Account {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String _id;
    @Column(nullable = false, length = 512, unique = true)
    @NotBlank(message = "Customer Id is mandatory")
    private String customerId;
    @NotNull(message = "Type is mandatory - 'SAVINGS' or 'CURRENT'")
    private Type type;
    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = false)
    private Date createdAt=new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @Column(updatable = true)
    private Date updatedAt =new Date();


}
