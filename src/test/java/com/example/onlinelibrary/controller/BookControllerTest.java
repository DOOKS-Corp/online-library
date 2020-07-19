package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.beans.RntBookResWrp;
import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.BookCategory;
import com.example.onlinelibrary.model.Publisher;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.example.onlinelibrary.repository.BookRepository;
import com.example.onlinelibrary.repository.PublisherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class BookControllerTest {

    @Autowired
    BookRepository bookRepository;

    @Autowired
    PublisherRepository publisherRepository;

    @Autowired
    AuthorRepository authorRepository;

    @LocalServerPort
    int port;

    @Autowired
    TestRestTemplate restTemplate;

    List<Book> testBookList;

    @BeforeEach
    void setUp() {
        bookRepository.deleteAll();
        publisherRepository.deleteAll();
        authorRepository.deleteAll();
        List<Book> bookList = generateBookList(10);

        bookList.forEach(book -> book.setPublisher(publisherRepository.save(book.getPublisher())));
        testBookList = bookRepository.saveAll(bookList);
    }

    @AfterEach
    void tearDown() {
        bookRepository.deleteAll(testBookList);
        testBookList.forEach(book -> {
            publisherRepository.deleteById(book.getPublisher().getId());
            book.getAuthors().forEach(author -> authorRepository.deleteById(author.getId()));
        });
    }

    @Test
    void getAllBooks() {
        ResponseEntity<Book[]> responseEntity = this.restTemplate
                .getForEntity(getBookUrlFor("/"), Book[].class);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(bookRepository.findAll(), Arrays.asList(books));
    }

    @Test
    void getOldBookByISBN() {
        Book testBook = testBookList.get(0);
        ResponseEntity<Book> responseEntity = restTemplate.getForEntity(
                getOldBookUrlFor("/" + testBook.getISBN()),
                Book.class
        );

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testBook, responseEntity.getBody());
    }

    @Test
    void getBookByISBN() {
        Book testBook = testBookList.get(0);
        ResponseEntity<RntBookResWrp> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/" + testBook.getISBN()),
                RntBookResWrp.class
        );

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(testBook.getISBN(), responseEntity.getBody().getBook().getIsbn());
    }

    @Test
    void getBooksByAuthorFirstName() {
        Author author = testBookList.get(0).getAuthors().stream()
                .findAny().orElseThrow(RuntimeException::new);

        List<Book> authorsBookList = testBookList.stream()
                .filter(book -> book.getAuthors().contains(author))
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = this.restTemplate
                .getForEntity(
                        getBookUrlFor("/author/first-name/" + author.getFirstName()),
                        Book[].class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(authorsBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByAuthorLastName() {
        Author author = testBookList.get(0).getAuthors().stream()
                .findAny().orElseThrow(RuntimeException::new);

        List<Book> authorsBookList = testBookList.stream()
                .filter(book -> book.getAuthors().contains(author))
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = this.restTemplate
                .getForEntity(
                        getBookUrlFor("/author/last-name/" + author.getLastName()),
                        Book[].class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(authorsBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByTitle() {
        String title = testBookList.get(0).getTitle();

        List<Book> titlesBookList = testBookList.stream()
                .filter(book -> book.getTitle().equals(title))
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/title/" + title),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(titlesBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByPublicationDate() {
        LocalDate pubDate = testBookList.get(0).getPubDate();

        List<Book> dateBookList = testBookList.stream()
                .filter(book -> book.getPubDate().isEqual(pubDate))
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/publication-date/" + pubDate),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(dateBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByPublicationDateBetween() {
        long q = 1;
        LocalDate existsPubDate = testBookList.get(0).getPubDate();
        LocalDate fromPubDate = existsPubDate.minusMonths(q);
        LocalDate toPubDate = existsPubDate.plusMonths(q);


        List<Book> dateBetweenBookList = testBookList.stream()
                .filter(book -> {
                    LocalDate currentDate = book.getPubDate();
                    return (currentDate.isAfter(fromPubDate) || currentDate.isEqual(fromPubDate))
                            && (book.getPubDate().isBefore(toPubDate) || currentDate.isEqual(toPubDate));
                })
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor(String.format("/publication-date/%s/%s", fromPubDate, toPubDate)),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(dateBetweenBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByPublisherName() {
        String publisherName = testBookList.get(0).getPublisher().getName();

        List<Book> publisherNameBookList = testBookList.stream()
                .filter(book -> book.getPublisher().getName().equals(publisherName))
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/publisher/name/" + publisherName),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(publisherNameBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByCostBetween() {
        int q = 10;
        int existsCost = testBookList.get(0).getCost();
        int fromCost = existsCost - q;
        int toCost = existsCost + q;

        List<Book> costBetweenBookList = testBookList.stream()
                .filter(book -> book.getCost() >= fromCost && book.getCost() <= toCost)
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor(String.format("/cost/%s/%s", fromCost, toCost)),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(costBetweenBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByCostBefore() {
        int q = 10;
        int existsCost = testBookList.get(0).getCost();
        int toCost = existsCost + q;

        List<Book> costBeforeBookList = testBookList.stream()
                .filter(book -> book.getCost() < toCost)
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/cost/before/" + toCost),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(costBeforeBookList, Arrays.asList(books));
    }

    @Test
    void getBooksByCostAfter() {
        int q = 10;
        int existsCost = testBookList.get(0).getCost();
        int fromCost = existsCost - q;

        List<Book> costAfterBookList = testBookList.stream()
                .filter(book -> book.getCost() > fromCost)
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/cost/after/" + fromCost),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(costAfterBookList, Arrays.asList(books));
    }

    @Test
    void getBookByCategory() {
        BookCategory requestCategory = testBookList.get(0).getCategory();

        List<Book> categoryBookList = testBookList.stream()
                .filter(book -> book.getCategory() == requestCategory)
                .collect(Collectors.toList());

        ResponseEntity<Book[]> responseEntity = restTemplate.getForEntity(
                getBookUrlFor("/category/" + requestCategory),
                Book[].class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book[] books = responseEntity.getBody();
        assertNotNull(books);

        assertIterableEquals(categoryBookList, Arrays.asList(books));
    }

    @Test
    void addBook() {
        Set<Author> authorSet = testBookList.get(0).getAuthors();
        Publisher publisher = testBookList.get(0).getPublisher();
        String id = "AddT";

        Book requestBook = new Book();
        requestBook.setISBN("GTestISBN" + id);
        requestBook.setTitle("GTest title " + id);
        requestBook.setCategory(BookCategory.ACTION);
        requestBook.setOrderItems(null);
        requestBook.setCost(120);
        requestBook.setAvailableNumber(10);
        requestBook.setPubDate(LocalDate.now());

        requestBook.setAuthors(authorSet);
        requestBook.setPublisher(publisher);

        ResponseEntity<Book> responseEntity =
                restTemplate.postForEntity(getBookUrlFor("/"), requestBook, Book.class);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book responseBook = responseEntity.getBody();
        assertEquals(requestBook, responseBook);

        Book savedBook = bookRepository.findBookByISBN(requestBook.getISBN()).orElseThrow(RuntimeException::new);
        assertEquals(requestBook, savedBook);
    }

    @Test
    void updateBook() throws JsonProcessingException {
        Book requestBook = testBookList.get(0);
        requestBook.setTitle("Updated title");

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.registerModule(new JavaTimeModule());
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(requestBook), headers);

        ResponseEntity<Book> responseEntity = restTemplate.exchange(
                getBookUrlFor("/"+ requestBook.getISBN()),
                HttpMethod.PUT,
                entity,
                Book.class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        Book responseBook = responseEntity.getBody();
        assertEquals(requestBook, responseBook);

        Book savedBook = bookRepository.findBookByISBN(requestBook.getISBN()).orElseThrow(RuntimeException::new);
        assertEquals(requestBook, savedBook);
    }

    @Test
    void deleteBook() {
        String isbn = testBookList.get(0).getISBN();
        ResponseEntity<String> responseEntity = restTemplate.exchange(
                getBookUrlFor("/" + isbn),
                HttpMethod.DELETE, null,
                String.class
        );
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        String pattern = "Book " + isbn + " was deleted";
        assertEquals(pattern, responseEntity.getBody());

        boolean isExists = bookRepository.existsById(isbn);
        assertFalse(isExists);
    }

    private List<Book> generateBookList(int length) {
        return Stream
                .iterate(1, n -> n + 1)
                .limit(length)
                .map(num -> {
                    Book book = new Book();
                    book.setISBN("GTestISBN" + num);
                    book.setTitle("GTest title " + num);
                    book.setCategory(BookCategory.values()[num % BookCategory.values().length]);
                    book.setOrderItems(null);
                    book.setCost(num * 10);
                    book.setAvailableNumber(num);
                    book.setPubDate(LocalDate.now().minusMonths(num));

                    Author author = new Author();
                    author.setFirstName("GAuthor fname " + num);
                    author.setLastName("GAuthor lname " + num);
                    author.setBooks(new HashSet<>(Collections.singletonList(book)));

                    book.setAuthors(new HashSet<>(Collections.singletonList(author)));

                    Publisher publisher = new Publisher();
                    publisher.setName("GPublisher name " + num);
                    publisher.setContact("GTest contact " + num);
                    publisher.setBooks(new HashSet<>(Collections.singletonList(book)));

                    book.setPublisher(publisher);
                    return book;
                })
                .collect(Collectors.toList());

    }

    private String getBookUrlFor(String postfix) {
        return String.format("http://localhost:%d/api/book%s", port, postfix);
    }

    private String getOldBookUrlFor(String postfix) {
        return String.format("http://localhost:%d/api/book/old%s", port, postfix);
    }
}