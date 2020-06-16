package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.model.CustomerCard;
import com.example.onlinelibrary.repository.CustomerCardRepository;
import com.example.onlinelibrary.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/customer-card")
public class CustomerCardController {

    private final CustomerCardRepository cardRepository;
    private final CustomerRepository customerRepository;

    public CustomerCardController(CustomerCardRepository cardRepository,
                                  CustomerRepository customerRepository) {
        this.cardRepository = cardRepository;
        this.customerRepository = customerRepository;
    }

    @GetMapping
    public List<CustomerCard> getAllCards() {
        return cardRepository.findAll();
    }

    @GetMapping("/{id}")
    public CustomerCard getCardById(@PathVariable Integer id) {
        return cardRepository.findById(id).orElseThrow(RuntimeException::new);
    }

    @PostMapping
    public CustomerCard addCard(@RequestBody CustomerCard requestCard) {
        CustomerCard initCard = new CustomerCard();
        Customer customer = customerRepository.findById(requestCard.getCustomer().getId())
                                                .orElseThrow(RuntimeException::new);
        initCard.setCustomer(customer);
        initCard.setCreatedAt(LocalDate.now());
        initCard.setBookList(requestCard.getBookList());
        return cardRepository.save(initCard);
    }

    @PutMapping("/{id}")
    public CustomerCard updateCard(@PathVariable Integer id,
                                   @RequestBody CustomerCard requestCard) {
        CustomerCard updatingCard = cardRepository.findById(id)
                                                    .orElseThrow(RuntimeException::new);
        updatingCard.setCustomer(requestCard.getCustomer());
        updatingCard.setBookList(requestCard.getBookList());
        return cardRepository.save(updatingCard);
    }

    @DeleteMapping("/{id}")
    public void deleteCard(@PathVariable Integer id) {
        cardRepository.deleteById(id);
    }
}
