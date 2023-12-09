package com.libralink.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PastOrPresent;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
public class Branch {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int branchId;

    @NotBlank(message = "Branch name is required")
    @Size(max = 255, message = "Branch name must be less than or equal to 255 characters")
    private String branchName;

    @NotNull(message = "Phone number is required")
    private long phoneNumber;

    @Email(message = "Invalid email address")
    private String email;

    @NotNull(message = "House number is required")
    private int houseNo;

    @NotBlank(message = "Lane is required")
    @Size(max = 255, message = "Lane must be less than or equal to 255 characters")
    private String lane;

    @NotBlank(message = "Address line 1 is required")
    @Size(max = 255, message = "Address line 1 must be less than or equal to 255 characters")
    private String address1;

    @Size(max = 255, message = "Address line 2 must be less than or equal to 255 characters")
    private String address2;

    @NotBlank(message = "City is required")
    @Size(max = 255, message = "City must be less than or equal to 255 characters")
    private String city;

    @NotBlank(message = "State is required")
    @Size(max = 255, message = "State must be less than or equal to 255 characters")
    private String state;

    @Digits(integer = 6, fraction = 0, message = "PIN code must be 6 digits")
    private int pincode;

    @JsonIgnore 
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<BookCopy> bookCopies;

    @JsonIgnore 
    @OneToMany(mappedBy = "branch", fetch = FetchType.EAGER)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Inventory> inventories;
	
	public Branch() {
		super();
	}

	public Branch(int branchId, String branchName, long phoneNumber, String email, int houseNo, String lane,
			String address1, String address2, String city, String state, int pincode, List<BookCopy> bookCopies,
			List<Inventory> inventories) {
		super();
		this.branchId = branchId;
		this.branchName = branchName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.houseNo = houseNo;
		this.lane = lane;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.bookCopies = bookCopies;
		this.inventories = inventories;
	}

	public int getBranchId() {
		return branchId;
	}

	public void setBranchId(int branchId) {
		this.branchId = branchId;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public long getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(long phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getLane() {
		return lane;
	}

	public void setLane(String lane) {
		this.lane = lane;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public int getPincode() {
		return pincode;
	}

	public void setPincode(int pincode) {
		this.pincode = pincode;
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
