package com.example.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "customers")
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @Embedded
    private CustomerEmail customerEmail;

    @ElementCollection(fetch = FetchType.LAZY)
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    private Set<CustomerAddress> addresses = new HashSet<>();

    @Embedded
    private CustomerPaymentMethod customerPaymentMethod;

}
