package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class AuthorControllerTest {

    @Autowired
    AuthorController authorController;

    @Autowired
    AuthorRepository authorRepository;

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    private final static ObjectMapper objectMapper = new ObjectMapper();


    @Test
    void getAuthorList() {
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/author", Author[].class);
        assertNotNull(responseEntity);

        Author[] authors = responseEntity.getBody();
        assertNotNull(authors);

        assertIterableEquals(authorRepository.findAll(), Arrays.asList(authors));
    }

    @Test
    void findAuthorById() {
        final int authorId = 1;
        ResponseEntity<Author> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/author/" + authorId, Author.class);
        assertNotNull(responseEntity);

        Author author = authorRepository.findById(authorId).get();

        assertNotNull(author);
        assertEquals(author, responseEntity.getBody());
    }

    @Test
    void findFirstName() {
        String authorFirstName = "Author name 1";
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/author/findAuthorNames/" + authorFirstName, Author[].class);
        assertNotNull(responseEntity);

        Author[] authors = responseEntity.getBody();
        assertNotNull(authors);

        assertEquals(authorRepository.findAllByFirstName(authorFirstName),
                Arrays.asList(authors));
    }

    @Test
    void findLastName() {
        final String authorLastName = "Author lastname 1";
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/author/findAuthorLastName/" + authorLastName, Author[].class);
        assertNotNull(responseEntity);

        Author[] authors = responseEntity.getBody();
        assertNotNull(authors);

        assertEquals(authorRepository.findAllByLastName(authorLastName),
                Arrays.asList(authors));

    }

    @Test
    void findFirstAndLastNames() {
        final String authorFirstName = "Author name 1";
        final String authorLastName = "Author lastname 1";
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "/author/fullNameAuthor/" + authorFirstName + "/" + authorLastName, Author[].class);
        assertNotNull(responseEntity);

        Author[] author = responseEntity.getBody();
        assertNotNull(author);

        assertEquals(authorRepository.findAllByFirstNameAndLastName(authorFirstName, authorLastName),
                Arrays.asList(author));
    }

    @Test
    void findISBN() {
        final String findAuthorISBN = "222222bbbbbbb";
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "author/getISBN/" + findAuthorISBN, Author[].class);
        assertNotNull(responseEntity);

        Author[] author = responseEntity.getBody();
        assertNotNull(author);

        assertEquals(authorRepository.findByISBN(findAuthorISBN),
                Arrays.asList(author));
    }

    @Test
    void findTitle() {
        final String findAuthorTitle = "Test title 2";
        ResponseEntity<Author[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "author/getTitle/" + findAuthorTitle, Author[].class);
        assertNotNull(responseEntity);

        Author[] title = responseEntity.getBody();
        assertNotNull(title);
        assertEquals(authorRepository.findAllByTitle(findAuthorTitle),
                Arrays.asList(title));
    }

    @Test
    void createAuthors() {
        final int authorID = 6;
        Author author = new Author(authorID, "First Name 1", "Last Name 1", new HashSet<>());

        ResponseEntity<Author> responseEntity =
                restTemplate.postForEntity("http://localhost:" + port + "/author", author, Author.class);
        assertNotNull(responseEntity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

    }

    @Test
    void updateAuthor() throws JsonProcessingException {
        final int authorID = 2;
        Author author = new Author(authorID, "NEW", "NEW", new HashSet<>());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(author), headers);
        ResponseEntity<Author> responseEntity =
                restTemplate.exchange("http://localhost:" + port + "/author/" + authorID, HttpMethod.PUT, entity, Author.class);
        Author authorBody = responseEntity.getBody();

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(author, authorBody);
    }

    @Test
    void deleteAuthor() {
        final int authorId = 1;
        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());

        ResponseEntity<String> responseEntity =
                restTemplate.exchange("http://localhost:" + port + "/author/" + authorId, HttpMethod.DELETE, entity, String.class);
        String messages = responseEntity.getBody();

        assertEquals(Optional.empty(), authorRepository.findById(authorId));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Delete author with id " + authorId, messages);

    }

    private static void printJSON(Object object) {
        String result;
        try {
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}