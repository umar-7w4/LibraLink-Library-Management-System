package com.libralink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.Book;
import com.libralink.entity.BookCopy;

@Repository
public interface BookRepository extends JpaRepository<Book, Integer>{

	List<Book> findByTitleContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
	
	List<Book> findByAuthorContainingIgnoreCaseOrAuthorContainingIgnoreCase(String title, String author);
}
