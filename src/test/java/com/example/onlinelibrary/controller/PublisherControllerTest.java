package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Publisher;
import com.example.onlinelibrary.repository.PublisherRepository;
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
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;


import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class PublisherControllerTest {

    @Autowired
    PublisherController publisherController;

    @LocalServerPort
    private int port; //Порт на котором запустится тестовый класс

    @Autowired
    private TestRestTemplate restTemplate; // Позволяет делать HTTP запросы

    @Autowired
    PublisherRepository publisherRepository;


    private final static ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void findAllPublisher() {
        assertNotNull(this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher", Publisher[].class));
        ResponseEntity<Publisher[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher", Publisher[].class);
//        assertEquals(responseEntity.getStatusCode(), 200);
        assertEquals(publisherRepository.findAll().size(), responseEntity.getBody().length);
        assertEquals(publisherRepository.findAll(), Arrays.asList(responseEntity.getBody()));

    }

    @Test
    void findById() {
        assertNotNull(this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/1", Publisher.class));
        ResponseEntity<Publisher> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/1", Publisher.class);
        assertEquals(publisherRepository.findById(1), responseEntity.getBody());
    }

    @Test
        //,,,,,,,,
    void findByName() {
        assertNotNull(this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/name/Test name 1", Publisher[].class));
        ResponseEntity<Publisher[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/name/Test name 1", Publisher[].class);
        assertEquals(publisherRepository.findByName("Test name 1"), Arrays.asList(responseEntity.getBody()));
        assertNotEquals(publisherRepository.findByName("Not Equals"), Arrays.asList(responseEntity.getBody()));
    }

    @Test
    void findByContact() {
        assertNotNull((this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/contact/Test contact 1", Publisher[].class)));
        ResponseEntity<Publisher[]> responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/contact/Test contact 1", Publisher[].class);
       // assertEquals(responseEntity.getBody().length > 0);
        assertEquals(publisherRepository.findByContact("Test contact 1"), Arrays.asList(responseEntity.getBody()));
        assertNotEquals(publisherRepository.findByContact("Error Contact"), Arrays.asList(responseEntity.getBody()));

    }

    @Test
    void findPublisherByISBN() {
        assertNotNull(this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/isbn/111111aaaaaaa", Publisher[].class));
        ResponseEntity <Publisher[]>  responseEntity = this.restTemplate
                .getForEntity("http://localhost:" + port + "api/publisher/isbn/111111aaaaaaa", Publisher[].class);
        assertThat(publisherRepository.findPublisherByISBN("111111aaaaaaa"), hasSize(1));
        assertNotEquals(publisherRepository.findPublisherByISBN("Error ISMN"), responseEntity.getBody());

    }

    @Test
    void createPublisher() throws JsonProcessingException {
        Publisher publisher = new Publisher(6, "Test contact 1000", "Test name 1000", new LinkedHashSet<>());
        String expected = objectMapper.writeValueAsString(publisher);
        ResponseEntity<Publisher> responseEntity = restTemplate.postForEntity("http://localhost:" + port + "api/publisher", publisher, Publisher.class);
        assertAll(
                () -> assertEquals(HttpStatus.OK, responseEntity.getStatusCode()),
                () -> assertNotNull(expected),
                () -> assertEquals(publisher, responseEntity.getBody())
        );
    }

    @Test
    void deletePublisherById() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(null, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/publisher/5", HttpMethod.DELETE, entity, String.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted publisher with id: 5", responseEntity.getBody());
    }

    @Test
    void updatePublisher() throws JsonProcessingException {
        Publisher publisher = new Publisher(4, "name", "contact", new LinkedHashSet<>());
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(publisher), headers);
        ResponseEntity<Publisher> responseEntity = restTemplate.exchange("http://localhost:" + port + "/api/publisher/4", HttpMethod.PUT, entity, Publisher.class);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    private void printJSON(Object object) {
        String result;
        try {
            result = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(object);
            System.out.println(result);
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }
}