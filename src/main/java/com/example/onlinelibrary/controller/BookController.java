package com.example.onlinelibrary.controller;


import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.repository.BookRepository;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLOutput;
import java.util.List;
import java.util.NoSuchElementException;


@RestController
@RequestMapping("/api/book")
public class BookController {
    private BookRepository bookRepository;

    public BookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping
    public List<Book> getAllBook() {
        return bookRepository.findAll();
    }

    @GetMapping("/{id}")
    public Book getBookById(@PathVariable int id) {
        return bookRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    //Сохранение новых обьектов (Результат всегда юудет разный)
    @PostMapping
    public Book postNewBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    //Изменение записи (Имутабелен - сколько раз вызвать результат будет один и тотже)
    @PutMapping("/{id}")
    public Book updateBook(@PathVariable int id, @RequestBody Book requestBook) {
        Book dbBook = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        dbBook.setName(requestBook.getName());
        dbBook.setGenre(requestBook.getGenre());
        dbBook.setAuthor(requestBook.getAuthor());
        dbBook.setDateRealise(requestBook.getDateRealise());
        return bookRepository.save(dbBook);
    }

    @DeleteMapping("/{id}")
    public void deleteBook(@PathVariable int id) {
        Book dbBook = bookRepository.findById(id).orElseThrow(RuntimeException::new);
        bookRepository.delete(dbBook);
    }

}



