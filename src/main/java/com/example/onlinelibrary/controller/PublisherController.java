package com.example.onlinelibrary.controller;
import com.example.onlinelibrary.model.Publisher;
import com.example.onlinelibrary.repository.PublisherRepository;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("api/publisher")
public class PublisherController {

    private final PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping
    public List<Publisher> findAllPublisher() {
        return publisherRepository.findAll();
    }

    @GetMapping("/{id}")
    public Publisher findById(@PathVariable int id) {
        return publisherRepository.findById(id);
    }

    @GetMapping("/name/{name}")
    public List<Publisher> findByName(@PathVariable String name) {
        return publisherRepository.findByName(name);
    }

    @GetMapping("/contact/{contact}")
    public List<Publisher> findByContact(@PathVariable String contact) {
        return publisherRepository.findByContact(contact);
    }

    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher requestPublisher) {
        Publisher publisher = new Publisher();
        publisher.setName(requestPublisher.getName());
        publisher.setBooks(requestPublisher.getBooks());
        publisher.setContact(requestPublisher.getContact());
        return publisherRepository.save(publisher);
    }

    @DeleteMapping("/{id}")
    public String deletePublisherById(@PathVariable int id) {
        publisherRepository.deleteById(id);
        return "Deleted publisher with id: " + id;
    }

    @PutMapping("/{id}")
    public Publisher updatePublisher(@RequestBody Publisher requestPublisher,
                                     @PathVariable int id) {
        Publisher publisher = publisherRepository.findById(id);
        publisher.setName(requestPublisher.getName());
        publisher.setBooks(requestPublisher.getBooks());
        publisher.setContact(requestPublisher.getContact());
        return publisherRepository.save(publisher);
    }
}