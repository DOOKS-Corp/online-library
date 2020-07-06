package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.repository.AuthorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/author")
public class AuthorController {

    final
    AuthorRepository authorRepository;

    public AuthorController(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }
    @GetMapping
    public List<Author> getAuthorList(){
        return authorRepository.findAll();
    }
    @GetMapping("/{id}")
    public Author findAuthorById(@PathVariable int id){
        return authorRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }
    @GetMapping("/findAuthorNames/{firstName}")
    public List<Author> findFirstName(@PathVariable String firstName){
        return authorRepository.findAllByFirstName(firstName);
    }
    @GetMapping("/findAuthorLastName/{lastName}")
    public List<Author> findLastName(@PathVariable String lastName){
        return authorRepository.findAllByLastName(lastName);
    }
    @GetMapping("/fullNameAuthor/{firstName}/{lastName}")
    public List<Author> findFirstAndLastNames(@PathVariable String firstName,@PathVariable String lastName){
        return authorRepository.findAllByFirstNameAndLastName(firstName,lastName);
    }
    @GetMapping("/getISBN/{iSBN}")
    public List<Author> findISBN(@PathVariable String iSBN){
        return authorRepository.findByISBN(iSBN);
    }
    @GetMapping("/getTitle/{title}")
    public List<Author> findTitle(@PathVariable String title){
        return authorRepository.findAllByTitle(title);
    }
    @PostMapping
    public Author createAuthors(@RequestBody Author author){
        return authorRepository.save(author);
    }
    @PutMapping("/{id}")
    public Author updateAuthor(@PathVariable int id,@RequestBody Author author){
        Author authorFind = authorRepository.findById(id).get();
        authorFind.setFirstName(author.getFirstName());
        authorFind.setLastName(author.getLastName());
        return authorRepository.save(authorFind);
    }
    @DeleteMapping("/{id}")
    public String deleteAuthor(@PathVariable int id){
        authorRepository.deleteById(id);
        return "Delete author with id " + id;
    }

}
