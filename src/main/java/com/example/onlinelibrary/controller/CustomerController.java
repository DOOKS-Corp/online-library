package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Address;
import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    CustomerRepository customerRepository;

    CustomerController(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @GetMapping("/findAll")
    public List<Customer> findAllCustomers() {
        return customerRepository.findAll();
    }

    @GetMapping("/findById/{id}")
    public Customer findCustomerById(@PathVariable int id) {
        return customerRepository.findById(id).orElseThrow(NoSuchElementException::new);
    }

    @PostMapping
    public Customer createCustomer(@RequestBody Customer customer) {
        return customerRepository.save(customer);
    }

    @PutMapping("/{id}")
    public Customer updateCustomer(@PathVariable int id, @RequestBody Customer customerNew) {
        Customer customerOld = customerRepository.findById(id).orElseThrow(NoSuchElementException::new);
        customerOld.setFirstName(customerNew.getFirstName());
        customerOld.setLastName(customerNew.getLastName());
        customerOld.setPhoneNumber(customerNew.getPhoneNumber());
        customerOld.setEmail(customerNew.getEmail());
        return customerRepository.save(customerOld);
    }

    @DeleteMapping("/{id}")
    public String deleteCustomerById(@PathVariable int id) {
        customerRepository.deleteById(id);
        return "Deleted customer with id: " + id;
    }

    @GetMapping("/byLastName/{lastName}")
    public Customer findByLastName(@PathVariable String lastName) {
        return customerRepository.findByLastName(lastName);
    }

    @GetMapping("/byEmail/{email}")
    public Customer findByEmail(@PathVariable String email) {
        return customerRepository.findByEmail(email);
    }

//    @GetMapping("/byAddress/{address}")
//    public List<Customer> findByAddresses(@PathVariable Address address) {
//        return customerRepository.findByAddresses(address);
//    }

    @GetMapping("/byZipCodel/{zipCode}")
    public List<Customer> findByAddresses_zipCode(String zipCode) {
        return customerRepository.findByAddresses_zipCode(zipCode);
    }

    @GetMapping("/byState/{state}")
    public List<Customer> findByAddresses_state(String state) {
        return customerRepository.findByAddresses_state(state);
    }

    @GetMapping("/byCity/{city}")
    public List<Customer> findByAddresses_city(String city) {
        return customerRepository.findByAddresses_city(city);
    }
}
