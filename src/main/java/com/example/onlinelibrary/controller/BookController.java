package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.time.LocalDate;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/book")
public class BookController {

    private final BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/{isbn}")
    public Book getBookByISBN(@PathVariable String isbn) {
        return bookRepository.findBookByISBN(isbn).orElseThrow(NoSuchElementException::new);
    }

    @GetMapping("/author/first-name/{firstName}")
    public List<Book> getBooksByAuthorFirstName(@PathVariable String firstName) {
        return bookRepository.findBookByAuthorFirstName(firstName);
    }

    @GetMapping("/author/last-name/{lastName}")
    public List<Book> getBooksByAuthorLastName(@PathVariable String lastName) {
        return bookRepository.findBookByAuthorLastName(lastName);
    }

    @GetMapping("/title/{title}")
    public List<Book> getBooksByTitle(@PathVariable String title) {
        return bookRepository.findAllByTitle(title);
    }

    @GetMapping("/publication-date/{publicationDate}")
    public List<Book> getBooksByPublicationDate(@PathVariable String publicationDate) {
        LocalDate date = LocalDate.parse(publicationDate);
        return bookRepository.findAllByPubDate(date);
    }

    @GetMapping("/publication-date/{startDate}/{endDate}")
    public List<Book> getBooksByPublicationDateBetween(@PathVariable String startDate,
                                                       @PathVariable String endDate) {
        LocalDate from = LocalDate.parse(startDate);
        LocalDate to = LocalDate.parse(endDate);

        return bookRepository.findAllByPubDateBetween(from, to);
    }

    @GetMapping("/publisher/name/{publisherName}")
    public List<Book> getBooksByPublisherName(@PathVariable String publisherName) {
        return bookRepository.findAllByPublisherName(publisherName);
    }

    @GetMapping("/cost/{start}/{end}")
    public List<Book> getBooksByCostBetween(@PathVariable Integer start,
                                            @PathVariable Integer end) {
        return bookRepository.findAllByCostBetween(start, end);
    }

    @GetMapping("/cost/before/{cost}")
    public List<Book> getBooksByCostBefore(@PathVariable Integer cost) {
        return bookRepository.findAllByCostBefore(cost);
    }

    @GetMapping("/cost/after/{cost}")
    public List<Book> getBooksByCostAfter(@PathVariable Integer cost) {
        return bookRepository.findAllByCostAfter(cost);
    }

    @PostMapping
    public Book addBook(@RequestBody Book requestBook) {
        Book book = new Book();

        book.setISBN(requestBook.getISBN());
        book.setTitle(requestBook.getTitle());
        book.setAuthors(requestBook.getAuthors());
        book.setCategory(requestBook.getCategory());
        book.setPubDate(requestBook.getPubDate());
        book.setOrderItems(requestBook.getOrderItems());
        book.setPublisher(requestBook.getPublisher());
        book.setCost(requestBook.getCost());
        book.setAvailableNumber(requestBook.getAvailableNumber());

        return bookRepository.save(book);
    }

    @PutMapping("/{isbn}")
    public Book updateBook(@PathVariable String isbn,
                           @RequestBody Book requestBook) {
        Book updatedBook = bookRepository.findBookByISBN(isbn)
                .orElseThrow(NoSuchElementException::new);

        updatedBook.setAuthors(requestBook.getAuthors());
        updatedBook.setTitle(requestBook.getTitle());
        updatedBook.setPubDate(requestBook.getPubDate());
        updatedBook.setCategory(requestBook.getCategory());
        updatedBook.setOrderItems(requestBook.getOrderItems());
        updatedBook.setPublisher(requestBook.getPublisher());
        updatedBook.setCost(requestBook.getCost());
        updatedBook.setAvailableNumber(requestBook.getAvailableNumber());

        return bookRepository.save(updatedBook);
    }

    @DeleteMapping("/{isbn}")
    public String deleteBook(@PathVariable String isbn) {
        bookRepository.deleteById(isbn);
        return "Book " + isbn + " was deleted";
    }
}



