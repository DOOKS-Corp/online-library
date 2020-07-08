package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.model.Customer;
import com.example.onlinelibrary.model.CustomerPaymentMethod;
import com.example.onlinelibrary.model.PaymentSystem;
import com.example.onlinelibrary.repository.CustomerRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.*;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerTestRestTemplate {

    @Autowired
    CustomerController customerController;
    @Autowired
    CustomerRepository customerRepository;
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate testRestTemplate;
    private final static ObjectMapper objectMapper = new ObjectMapper();

    @Test
    void findAllCustomers() {
        ResponseEntity<Customer[]> responseEntity = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/customer/findAll/", Customer[].class);
        assertNotNull(responseEntity);

        Customer[] customers = responseEntity.getBody();
        assertNotNull(customers);

        assertIterableEquals(customerRepository.findAll(), Arrays.asList(customers));
    }

    @Test
    void findCustomerById() {
        final int customerId = 1;

        ResponseEntity<Customer> responseEntity = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/customer/findById/" + customerId, Customer.class);
        assertNotNull(responseEntity);

        Customer customer = customerRepository.findById(customerId).get();
        assertNotNull(customer);
        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    void createCustomer() {
        final int customerId = 6;

        CustomerPaymentMethod customerPaymentMethod = new CustomerPaymentMethod(PaymentSystem.VISA,
                "6666666666666666", "Holders lastname 6", "Holders name 6", LocalDate.now());

        Customer customer = new Customer(customerId, "First Name 6", "Last Name 6",
                "66666666666", "Email 6",
                new HashSet<>(), customerPaymentMethod, new HashSet<>());

        ResponseEntity<Customer> responseEntity = testRestTemplate
                .postForEntity("http://localhost:" + port + "/customer/", customer, Customer.class);
        assertNotNull(responseEntity);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

//    @Test
//    void updateCustomer() throws JsonProcessingException {
//        final int customerId = 2;
//
//        CustomerPaymentMethod customerPaymentMethod = new CustomerPaymentMethod(PaymentSystem.VISA,
//                "6666666666666666", "Holders lastname 6", "Holders name 6", LocalDate.now());
//
//        Customer customer = new Customer(customerId, "First Name 6", "Last Name 6",
//                "66666666666", "Email 6",
//                new HashSet<>(), customerPaymentMethod, new HashSet<>());
//
//        HttpHeaders httpHeaders = new HttpHeaders();
//        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//        HttpEntity<String> entity = new HttpEntity<>(objectMapper.writeValueAsString(customer), httpHeaders);
//        ResponseEntity<Customer> responseEntity = testRestTemplate
//                .exchange("http://localhost:" + port + "/customer/"
//                        + customerId, HttpMethod.PUT, entity, Customer.class);
//        Customer customerBody = responseEntity.getBody();
//
//        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
//        assertEquals(customer, customerBody);
//    }

    @Test
    void deleteCustomerById() {
        final int customerId = 3;

        HttpEntity<String> entity = new HttpEntity<>(null, new HttpHeaders());
        ResponseEntity<String> responseEntity = testRestTemplate
                .exchange("http://localhost:" + port + "/customer/"
                + customerId, HttpMethod.DELETE, entity, String.class);
        String message = responseEntity.getBody();

        assertEquals(Optional.empty(), customerRepository.findById(customerId));
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Deleted customer with id: " + customerId, message);
    }

    @Test
    void findCustomerByLastName() {
        String customerLastName = "Test lastname 1";

        ResponseEntity<Customer[]> responseEntity = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/customer/byLastName/"
                        + customerLastName, Customer[].class);
        assertNotNull(responseEntity);

        Customer[] customers = responseEntity.getBody();
        assertNotNull(customers);

        assertEquals(customerRepository.findAllCustomerByLastName(customerLastName),
                Arrays.asList(customers));
    }

    @Test
    void findCustomerByEmail() {
        String customerEmail = "test1@mail.com";

        ResponseEntity<Customer> responseEntity = this.testRestTemplate
                .getForEntity("http://localhost:" + port + "/customer/byEmail/"
                        + customerEmail, Customer.class);
        assertNotNull(responseEntity);

        Customer customer = customerRepository.findCustomerByEmail(customerEmail);
        assertNotNull(customer);

        assertEquals(customer, responseEntity.getBody());
    }

    @Test
    void findCustomerByAddressesCustom() {
    }

//    @Test
//    void findCustomerByAddresses_zipCode() {
//        final String customerByZipZode = "111111";
//
//        ResponseEntity<Customer[]> responseEntity = this.testRestTemplate
//                .getForEntity("http://localhost:" + port + "/customer/byZipCode/"
//                        + customerByZipZode, Customer[].class);
//        assertNotNull(responseEntity);
//
//        Customer[] customers = responseEntity.getBody();
//        assertNotNull(customers);
//
//        assertEquals(customerRepository.findCustomerByAddresses_zipCode(customerByZipZode),
//                Arrays.asList(customers));
//    }

    @Test
    void findCustomerByAddresses_state() {
    }

    @Test
    void findCustomerByAddresses_city() {
    }
}