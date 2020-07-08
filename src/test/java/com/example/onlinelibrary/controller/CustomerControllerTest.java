package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTest {

    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final static ObjectMapper objectMapper = new ObjectMapper();

//    @Test
//    void findAllCustomers() {
//        ResponseEntity<Customer[]> responseEntity = this.testRestTemplate
//                .getForEntity("http://localhost:" + port + "/customer/", Customer[].class);
//        assertNotNull(responseEntity);
//
//        Customer[] customers = responseEntity.getBody();
//        assertNotNull(customers);
//
//        assertIterableEquals(customerRepository.findAll(), Arrays.asList(customers));
//    }

//    @Test
//    void findCustomerById() {
//        final int customerId = 1;
//
//        ResponseEntity<Customer> responseEntity = this.testRestTemplate
//                .getForEntity("http://localhost:" + port + "/customer/" + customerId, Customer.class);
//        assertNotNull(responseEntity);
//
//        Customer customer = customerRepository.findById()
//    }

    @Test
    void createCustomer() {
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void findCustomerByLastName() {
    }

    @Test
    void findCustomerByEmail() {
    }

    @Test
    void findCustomerByAddressesCustom() {
    }

    @Test
    void findCustomerByAddresses_zipCode() {
    }

    @Test
    void findCustomerByAddresses_state() {
    }

    @Test
    void findCustomerByAddresses_city() {
    }
}