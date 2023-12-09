package com.libralink.service;

import com.libralink.entity.Book;
import com.libralink.exception.BookException;
import com.libralink.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service("BookService")
public class BookServiceImplementation implements BookService {

	@Autowired
    private BookRepository bookRepository;

    @Autowired
    public BookServiceImplementation(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @Override
    public Book addBook(@Valid Book book) throws BookException {
        try {
            return bookRepository.saveAndFlush(book);
        } catch (Exception e) {
            throw new BookException("Failed to add book: " + e.getMessage());
        }
    }

    @Override
    public Book removeBook(int bookId) throws BookException {
        try {
            Book book = getBook(bookId);
            bookRepository.delete(book);
            return book;
        } catch (Exception e) {
            throw new BookException("Failed to remove book: " + e.getMessage());
        }
    }

    @Override
    public Book updateBook(@Valid Book updatedBook) throws BookException {
        int bookId = updatedBook.getBookId();
        try {
            Book book = getBook(bookId);

            book.setTitle(updatedBook.getTitle());
            book.setAuthor(updatedBook.getAuthor());
            book.setIsbn(updatedBook.getIsbn());
            book.setGenre(updatedBook.getGenre());
            book.setPublisher(updatedBook.getPublisher());
            book.setPublicationDate(updatedBook.getPublicationDate());

            return bookRepository.saveAndFlush(book);
        } catch (Exception e) {
            throw new BookException("Failed to update book: " + e.getMessage());
        }
    }

    @Override
    public Book getBook(int bookId) throws BookException {
        try {
            return bookRepository.findById(bookId)
                    .orElseThrow(() -> new EntityNotFoundException("Book with ID " + bookId + " not found"));
        } catch (Exception e) {
            throw new BookException("Failed to retrieve book: " + e.getMessage());
        }
    }

    @Override
    public List<Book> getAllBooks() throws BookException {
        try {
            return bookRepository.findAll();
        } catch (Exception e) {
            throw new BookException("Failed to retrieve all books: " + e.getMessage());
        }
    }
    
    @Override
    public long countAllBooks() throws BookException {
        try {
            return bookRepository.count();
        } catch (Exception e) {
            throw new BookException("Failed to count all books: " + e.getMessage());
        }
    }
    
    @Override
    public Map<String, Long> countBooksByGenre() throws BookException {
        try {
            return bookRepository.findAll()
                                 .stream()
                                 .collect(Collectors.groupingBy(Book::getGenre, Collectors.counting()));
        } catch (Exception e) {
            throw new BookException("Error fetching books by genre: " + e.getMessage());
        }
    }
    
    @Override
    public Map<String, Long> countBooksByAuthor() throws BookException{
    	try {
    		return bookRepository.findAll().stream().collect(Collectors.groupingBy(Book::getAuthor, Collectors.counting()));
    	}
    	catch(Exception e) {
    		throw new BookException("Error fetching books by author: "+e.getMessage());
    	}
    }
    
    @Override
    public List<Book> searchBooks(String query) {
        return bookRepository.findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }
    
    @Override
    public List<Book> searchBookByAuthor(String query){
    	return bookRepository.findByAuthorContainingIgnoreCaseOrAuthorContainingIgnoreCase(query, query);
    }
}
