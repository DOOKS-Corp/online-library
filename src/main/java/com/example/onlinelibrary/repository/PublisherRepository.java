package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Repository
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {

    Publisher findById(int id);

    List<Publisher> findByName(String name);

    List<Publisher> findByContact(String contakt);

    @Query(value = "SELECT id, name, contact " +
            "FROM publishers as p " +
            "INNER JOIN books as b " +
            "on p.id = b.publisher_id " +
            "WHERE b.ISBN = :ISBN"
            , nativeQuery = true)
   List <Publisher> findPublisherByISBN(@Param("ISBN")String ISBN);
}
