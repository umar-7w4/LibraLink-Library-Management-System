package com.libralink.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 
 {
    "branch": {
        "branchId": 1
    },
    "book": {
        "bookId": 2
    },
    "quantity": 10,
    "dateAdded": "2023-11-24",
    "dateModified": "2023-11-24"
}

 
 */

@Entity
public class Inventory {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int inventoryId;

    @NotNull(message = "Branch is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "branch_id", referencedColumnName = "branchId")
    private Branch branch;

    @NotNull(message = "Book is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_id", referencedColumnName = "bookId")
    private Book book;

    @Positive(message = "Quantity must be a positive value")
    private int quantity;

    @NotNull(message = "Date added is required")
    @PastOrPresent(message = "Date added must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateAdded;

    @NotNull(message = "Date modified is required")
    @PastOrPresent(message = "Date modified must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dateModified;

	public Inventory() {
		super();
	}

	public Inventory(int inventoryId, Branch branch, Book book, int quantity, LocalDate dateAdded,
			LocalDate dateModified) {
		super();
		this.inventoryId = inventoryId;
		this.branch = branch;
		this.book = book;
		this.quantity = quantity;
		this.dateAdded = dateAdded;
		this.dateModified = dateModified;
	}

	public int getInventoryId() {
		return inventoryId;
	}

	public void setInventoryId(int inventoryId) {
		this.inventoryId = inventoryId;
	}

	public Branch getBranch() {
		return branch;
	}

	public void setBranch(Branch branch) {
		this.branch = branch;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public LocalDate getDateAdded() {
		return dateAdded;
	}

	public void setDateAdded(LocalDate dateAdded) {
		this.dateAdded = dateAdded;
	}

	public LocalDate getDateModified() {
		return dateModified;
	}

	public void setDateModified(LocalDate dateModified) {
		this.dateModified = dateModified;
	}
	
	

}
