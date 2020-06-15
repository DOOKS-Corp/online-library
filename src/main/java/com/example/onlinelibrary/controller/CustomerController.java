package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/book")
public class CustomerController {

    CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/findall")
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/findbyid/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return customerRepository.findById(id).get();
    }

    @PostMapping("/create")
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/update")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customerNew) {
        Customer customerOld = customerRepository.findById(id).get();
        customerOld.setName(customerNew.getName());
        return customerRepository.save(customerOld);
    }

    @DeleteMapping("/deletebyid")
    public String deleteCustomerById(@PathVariable int id) {
        customerRepository.deleteById(id);
        return "Deleted customer with id: " + id;
    }

}
