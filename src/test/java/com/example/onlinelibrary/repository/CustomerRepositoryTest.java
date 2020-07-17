package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Customer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void findCustomerByLastName() {
        final String lastName = "Test lastname 1";

        Customer customer = customerRepository.findCustomerByLastName(lastName);

        assertNotNull(customer);
        assertEquals(lastName, customer.getLastName());
    }

    @Test
    void findCustomerByEmail() {
        final String email = "test1@mail.com";

        Customer customer = customerRepository.findCustomerByEmail(email);

        assertNotNull(customer);
        assertEquals(email, customer.getEmail());
    }

    @Test
    void findCustomerByAddressesCustom() {
        final String addressLine1 = "Test address 1 line 1";
        final String addressLine2 = "Test address 1 line 2";
        final String city = "Test city 1";
        final String state = "TS";
        final String country = "Test country 1";

        List<Customer> customersAddress = customerRepository.findCustomerByAddressCustom(addressLine1, addressLine2, city, state, country);
        assertNotNull(customersAddress);

        boolean isValid = customersAddress
                .stream()
                .allMatch(c -> c.getAddresses()
                .stream().allMatch(a -> (a.getAddressLine1().equals(addressLine1))
                        && (a.getAddressLine2().equals(addressLine2))
                        &&(a.getCity().equals(city))
                        &&(a.getState().equals(state))
                        &&(a.getCountry().equals(country))));
        assertTrue(isValid);
    }

    @Test
    void findCustomerByAddresses_zipCode() {
        final String zipCode = "111111";

        List<Customer> customersZipCode = customerRepository.findCustomerByAddresses_zipCode(zipCode);
        assertNotNull(customersZipCode);

        boolean isValid = customersZipCode
                .stream()
                .allMatch(c -> c.getAddresses()
                .stream().anyMatch(a -> a.getZipCode().equals(zipCode)));
        assertTrue(isValid);
    }

    @Test
    void findCustomerByAddresses_state() {
        final String state = "TS";

        List<Customer> customersState = customerRepository.findCustomerByAddresses_state(state);
        assertNotNull(customersState);

        boolean isValid = customersState
                .stream()
                .allMatch(c -> c.getAddresses()
                .stream().anyMatch(a -> a.getState().equals(state)));
        assertTrue(isValid);
    }

    @Test
    void findCustomerByAddresses_city() {
        final String city = "Test city 1";

        List<Customer> customersCity = customerRepository.findByAddresses_city(city);
        assertNotNull(customersCity);

        boolean isValid = customersCity
                .stream()
                .allMatch(c -> c.getAddresses()
                .stream().anyMatch(a -> a.getCity().equals(city)));
        assertTrue(isValid);

    }
}