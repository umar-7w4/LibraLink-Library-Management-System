package com.libralink.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 

    {
        "bookCopyId": 5,
        "book":{
            "bookId":3
        },
        "branch": {
            "branchId": 2
            },
        "status": "Not Available",
        "dueDate": "2023-05-04"
    }
 
 */

@Entity
public class BookCopy {
	
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookCopyId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branchId")
    private Branch branch;

    @NotBlank(message = "Status is required")
    @Size(max = 255, message = "Status must be less than or equal to 255 characters")
    private String status;

    @NotNull(message = "Due date is required")
    @PastOrPresent(message = "Due date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
	
	public BookCopy() {
		super();
	}

	public BookCopy(int bookCopyId, Book book, Branch branch, String status, LocalDate dueDate) {
		super();
		this.bookCopyId = bookCopyId;
		this.book = book;
		this.branch = branch;
		this.status = status;
		this.dueDate = dueDate;
	}

	public int getBookCopyId() {
		return bookCopyId;
	}

	public void setBookCopyId(int bookCopyId) {
		this.bookCopyId = bookCopyId;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
	
	

}
