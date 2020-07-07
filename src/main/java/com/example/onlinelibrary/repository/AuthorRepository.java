package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AuthorRepository extends JpaRepository<Author,Integer> {

    List<Author> findAllByFirstName(String firstName);

    List<Author> findAllByLastName(String lastName);

    List<Author> findAllByFirstNameAndLastName(String firstName,String lastName);

    @Query(value = "SELECT id, first_name, last_name  " +
            "FROM authors AS a " +
            "INNER JOIN author_books AS ab " +
            "ON a.id = ab.author_id " +
            "WHERE ab.book_ISBN = :ISBN"
            ,nativeQuery = true)
    List<Author> findByISBN(@Param("ISBN")String ISBN);

    @Query(value = "SELECT id, first_name, last_name " +
            "FROM authors AS a " +
            "LEFT JOIN author_books AS ab " +
            "ON a.id = ab.author_id " +
            "LEFT JOIN books AS b " +
            "ON ab.book_ISBN = b.ISBN " +
            "WHERE b.title = :title"
            ,nativeQuery = true)
    List<Author> findAllByTitle(@Param("title") String title);
}
