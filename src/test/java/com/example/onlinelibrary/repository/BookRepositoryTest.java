package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Book;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class BookRepositoryTest {

    @Autowired
    BookRepository bookRepository;

    @Test
    void findBookByISBN() {
        final String isbnTest = "111111aaaaaaa";

        Book book = bookRepository.findBookByISBN(isbnTest);

        assertNotNull(book);
        assertEquals(isbnTest, book.getISBN());
    }

    @Test
    void findAllByAuthors() {

    }

    @Test
    void findBookByAuthorFirstName() {
        final String authorName = "Author name 1";

        List<Book> authorsBooks = bookRepository.findBookByAuthorFirstName(authorName);
        System.out.println(authorsBooks);
        assertNotNull(authorsBooks);

        boolean isValid = authorsBooks
                .stream()
                .allMatch(e -> e.getAuthors()
                        .stream().anyMatch(a -> a.getFirstName().equals(authorName)));
        assertTrue(isValid);
    }

    @Test
    void findBookByAuthorLastName() {
        final String authorLastName = "Author lastname 1";

        List<Book> authorsBooks = bookRepository.findBookByAuthorLastName(authorLastName);
        System.out.println(authorsBooks);
        assertNotNull(authorsBooks);

        boolean isValid = authorsBooks
                .stream()
                .allMatch(e -> e.getAuthors()
                        .stream().anyMatch(a -> a.getLastName().equals(authorLastName)));
        assertTrue(isValid);
    }

    @Test
    void findAllByTitle() {
        final String title = "Test title 1";

        List<Book> bookList = bookRepository.findAllByTitle(title);

        assertNotNull(bookList);
        assertTrue(bookList.stream().allMatch(e -> e.getTitle().equals(title)));
    }

    @Test
    void findAllByPubDate() {
        final LocalDate date = LocalDate.of(2020, 8, 18);

        List<Book> bookList = bookRepository.findAllByPubDate(date);

        assertNotNull(bookList);
        assertTrue(bookList.stream().allMatch(e -> e.getPubDate().isEqual(date)));
    }

    @Test
    void findAllByPubDateBetween() {
        final LocalDate startDate = LocalDate.of(2002, 8, 18);
        final LocalDate endDate = LocalDate.of(2020, 8, 18);

        List<Book> bookList = bookRepository.findAllByPubDateBetween(startDate, endDate);

        assertNotNull(bookList);

        boolean isValid = bookList
                .stream()
                .allMatch(e -> (e.getPubDate().isAfter(startDate) || e.getPubDate().isEqual(startDate))
                        && (e.getPubDate().isBefore(endDate) || e.getPubDate().isEqual(endDate)));

        assertTrue(isValid);
    }

    @Test
    void findAllByPublisher() {
    }

    @Test
    void findAllByCostBetween() {
    }

    @Test
    void findAllByCostBefore() {
    }

    @Test
    void findAllByCostAfter() {
    }
}