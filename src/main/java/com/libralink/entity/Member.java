package com.libralink.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.Digits;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;

/*
 
 {
  "memberId": 1,
  "firstName": "John",
  "lastName": "Doe",
  "phoneNumber": 1234567890,
  "email": "john.doe@example.com",
  "houseNo": 123,
  "lane": "Main Street",
  "address1": "Apt 456",
  "address2": "Near Central Park",
  "city": "Metropolis",
  "state": "Gotham",
  "pincode": 123456,
  "checkouts": [],
  "fines": [],
  "membershipExpirationDate": "2023-12-31"
}

 
 */

@Entity
public class Member {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long memberId;

    @NotBlank(message = "First name is required")
    @Size(max = 255, message = "First name must be less than or equal to 255 characters")
    private String firstName;

    @NotBlank(message = "Last name is required")
    @Size(max = 255, message = "Last name must be less than or equal to 255 characters")
    private String lastName;

    @Positive(message = "Phone number must be a positive value")
    private long phoneNumber;

    @Email(message = "Invalid email address")
    private String email;

    @Positive(message = "House number must be a positive value")
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
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Checkout> checkouts;

    @JsonIgnore 
    @OneToMany(mappedBy = "member", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @Fetch(value = FetchMode.SUBSELECT)
    private List<Fine> fines;

    @NotNull(message = "Membership expiration date is required")
    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate membershipExpirationDate;
	
	public Member() {
		super();
	}

	public Member(Long memberId, String firstName, String lastName, long phoneNumber, String email, int houseNo,
			String lane, String address1, String address2, String city, String state, int pincode,
			List<Checkout> checkouts, List<Fine> fines, LocalDate membershipExpirationDate) {
		super();
		this.memberId = memberId;
		this.firstName = firstName;
		this.lastName = lastName;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.houseNo = houseNo;
		this.lane = lane;
		this.address1 = address1;
		this.address2 = address2;
		this.city = city;
		this.state = state;
		this.pincode = pincode;
		this.checkouts = checkouts;
		this.fines = fines;
		this.membershipExpirationDate = membershipExpirationDate;
	}

	public Long getMemberId() {
		return memberId;
	}

	public void setMemberId(Long memberId) {
		this.memberId = memberId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
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

	public List<Checkout> getCheckouts() {
		return checkouts;
	}

	public void setCheckouts(List<Checkout> checkouts) {
		checkouts = checkouts;
	}

	public List<Fine> getFines() {
		return fines;
	}

	public void setFines(List<Fine> fines) {
		this.fines = fines;
	}

	public LocalDate getMembershipExpirationDate() {
		return membershipExpirationDate;
	}

	public void setMembershipExpirationDate(LocalDate membershipExpirationDate) {
		this.membershipExpirationDate = membershipExpirationDate;
	}

}
