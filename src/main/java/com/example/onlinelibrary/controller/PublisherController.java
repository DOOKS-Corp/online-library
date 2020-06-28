package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Publisher;
import com.example.onlinelibrary.repository.PublisherRepository;
import org.springframework.http.converter.json.GsonBuilderUtils;
import org.springframework.web.bind.annotation.*;
import org.w3c.dom.ls.LSOutput;

import java.util.List;

@RestController
@RequestMapping("api/publisher")
public class PublisherController {
    PublisherRepository publisherRepository;

    public PublisherController(PublisherRepository publisherRepository) {
        this.publisherRepository = publisherRepository;
    }

    @GetMapping("/findall")
    public List<Publisher> findAllPublisher(){
        return publisherRepository.findAll();
    }

    @GetMapping("/findbyid/{id}")
    public List <Publisher> findById(@PathVariable int id){
        return publisherRepository.findById(id);
    }

    @GetMapping("findbyname/{name}")
    public Publisher findByname(@PathVariable String name) {
        return publisherRepository.findByName(name);
    }

    @GetMapping("findbycontact/{contact}")
    public Publisher findByContact(@PathVariable String contact) {
        return  publisherRepository.findByContact(contact);
    }

    @PostMapping
    public Publisher createPublisher(@RequestBody Publisher publisher) {return publisherRepository.save(publisher);}

    @DeleteMapping("/{id}")
    public String deletePublisherById(@PathVariable int id){
        publisherRepository.deleteById(id);
        return "Deleted publisher with id: " + id;
    }



}
