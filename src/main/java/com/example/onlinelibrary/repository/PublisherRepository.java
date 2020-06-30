package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
    Publisher findById(int id);
   List <Publisher> findByName(String name);
   List <Publisher> findByContact(String contakt);

}
