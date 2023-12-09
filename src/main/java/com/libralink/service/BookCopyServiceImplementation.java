package com.libralink.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.libralink.entity.Book;
import com.libralink.entity.BookCopy;
import com.libralink.entity.Branch;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BookException;
import com.libralink.exception.BranchException;
import com.libralink.repository.BookCopyRepository;
import com.libralink.repository.BookRepository;
import com.libralink.repository.BranchRepository;

@Service("BookCopyService")
public class BookCopyServiceImplementation implements BookCopyService {

    @Autowired
    private BookCopyRepository bookCopyRepository;
    
    @Autowired
    private BookRepository bookRepository;
    
    @Autowired
    private BranchRepository branchRepository;

    @Override
    public BookCopy addBookCopy(BookCopy bookCopy) throws BookCopyException {
        try {
            if (bookCopy.getBook() != null && bookCopy.getBook().getBookId() != 0) {
                Book book = bookRepository.findById(bookCopy.getBook().getBookId())
                        .orElseThrow(() -> new BookException("Book not found"));
                bookCopy.setBook(book);
            }

            if (bookCopy.getBranch() != null && bookCopy.getBranch().getBranchId() != 0) {
                Branch branch = branchRepository.findById(bookCopy.getBranch().getBranchId())
                        .orElseThrow(() -> new BranchException("Branch not found"));
                bookCopy.setBranch(branch);
            }

            return bookCopyRepository.saveAndFlush(bookCopy);
        } catch (Exception e) {
            throw new BookCopyException("Failed to add book copy: " + e.getMessage());
        }
    }


    @Override
    public BookCopy removeBookCopy(int bookCopyId) throws BookCopyException {
        BookCopy bookCopy = bookCopyRepository.findById(bookCopyId)
                .orElseThrow(() -> new BookCopyException("Book copy with ID " + bookCopyId + " not found"));

        bookCopyRepository.deleteById(bookCopyId);
        return bookCopy;
    }
    
    @Override
    public BookCopy updateBookCopy(BookCopy bookCopy) throws BookCopyException, BookException, BranchException {
        BookCopy existingBookCopy = bookCopyRepository.findById(bookCopy.getBookCopyId())
                .orElseThrow(() -> new BookCopyException("Book copy not found"));

        if (bookCopy.getBook() != null && bookCopy.getBook().getBookId() != 0) {
            Book book = bookRepository.findById(bookCopy.getBook().getBookId())
                    .orElseThrow(() -> new BookException("Book not found"));
            existingBookCopy.setBook(book);
        }

        if (bookCopy.getBranch() != null && bookCopy.getBranch().getBranchId() != 0) {
            Branch branch = branchRepository.findById(bookCopy.getBranch().getBranchId())
                    .orElseThrow(() -> new BranchException("Branch not found"));
            existingBookCopy.setBranch(branch);
        }

        existingBookCopy.setStatus(bookCopy.getStatus());
        existingBookCopy.setDueDate(bookCopy.getDueDate());

        return bookCopyRepository.saveAndFlush(existingBookCopy);
    }



    @Override
    public BookCopy getBookCopy(int bookCopyId) throws BookCopyException {
        return bookCopyRepository.findById(bookCopyId)
                .orElseThrow(() -> new BookCopyException("Book copy with ID " + bookCopyId + " not found"));
    }

    @Override
    public List<BookCopy> getAllBookCopys() throws BookCopyException {
        try {
            return bookCopyRepository.findAll();
        } catch (Exception e) {
            throw new BookCopyException("Failed to retrieve all book copies: " + e.getMessage());
        }
    }
    
    @Override
    public List<BookCopy> searchBookCopies(String query) {
        return bookCopyRepository.findByBookTitleContainingIgnoreCase(query);
    }
}
