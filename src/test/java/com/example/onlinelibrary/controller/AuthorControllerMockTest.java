package com.example.onlinelibrary.controller;


import com.example.onlinelibrary.model.Author;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Mockito.*;

import com.example.onlinelibrary.repository.AuthorRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class AuthorControllerMockTest {

    @Autowired
    AuthorRepository authorRepository;

    @Autowired
    MockMvc mockMvc;

    private static final ObjectMapper om = new ObjectMapper();


    @Test
    void getAuthorList() throws Exception {

    mockMvc.perform(get("/author"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andExpect(jsonPath("$",hasSize(5)))
            .andExpect(jsonPath("$[0].id",is(1)))
            .andExpect(jsonPath("$[1].id",is(2)))
            .andExpect(jsonPath("$[2].id",is(3)))
            .andExpect(jsonPath("$[3].id",is(4)))
            .andExpect(jsonPath("$[4].id",is(5)));

    }

    @Test
    void findAuthorById() throws Exception {

        mockMvc.perform(get("/author/1"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(1)))
                .andExpect(jsonPath("$.firstName",is("Author name 1")))
                .andExpect(jsonPath("$.lastName",is("Author lastname 1")));

    }

    @Test
    void findFirstName() throws Exception {

        mockMvc.perform(get("/author/findAuthorNames/Author name 2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));

    }

    @Test
    void findLastName() throws Exception {
        mockMvc.perform(get("/author/findAuthorLastName/Author lastname 2"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)));

    }

    @Test
    void findFirstAndLastNames() throws Exception {
        mockMvc.perform(get("/author/fullNameAuthor/Author name 3/Author lastname 3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(1)))
                .andExpect(jsonPath("$[0].id",is(3)));

    }

    @Test
    void findISBN() throws Exception {
        mockMvc.perform(get("/author/getISBN/333333ccccccc"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));

    }

    @Test
    void findTitle() throws Exception {
        mockMvc.perform(get("/author/getTitle/Test title 3"))
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$",hasSize(2)));
    }

    @Test
    void createAuthors() throws Exception {
        mockMvc.perform(post("/author")
                .content(om.writeValueAsBytes(new Author(6,"Author name 6", "Author lastname 6",new HashSet<>())))
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id",is(6)))
                .andExpect(jsonPath("$.firstName",is("Author name 6")))
                .andExpect(jsonPath("$.lastName",is("Author lastname 6")));

    }

    @Test
    void updateAuthor() throws Exception {
        mockMvc.perform(put("/author/1")
                .content(om.writeValueAsBytes(new Author(1,"Author name 55", "Author lastname 55",new HashSet<>())))
                .header(HttpHeaders.CONTENT_TYPE,MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                        .andExpect(jsonPath("$.id",is(1)))
                        .andExpect(jsonPath("$.firstName",is("Author name 55")))
                        .andExpect(jsonPath("$.lastName",is("Author lastname 55")));
    }

    @Test
    void deleteAuthor() throws Exception {
        mockMvc.perform(delete("/author/2"))
                .andExpect(content().contentType(MediaType.valueOf("text/plain;charset=UTF-8")))
                .andExpect(jsonPath("$", Matchers.is("Delete author with id 2")));

    }
}