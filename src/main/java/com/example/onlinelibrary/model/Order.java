package com.example.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Temporal(TemporalType.DATE)
    private Date orderDate;

    @Temporal(TemporalType.DATE)
    private Date shipDate;

    @NotNull
    @Embedded
    private Address address;

    @Temporal(TemporalType.DATE)
    private Date leaseStartDate;

    @Temporal(TemporalType.DATE)
    private Date leaseEndDate;

    private boolean preOrdered;


}
