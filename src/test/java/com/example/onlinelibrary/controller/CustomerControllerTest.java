package com.example.onlinelibrary.controller;

import com.example.onlinelibrary.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    CustomerRepository customerRepository;

    @Test
    void findAllCustomers() {

    }

    @Test
    void findCustomerById() {
    }

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