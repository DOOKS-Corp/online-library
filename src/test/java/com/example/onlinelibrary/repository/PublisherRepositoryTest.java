package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Publisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class PublisherRepositoryTest {
    @Autowired
    PublisherRepository publisherRepository;

    @Test
    void findById() {
        final int id = 1;
        Publisher publisher = publisherRepository.findById(id);
        assertNotNull(publisher);
        assertEquals(id, publisher.getId());
    }

    @Test
    void findByName() {
        final String name = "Test name 1";
        List<Publisher> publishers = publisherRepository.findByName(name);
        assertNotNull(publishers);
        boolean isName = publishers.stream().allMatch(a -> a.getName().equals(name));
        assertTrue(isName);

    }

    @Test
    void findByContact() {
        final String contact = "Test contact 1";
        List<Publisher> publishers = publisherRepository.findByContact(contact);
        assertNotNull(publishers);
        boolean isContact = publishers.stream().allMatch(a -> a.getContact().equals(contact));
        assertTrue(isContact);
    }

    @Test
    void findPublisherByISBN() {
        final String ISBN = "111111aaaaaaa";
        List <Publisher> publishers = publisherRepository.findPublisherByISBN(ISBN);
        assertNotNull(publishers);
        boolean isISBN = publishers
                .stream()
                .flatMap(p -> p.getBooks().stream())
                .anyMatch(b -> b.getISBN().equals(ISBN));
        assertTrue(isISBN);
    }
}