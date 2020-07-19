package com.example.onlinelibrary.business;

import com.example.onlinelibrary.beans.*;
import com.example.onlinelibrary.exceptions.NotFoundException;
import com.example.onlinelibrary.model.Author;
import com.example.onlinelibrary.model.Book;
import com.example.onlinelibrary.repository.AuthorRepository;
import com.example.onlinelibrary.repository.BookRepository;
import com.example.onlinelibrary.repository.PublisherRepository;
import org.springframework.stereotype.Component;

import java.time.ZoneId;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Component
public class BookBusinessImpl {

    final private BookRepository bookRepository;

    final private AuthorRepository authorRepository;

    final private PublisherRepository publisherRepository;

    public BookBusinessImpl(BookRepository bookRepository, AuthorRepository authorRepository, PublisherRepository publisherRepository) {
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.publisherRepository = publisherRepository;
    }

    public RntBookResWrp service (String isbn){

        RntBookResWrp bookResWrp=createRntBookResRep();

        Optional<Book> book = bookRepository.findBookByISBN(isbn);
            try{
                if(!book.isPresent())
                    throw new NotFoundException(15155, "No book found. Check ISBN");

            bookResWrp.getBook().setIsbn(book.get().getISBN());
            bookResWrp.getBook().setTitle(book.get().getTitle());
            bookResWrp.getBook().setCost(Integer.toString(book.get().getCost()));
            bookResWrp.getBook().setPubDate(book.get().getPubDate().atStartOfDay(ZoneId.of("Europe/Paris")).toOffsetDateTime());
            bookResWrp.getBook().setCategory(book.get().getCategory().toString());
            RntPublisher publisher = new RntPublisher();
            publisher.setName(book.get().getPublisher().getName());
            publisher.setContact(book.get().getPublisher().getContact());
            bookResWrp.getBook().setPublisher(publisher);

            bookResWrp.getBook().setAuthor(getAuthors(isbn));
        }
            catch (NotFoundException n ) {


                ErrorObject errorObject = new ErrorObject();
                errorObject.setErrorType(ErrorObject.ErrorTypeEnum.WARNING);
                errorObject.setMessage(n.getMessage());
                errorObject.setFieldName(Integer.toString(n.getCode()));
                errorObject.setMoreInfo(n.toString());
                bookResWrp.getMetadata().getMessages().add(errorObject);
                bookResWrp.getMetadata().setStatusCode(Metadata.StatusCodeEnum.U);

            }
        return bookResWrp;
    }

    private List<RntAuthor> getAuthors (String isbn) throws NotFoundException{
        List<RntAuthor> rntAuthors = new ArrayList<>();
            List<Author> authorList = authorRepository.findByISBN(isbn);

            if(authorList.isEmpty())
                throw new NotFoundException(10022, "No author found");

            for (Author author: authorList
                 ) {
                RntAuthor rntAuthor = new RntAuthor();
                rntAuthor.setLastName(author.getLastName());
                rntAuthor.setFirstName(author.getFirstName());
                rntAuthors.add(rntAuthor);
            }

            return rntAuthors;

    }
    private RntBookResWrp createRntBookResRep (){
        RntBookResWrp bookResWrp = new RntBookResWrp();
        Metadata metadata = new Metadata();
        List<ErrorObject> errorObjectList =new ArrayList<ErrorObject>();
        RntBookItem rntBookItem = new RntBookItem();

        metadata.setMessages(errorObjectList);
        bookResWrp.setMetadata(metadata);
        bookResWrp.setBook(rntBookItem);

        List <RntAuthor> rntAuthorList = new ArrayList<>();

        RntPublisher rntPublisher = new RntPublisher();
        rntAuthorList.add(new RntAuthor());
        bookResWrp.getBook().setAuthor(rntAuthorList);
        bookResWrp.getBook().setPublisher(rntPublisher);

        return  bookResWrp;
    }



}
