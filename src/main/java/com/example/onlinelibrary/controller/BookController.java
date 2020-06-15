package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/findall")
    public List<Book> findAllCustomers() {
        return bookRepository.findAll();
    }

    @GetMapping("/findbyid/{id}")
    public Book findCustomerById(@PathVariable int id) {
        return bookRepository.findById(id).get();
    }

    @PostMapping("/create")
    public Book createCustomer(@RequestBody Book customer) {
        return bookRepository.save(customer);
    }

    @PutMapping("/update")
    public Book updateCustomer(@PathVariable int id, @RequestBody Book bookNew) {
        Book customerOld = bookRepository.findById(id).get();
        bookNew.setName(customerOld.getName());
        return bookRepository.save(bookNew;
    }

    @DeleteMapping("/deletebyid")
    public String deleteCustomerById(@PathVariable int id) {
        bookRepository.deleteById(id);
        return "Deleted customer with id: " + id;
    }
}
