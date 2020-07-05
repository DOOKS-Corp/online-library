package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.model.Book;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class AuthorRepositoryTest {

    @Autowired
    AuthorRepository authorRepository;

    @Test
    void findAllByFirstName() {
        final String firstName = "Author name 1";

        List<Author> author = authorRepository.findAllByFirstName(firstName);
        assertNotNull(author);

        boolean isTrue = author.stream().allMatch(a -> a.getFirstName().equals(firstName));
        assertTrue(isTrue);
    }

    @Test
    void findAllByLastName() {
        final String lastName = "Author lastname 1";

        List<Author> author = authorRepository.findAllByLastName(lastName);
        assertNotNull(author);

        boolean isTrue = author.stream().allMatch(a -> a.getLastName().equals(lastName));
        assertTrue(isTrue);
    }

    @Test
    void findAllByFirstNameAndLastName() {
        final String firstName = "Author name 3";
        final String lastName = "Author last 3";

        List<Author> author = authorRepository.findAllByFirstNameAndLastName(firstName, lastName);
        assertNotNull(author);

        boolean isTrue = author.stream()
                .allMatch(author1 -> author1.getFirstName().equals(firstName)
                        && author1.getLastName().equals(lastName));
        assertTrue(isTrue);
    }


    @Test
    void findByISBN() {
        final String iSBN = "222222bbbbbbb";

        List<Author> authorList = authorRepository.findByISBN(iSBN);
        assertNotNull(authorList);

        boolean isTrue = authorList.stream()
                .allMatch(author -> author.getBooks()
                        .stream().anyMatch(book -> book.getISBN().equals(iSBN)));
        assertTrue(isTrue);
    }

    @Test
    void findAllByTitle() {
        final String title = "Test title 2";

        List<Author> authorList = authorRepository.findAllByTitle(title);
        assertNotNull(authorList);

        boolean isTrue = authorList.stream()
                         .allMatch(author -> author.getBooks()
                                    .stream()
                                     .anyMatch(book -> book.getTitle().equals(title)));
        assertTrue(isTrue);
    }
}