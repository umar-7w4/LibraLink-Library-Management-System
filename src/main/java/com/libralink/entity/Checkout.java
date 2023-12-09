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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 {
    "member": {
        "memberId": 1
    },
    "bookCopy": {
        "bookCopyId": 7
    },
    "checkoutDate": "2023-11-24",
    "returnDate": "2023-11-12",
    "dueDate": "2023-11-11"
}

 */

@Entity
public class Checkout {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int checkoutId;

    @NotNull(message = "Member is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    @NotNull(message = "Book copy is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", referencedColumnName = "bookCopyId")
    private BookCopy bookCopy;

    @NotNull(message = "Checkout date is required")
    @PastOrPresent(message = "Checkout date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate checkoutDate;

    @NotNull(message = "Return date is required")
    @PastOrPresent(message = "Return date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate returnDate;

    @NotNull(message = "Due date is required")
    @PastOrPresent(message = "Due date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate dueDate;
	
	public Checkout() {
		super();
	}

	public Checkout(int checkoutId, Member member, BookCopy bookCopy, LocalDate checkoutDate, LocalDate returnDate,
			LocalDate dueDate) {
		super();
		this.checkoutId = checkoutId;
		this.member = member;
		this.bookCopy = bookCopy;
		this.checkoutDate = checkoutDate;
		this.returnDate = returnDate;
		this.dueDate = dueDate;
	}

	public int getCheckoutId() {
		return checkoutId;
	}

	public void setCheckoutId(int checkoutId) {
		this.checkoutId = checkoutId;
	}

	public Member getMember() {
		return member;
	}

	public void setMember(Member member) {
		this.member = member;
	}

	public BookCopy getBookCopy() {
		return bookCopy;
	}

	public void setBookCopy(BookCopy bookCopy) {
		this.bookCopy = bookCopy;
	}

	public LocalDate getCheckoutDate() {
		return checkoutDate;
	}

	public void setCheckoutDate(LocalDate checkoutDate) {
		this.checkoutDate = checkoutDate;
	}

	public LocalDate getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(LocalDate returnDate) {
		this.returnDate = returnDate;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}
	
}
