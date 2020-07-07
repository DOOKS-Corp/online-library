package com.example.onlinelibrary.repository;

import ch.qos.logback.core.boolex.EvaluationException;
import com.example.onlinelibrary.model.Address;
import com.example.onlinelibrary.model.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

    public Customer findByLastName(String lastName);
    public Customer findByEmail(String email);

    @Query(value = "FROM Customer c " +
            "where c.addresses.addressLine1 = :addressLine1 " +
            "AND c.addresses.addressLine2 = :addressLine2 " +
            "AND c.addresses.city = :city " +
            "AND c.addresses.state = :state " +
            "AND c.addresses.country = :country " +
            "AND c.addresses.zipCode = :zipCode")
    public List<Customer> findByAddressesCustom(@Param("addressLine1")String addressLine1,
                                                @Param("addressLine2")String addressLine2,
                                                @Param("city") String city,
                                                @Param("state") String state,
                                                @Param("country") String country,
                                                @Param("zipCode") String zipCode);


    public List<Customer> findByAddresses_zipCode(String zipCode);
    public List<Customer> findByAddresses_state(String state);
    public List<Customer> findByAddresses_city(String city);

}
