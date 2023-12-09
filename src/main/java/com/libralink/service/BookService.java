package com.libralink.service;

import java.util.List;
import java.util.Map;

import com.libralink.entity.Book;
import com.libralink.exception.BookException;

public interface BookService {

    Book addBook(Book book) throws BookException;

    Book removeBook(int bookId) throws BookException;

    Book updateBook(Book updatedBook) throws BookException;

    Book getBook(int bookId) throws BookException;

    List<Book> getAllBooks() throws BookException;
    
    long countAllBooks() throws BookException;
    
    Map<String, Long> countBooksByGenre() throws BookException;

	List<Book> searchBooks(String query);
	
	List<Book> searchBookByAuthor(String query);

	Map<String, Long> countBooksByAuthor() throws BookException;
}
