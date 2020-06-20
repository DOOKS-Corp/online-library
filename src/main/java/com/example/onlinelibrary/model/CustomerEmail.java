package com.example.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class CustomerEmail {

    @NotNull
    @Email
    @Size(max = 50)
    private String email;
}
