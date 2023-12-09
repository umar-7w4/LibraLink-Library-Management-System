package com.libralink.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 
 {
    "fineId": 1,
    "member": {
        "memberId": 1
    },
    "bookCopy": {
        "bookCopyId": 1
    },
    "fineAmount": 10.5,
    "fineDate": "2023-11-24",
    "status": "Paid"
}

 */


@Entity
public class Fine {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int fineId;

    @NotNull(message = "Member is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", referencedColumnName = "memberId")
    private Member member;

    @NotNull(message = "Book copy is required")
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "book_copy_id", referencedColumnName = "bookCopyId")
    private BookCopy bookCopy;

    @Positive(message = "Fine amount must be a positive value")
    private double fineAmount;

    @NotNull(message = "Fine date is required")
    @PastOrPresent(message = "Fine date must be in the past or present")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate fineDate;

    @NotBlank(message = "Status is required")
    @Size(max = 255, message = "Status must be less than or equal to 255 characters")
    private String status;
	
	public Fine() {
		super();
	}

	public Fine(int fineId, Member member, BookCopy bookCopy, double fineAmount, LocalDate fineDate, String status) {
		super();
		this.fineId = fineId;
		this.member = member;
		this.bookCopy = bookCopy;
		this.fineAmount = fineAmount;
		this.fineDate = fineDate;
		this.status = status;
	}

	public int getFineId() {
		return fineId;
	}

	public void setFineId(int fineId) {
		this.fineId = fineId;
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

	public double getFineAmount() {
		return fineAmount;
	}

	public void setFineAmount(double fineAmount) {
		this.fineAmount = fineAmount;
	}

	public LocalDate getFineDate() {
		return fineDate;
	}

	public void setFineDate(LocalDate fineDate) {
		this.fineDate = fineDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
