package com.example.onlinelibrary.model;

import lombok.*;

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
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;

    @NotNull
    @Size(min = 10, max = 13)
    private String phoneNumber;

    @NotNull
    @Email
    private String email;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "customer_addresses", joinColumns = @JoinColumn(name = "customer_id"))
    private Set<Address> addresses = new HashSet<>();

    @Embedded
    private CustomerPaymentMethod customerPaymentMethod;

    @ToString.Exclude
    @OneToMany(mappedBy = "customer")
    private Set<Order> orders = new HashSet<>();

}
