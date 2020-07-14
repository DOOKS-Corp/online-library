package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Publisher;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import java.util.LinkedHashSet;


import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
class PublisherControllerMock {

    @Autowired
    MockMvc mockMvc;

    ObjectMapper objectMapper = new ObjectMapper();

    @Autowired
    PublisherController publisherController;


    @Test
        //Данные из бд!!!
    void findAllPublisher() throws Exception {
        mockMvc.perform(get("/api/publisher"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(5)));
    }

    @Test
    void findById() throws Exception {
        mockMvc.perform(get("/api/publisher/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Test name 1")))
                .andExpect(jsonPath("$.contact", Matchers.is("Test contact 1")));
    }

    @Test
    void findByName() throws Exception {
        mockMvc.perform(get("/api/publisher/name/Test name 1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test name 1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("Test contact 1")));
    }

    @Test
    void findByContact() throws Exception {
        mockMvc.perform(get("/api/publisher/contact/Test contact 1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test name 1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("Test contact 1")));
    }

    @Test
    void findPublisherByISBN() throws Exception {
        mockMvc.perform(get("/api/publisher/isbn/111111aaaaaaa"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].id", Matchers.is(1)))
                .andExpect(jsonPath("$[0].name", Matchers.is("Test name 1")))
                .andExpect(jsonPath("$[0].contact", Matchers.is("Test contact 1")));
    }

    @Test
    void createPublisher() throws Exception {
        Publisher publisher = new Publisher(6, "Test new", "Test new", new LinkedHashSet<>());
        mockMvc.perform(post("/api/publisher/")
                .content(objectMapper.writeValueAsString(publisher))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id", Matchers.is(6)))
                .andExpect(jsonPath("$.name", Matchers.is("Test new")))
                .andExpect(jsonPath("$.contact", Matchers.is("Test new")));
    }

    @Test
    void deletePublisherById() throws Exception {
        mockMvc.perform((delete("/api/publisher/1")))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(jsonPath("$", Matchers.is("Deleted publisher with id: 1")));
    }

    @Test
    void updatePublisher() throws Exception {
        Publisher publisher = new Publisher(1, "Test new", "Test new", new LinkedHashSet<>());
        mockMvc.perform(put("/api/publisher/1")
                .content(objectMapper.writeValueAsString(publisher))
                .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", Matchers.is(1)))
                .andExpect(jsonPath("$.name", Matchers.is("Test new")))
                .andExpect(jsonPath("$.contact", Matchers.is("Test new")));
    }
}