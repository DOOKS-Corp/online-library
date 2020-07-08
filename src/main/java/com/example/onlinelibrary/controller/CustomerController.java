package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.repository.CustomerRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/customer")
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
    public List<Customer> findCustomerByLastName(@PathVariable String lastName) {
        return customerRepository.findAllCustomerByLastName(lastName);
    }

    @GetMapping("/byEmail/{email}")
    public Customer findCustomerByEmail(@PathVariable String email) {
        return customerRepository.findCustomerByEmail(email);
    }

    @GetMapping("/byAddress/{addressLine1}/{addressLine2}/{city}/{state}/{country}")
    public List<Customer> findCustomerByAddressesCustom(@PathVariable String addressLine1,
                                          @PathVariable String addressLine2,
                                          @PathVariable String city,
                                          @PathVariable String state,
                                          @PathVariable String country) {
        return customerRepository.findCustomerByAddressCustom(addressLine1, addressLine2, city, state, country);
    }

    @GetMapping("/byZipCode/{zipCode}")
    public List<Customer> findCustomerByAddresses_zipCode(String zipCode) {
        return customerRepository.findCustomerByAddresses_zipCode(zipCode);
    }

    @GetMapping("/byState/{state}")
    public List<Customer> findCustomerByAddresses_state(String state) {
        return customerRepository.findCustomerByAddresses_state(state);
    }

    @GetMapping("/byCity/{city}")
    public List<Customer> findCustomerByAddresses_city(String city) {
        return customerRepository.findByAddresses_city(city);
    }
}
