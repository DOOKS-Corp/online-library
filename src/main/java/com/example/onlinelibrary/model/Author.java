package com.example.onlinelibrary.model;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Size(min = 2, max = 30)
    private String firstName;

    @NotNull
    @Size(min = 2, max = 30)
    private String lastName;


    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    @ManyToMany(fetch = FetchType.LAZY,
            cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "author_books",
            joinColumns = { @JoinColumn(name = "author_id")},
            inverseJoinColumns = { @JoinColumn(name = "book_ISBN")})
    private Set<Book> books = new HashSet<>();
}
