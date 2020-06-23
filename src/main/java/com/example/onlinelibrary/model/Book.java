package com.example.onlinelibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedHashSet;
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
    @OneToMany(mappedBy = "book")
    private Set<OrdersBooks> ordersBooks = new LinkedHashSet<>();

//    @ToString.Exclude
//    @EqualsAndHashCode.Exclude
//    @ManyToMany(fetch = FetchType.LAZY,
//                cascade = { CascadeType.PERSIST, CascadeType.MERGE })
//    @JoinTable(name = "orders_books",
//               joinColumns = { @JoinColumn(name = "book_id")},
//               inverseJoinColumns = { @JoinColumn(name = "order_id")})
//    private Set<Order> orders = new HashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "books")
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @OnDelete(action = OnDeleteAction.CASCADE)
    @JsonIgnore
    private Publisher publisher;

}
