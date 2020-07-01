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
    void findAllByPublisherName() {
        final String publisherName = "Test name 1";

        List<Book> bookList = bookRepository.findAllByPublisherName(publisherName);

        assertNotNull(bookList);

        boolean isValid = bookList.stream()
                .allMatch(b -> b.getPublisher().getName().equals(publisherName));

        assertTrue(isValid);
    }

    @Test
    void findAllByCostBetween() {
        final int leastCost = 50;
        final int greatestCost = 150;

        List<Book> bookList = bookRepository.findAllByCostBetween(leastCost, greatestCost);

        assertNotNull(bookList);

        boolean isValid = bookList.stream()
                .allMatch(b -> b.getCost() <= greatestCost && b.getCost() >= leastCost);

        assertTrue(isValid);
    }

    @Test
    void findAllByCostBefore() {
        final int greatestCost = 150;

        List<Book> bookList = bookRepository.findAllByCostBefore(greatestCost);

        assertNotNull(bookList);

        boolean isValid = bookList.stream()
                .allMatch(b -> b.getCost() < greatestCost);

        assertTrue(isValid);
    }

    @Test
    void findAllByCostAfter() {
        final int leastCost = 50;

        List<Book> bookList = bookRepository.findAllByCostAfter(leastCost);

        assertNotNull(bookList);

        boolean isValid = bookList.stream()
                .allMatch(b -> b.getCost() > leastCost);

        assertTrue(isValid);
    }
}