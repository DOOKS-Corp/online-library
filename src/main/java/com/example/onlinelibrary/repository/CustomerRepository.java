package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    List<Customer> findAllCustomerByLastName(String lastName);
    Customer findCustomerByEmail(String email);

    @Query(value = "SELECT * FROM customers AS c " +
            "LEFT JOIN customer_addresses AS ca " +
            "ON c.id = ca.customer_id " +
            "WHERE ca.address_line1 = :addressLine1 " +
            "AND ca.address_line2 = :addressLine2 " +
            "AND ca.city = :city " +
            "AND ca.state = :state " +
            "AND ca.country = :country ", nativeQuery = true)
    Customer findCustomerByAddressCustom(@Param("addressLine1") String addressLine1,
                                             @Param("addressLine2") String addressLine2,
                                             @Param("city") String city,
                                             @Param("state") String state,
                                             @Param("country") String country);


    List<Customer> findCustomerByAddresses_zipCode(String zipCode);
    List<Customer> findCustomerByAddresses_state(String state);
    List<Customer> findCustomerByAddresses_city(String city);

}
