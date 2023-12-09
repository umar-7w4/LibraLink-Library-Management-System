package com.libralink.service;

import java.time.LocalDate;
import java.util.List;

import com.libralink.entity.Book;
import com.libralink.entity.BookCopy;
import com.libralink.exception.BookCopyException;
import com.libralink.exception.BookException;
import com.libralink.exception.BranchException;
import com.libralink.entity.BookCopy;
import com.libralink.exception.BookCopyException;

import java.util.List;

public interface BookCopyService {

    BookCopy addBookCopy(BookCopy bookCopy) throws BookCopyException;

    BookCopy removeBookCopy(int bookCopyId) throws BookCopyException;

    BookCopy updateBookCopy(BookCopy bookCopy) throws BookCopyException, BranchException, BookException;

    BookCopy getBookCopy(int bookCopyId) throws BookCopyException;

    List<BookCopy> getAllBookCopys() throws BookCopyException;

	List<BookCopy> searchBookCopies(String query);
}

