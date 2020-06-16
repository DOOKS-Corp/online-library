package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.CustomerCard;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerCardRepository extends JpaRepository<CustomerCard, Integer> {
}
