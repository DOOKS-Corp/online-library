package com.example.onlinelibrary.model;

import lombok.*;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
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

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
                cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "orders_books",
               joinColumns = { @JoinColumn(name = "book_id")},
               inverseJoinColumns = { @JoinColumn(name = "order_id")})
    private Set<Order> orders = new HashSet<>();

}
