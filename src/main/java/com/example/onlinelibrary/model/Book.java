package com.example.onlinelibrary.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;
import java.time.LocalDate;
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
    @NotNull
    @Size(min = 10, max = 13)
    private String ISBN;

    @NotNull
    @Size(min = 2, max = 100)
    private String title;

    @Column(columnDefinition = "DATE")
    private LocalDate pubDate;

    @NotNull
    @Min(0)
    private int cost;

    @NotNull
    @Enumerated(EnumType.STRING)
    private BookCategory category;

    @NotNull
    private int availableNumber;

    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @OneToMany(mappedBy = "book")
    private Set<OrderItems> orderItems = new LinkedHashSet<>();

    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE },
            mappedBy = "books")
    @JsonIgnore
    private Set<Author> authors = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JsonIgnore
    private Publisher publisher;

}
