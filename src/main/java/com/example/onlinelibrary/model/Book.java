package com.example.onlinelibrary.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import javax.validation.constraints.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ISBN;

    @NotNull
    private String title;

    @Temporal(TemporalType.DATE)
    private Date pubDate;

    private int pubId;

    @NotNull
    private int cost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @NotNull
    private int availableNumber;

}
