package com.libralink.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.libralink.entity.Book;
import com.libralink.entity.BookCopy;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BookException;
import com.libralink.service.BookCopyService;
import com.libralink.service.BookService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.libralink.entity.BookCopy;
import com.libralink.exception.BookCopyException;
import com.libralink.service.BookCopyService;
import java.util.*;

@RestController
@RequestMapping("/libralink-book")
public class BookController {
	

 	@Autowired
    private BookService bookService;

 	@PostMapping("/add")
 	public ResponseEntity<Book> addBook(@Valid @RequestBody Book book) throws BookException {
 	    Book createdBook = bookService.addBook(book);
 	    return new ResponseEntity<>(createdBook, HttpStatus.CREATED);
 	}


    @DeleteMapping("/delete/{bookId}")
    public ResponseEntity<Void> removeBook(@PathVariable int bookId) throws BookException {
        bookService.removeBook(bookId);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/update")
    public ResponseEntity<Book> updateBook(@Valid @RequestBody Book book) throws BookException {
        Book updatedBook = bookService.updateBook(book);
        return ResponseEntity.ok(updatedBook);
    }

    @GetMapping("/get/{bookId}")
    public ResponseEntity<Book> getBook(@PathVariable int bookId) throws BookException {
        Book book = bookService.getBook(bookId);
        if (book == null) {
            throw new BookException("Book not found with ID: " + bookId);
        }
        return ResponseEntity.ok(book);
    }

    @GetMapping("/list")
	public ResponseEntity<List<Book>> getAllBooks() throws BookException {
	    List<Book> books = bookService.getAllBooks();
	    return ResponseEntity.ok(books);
	}
    
    @GetMapping("/dashboard")
    public String dashboard(Model model) throws BookException {
        model.addAttribute("totalBooks", bookService.countAllBooks());
        // Add similar attributes for members, employees, etc.
        return "dashboard";
    }
    
    @GetMapping("/books/count")
    public ResponseEntity<?> getBooksCount() throws BookException {
        long count = bookService.countAllBooks();
        return ResponseEntity.ok(Collections.singletonMap("count", count));
    }
    
    @GetMapping("/genre-count")
    public ResponseEntity<?> getBooksCountByGenre() {
        try {
            Map<String, Long> genreCounts = bookService.countBooksByGenre();
            return ResponseEntity.ok(genreCounts);
        } catch (BookException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                                 .body("Failed to fetch genre counts: " + e.getMessage());
        }
    }
    
    @GetMapping("/author-count")
    public ResponseEntity<?> getBooksCountByAuthor(){
    	try {
    		Map<String, Long> authorCounts = bookService.countBooksByAuthor();
    		return ResponseEntity.ok(authorCounts);
    	}
    	catch(Exception e) {
    		return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to fetch author counts: "+e.getMessage());
    	}
    }

    @GetMapping("/search")
    public ResponseEntity<List<Book>> searchBooks(@RequestParam String query) {
        List<Book> books = bookService.searchBooks(query);
        return ResponseEntity.ok(books);
    }
    
    @GetMapping("/searchbyauthor")
    public ResponseEntity<List<Book>> searchBookByAuthor(@RequestParam String query){
    	List<Book> books = bookService.searchBookByAuthor(query);
		return ResponseEntity.ok(books);
    } 

}











