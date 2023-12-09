package com.libralink.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.libralink.entity.BookCopy;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BookException;
import com.libralink.exception.BranchException;
import com.libralink.service.BookCopyService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/libralink-bookcopy")
public class BookCopyController {
    
    @Autowired
    private BookCopyService bookCopyService;
    
    @PostMapping("/add")
    public ResponseEntity<BookCopy> addBookCopy(@Valid @RequestBody BookCopy bookCopy) throws BookCopyException {
        BookCopy newBookCopy = bookCopyService.addBookCopy(bookCopy);
        return new ResponseEntity<>(newBookCopy, HttpStatus.CREATED);
    }
    
    @DeleteMapping("/delete/{bookCopyId}")
    public ResponseEntity<Void> removeBookCopy(@PathVariable int bookCopyId) throws BookCopyException {
        bookCopyService.removeBookCopy(bookCopyId);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping(value = "/update", consumes = "application/json")
    public ResponseEntity<BookCopy> updateBookCopy(@RequestBody BookCopy bookCopy) throws BookCopyException, BranchException, BookException {
        BookCopy updatedBookCopy = bookCopyService.updateBookCopy(bookCopy);
        return ResponseEntity.ok(updatedBookCopy);
    }
    
    @GetMapping("/get/{bookCopyId}")
    public ResponseEntity<BookCopy> getBookCopy(@PathVariable int bookCopyId) throws BookCopyException {
        BookCopy bookCopy = bookCopyService.getBookCopy(bookCopyId);
        return ResponseEntity.ok(bookCopy);
    }
    
    @GetMapping("/list")
    public ResponseEntity<List<BookCopy>> getAllBookCopys() throws BookCopyException {
        List<BookCopy> bookCopys = bookCopyService.getAllBookCopys();
        return ResponseEntity.ok(bookCopys);
    }
    
    @GetMapping("/search")
    public List<BookCopy> searchBookCopies(@RequestParam String query) {
        return bookCopyService.searchBookCopies(query);
    }
}
