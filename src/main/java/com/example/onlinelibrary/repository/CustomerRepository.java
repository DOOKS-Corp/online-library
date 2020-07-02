package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Address;
import com.example.onlinelibrary.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByLastName(String lastName);
    public Customer findByEmail(String email);
    public List<Customer> findByAddresses(Address address);
    public List<Customer> findByAddresses_zipCode(String zipCode);
    public List<Customer> findByAddresses_state(String state);
    public List<Customer> findByAddresses_city(String city);

}
