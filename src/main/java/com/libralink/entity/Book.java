package com.libralink.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 
 {
  "title": "Sample Book",
  "author": "John Doe",
  "isbn": "978-1234567890",
  "genre": "Fiction",
  "publisher": "Publisher ABC",
  "publicationDate": "2022-01-15"
 }

 */

@Entity
public class Book {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;

    @NotNull(message = "Title is required")
    @Size(max = 255, message = "Title must be less than or equal to 255 characters")
    private String title;

    @NotNull(message = "Author is required")
    @Size(max = 255, message = "Author must be less than or equal to 255 characters")
    private String author;

    @NotNull(message = "ISBN is required")
    @Size(max = 20, message = "ISBN must be less than or equal to 20 characters")
    private String isbn;

    @Size(max = 255, message = "Genre must be less than or equal to 255 characters")
    private String genre;
 
    @Size(max = 255, message = "Publisher must be less than or equal to 255 characters")
    private String publisher;

    @NotNull(message = "Publication date is required")
    @PastOrPresent(message = "Publication date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate publicationDate;

    @JsonIgnore 
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BookCopy> bookCopies;

    @JsonIgnore 
    @OneToMany(mappedBy = "book", fetch = FetchType.LAZY)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Inventory> inventories;
	
	public Book() {
		super();
	}

	public Book(int bookId, String title, String author, String isbn, String genre, String publisher,
			LocalDate publicationDate, List<BookCopy> bookCopies, List<Inventory> inventories) {
		super();
		this.bookId = bookId;
		this.title = title;
		this.author = author;
		this.isbn = isbn;
		this.genre = genre;
		this.publisher = publisher;
		this.publicationDate = publicationDate;
		this.bookCopies = bookCopies;
		this.inventories = inventories;
	}

	public int getBookId() {
		return bookId;
	}

	public void setBookId(int bookId) {
		this.bookId = bookId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getPublisher() {
		return publisher;
	}

	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}

	public LocalDate getPublicationDate() {
		return publicationDate;
	}

	public void setPublicationDate(LocalDate publicationDate) {
		this.publicationDate = publicationDate;
	}

	public List<BookCopy> getBookCopies() {
		return bookCopies;
	}

	public void setBookCopies(List<BookCopy> bookCopies) {
		this.bookCopies = bookCopies;
	}

	public List<Inventory> getInventories() {
		return inventories;
	}

	public void setInventories(List<Inventory> inventories) {
		this.inventories = inventories;
	}
	
	

	
}
