package com.example.onlinelibrary.controller;


import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Publisher;
import com.example.onlinelibrary.repository.BookRepository;
import com.example.onlinelibrary.repository.PublisherRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jooq.AutoConfigureJooq;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerTestMockMVC {
    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @MockBean
    PublisherRepository publisherRepository;
    BookRepository bookRepository;
    Publisher publisher1 = null;
    Publisher publisher2 = null;
    List<Publisher> publisherList = new ArrayList<>();

    @BeforeEach
    void setUp() {
        publisher1 = new Publisher(1, "name1", "contact1", new LinkedHashSet<>());
        publisher2 = new Publisher(2, "name2", "contact2", new LinkedHashSet<>());
        publisherList.add(publisher1);
        publisherList.add(publisher2);
    }

    @AfterEach
    void tearDown() {
        publisher1 = null;
        publisher2 = null;
        publisherList.remove(publisher1);
        publisherList.remove(publisher2);

    }

    @Test
    void findAllPublisher() throws Exception {
        when(publisherRepository.findAll()).thenReturn(publisherList);
        mockMvc.perform(get("/api/publisher"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("contact1")))
                .andExpect(jsonPath("$[1].id", Matchers.is(2)))
                .andExpect(jsonPath("$[1].name", Matchers.is("name2")))
                .andExpect(jsonPath("$[1].contact", Matchers.is("contact2")));
    }

    @Test
    void findById() throws Exception {
        when(publisherRepository.findById(1)).thenReturn(publisher1);
        mockMvc.perform(get("/api/publisher/{id}", 1))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("name1")))
                .andExpect(jsonPath("$.contact", Matchers.is("contact1")));
    }

    @Test
    void findByName() throws Exception {
        when(publisherRepository.findByName("name1")).thenReturn(publisherList);
        mockMvc.perform(get("/api/publisher/name/{name}", "name1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("contact1")));
    }

    @Test
    void findByContact() throws Exception {
        when(publisherRepository.findByContact("contact1")).thenReturn(publisherList);
        mockMvc.perform(get("/api/publisher/contact/{contact}", "contact1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("contact1")));
    }

    @Test
    void findPublisherByISBN() throws Exception {
        when(publisherRepository.findPublisherByISBN("111111aaaaaaa")).thenReturn(publisherList);
        mockMvc.perform(get("/api/publisher/isbn/111111aaaaaaa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("name1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("contact1")));
    }

    @Test
    void createPublisher() throws Exception {
        when(publisherRepository.save(any(Publisher.class))).thenReturn(publisher1);
        mockMvc.perform(post("/api/publisher")
                .content(objectMapper.writeValueAsString(publisher1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("name1")))
                .andExpect(jsonPath("$.contact", Matchers.is("contact1")));
    }

    @Test
    void deletePublisherById() throws Exception {
        doNothing().when(publisherRepository).deleteById(1);
        mockMvc.perform(delete("/api/publisher/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.is("Deleted publisher with id: 1")));
      verify(publisherRepository, times(1)).deleteById(1);

    }

    @Test
    void updatePublisher() throws Exception {
        when(publisherRepository.save(publisher1)).thenReturn(publisher2);
        when(publisherRepository.findById(1)).thenReturn(publisher1);
        mockMvc.perform(put("/api/publisher/1")
                .content(objectMapper.writeValueAsString(publisher1))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(2)))
                .andExpect(jsonPath("$.name", Matchers.is("name2")))
                .andExpect(jsonPath("$.contact", Matchers.is("contact2")));
    }
}