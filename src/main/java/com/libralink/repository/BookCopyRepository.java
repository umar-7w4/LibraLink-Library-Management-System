package com.libralink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.libralink.entity.BookCopy;


import java.util.*;

@Repository
public interface BookCopyRepository extends JpaRepository<BookCopy, Integer>{
	List<BookCopy> findByBookTitleContainingIgnoreCase(String title);
}
