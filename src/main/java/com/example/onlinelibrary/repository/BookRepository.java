package com.example.onlinelibrary.repository;

import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.model.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, String> {

    Book findBookByISBN(String isbn);

    List<Book> findAllByAuthors(Author author);

    @Query(value="" +
            "select isbn, title,\n" +
            "       pub_date,\n" +
            "       cost,\n" +
            "       category,\n" +
            "       available_number,\n" +
            "       publisher_id\n" +
            "from books as b\n" +
            "left join author_books as ab\n" +
            "    on b.isbn = ab.book_ISBN\n" +
            "left join authors a\n" +
            "    on ab.author_id = a.id\n" +
            "where a.first_name = :name",
            nativeQuery = true
    )
    List<Book> findBookByAuthorFirstName(@Param("name") String name);

    @Query(value="" +
            "select isbn, title,\n" +
            "       pub_date,\n" +
            "       cost,\n" +
            "       category,\n" +
            "       available_number,\n" +
            "       publisher_id\n" +
            "from books as b\n" +
            "left join author_books as ab\n" +
            "    on b.isbn = ab.book_ISBN\n" +
            "left join authors a\n" +
            "    on ab.author_id = a.id\n" +
            "where a.last_name = :name",
            nativeQuery = true
    )
    List<Book> findBookByAuthorLastName(@Param("name") String name);

    List<Book> findAllByTitle(String title);

    List<Book> findAllByPubDate(LocalDate publicationDate);

    List<Book> findAllByPubDateBetween(LocalDate startDate, LocalDate endDate);

    List<Book> findAllByPublisher(Publisher publisher);

    List<Book> findAllByCostBetween(Integer startCost, Integer endCost);

    List<Book> findAllByCostBefore(Integer cost);

    List<Book> findAllByCostAfter(Integer cost);

}
