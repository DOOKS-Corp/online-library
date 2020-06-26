package com.example.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerPaymentMethod {

    @NotNull
    @Enumerated(EnumType.STRING)
    private PaymentSystem paymentSystem;

    @NotNull
    @Size(min = 16, max = 16)
    private String cardNumber;

    @NotNull
    @Size(min = 2, max = 30)
    private String cardHoldersLastName;

    @NotNull
    @Size(min = 2, max = 30)
    private String cardHoldersFirstName;

    @Column(columnDefinition = "DATE")
    private LocalDate cardValidUntil;

}
