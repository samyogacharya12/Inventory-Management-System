package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Max;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {

	private long customerId;
private String customerName;
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date buyDate;
private String permanentAddress;
private String temporaryAddress;
private String email;
@Max(999999999)
private int phoneNumber;
private String Country;
public long getCustomerId() {
	return customerId;
}
public void setCustomerId(long customerId) {
	this.customerId = customerId;
}
public String getCustomerName() {
	return customerName;
}
public void setCustomerName(String customerName) {
	this.customerName = customerName;
}
public Date getBuyDate() {
	return buyDate;
}
public void setBuyDate(Date buyDate) {
	this.buyDate = buyDate;
}
public String getPermanentAddress() {
	return permanentAddress;
}
public void setPermanentAddress(String permanentAddress) {
	this.permanentAddress = permanentAddress;
}
public String getTemporaryAddress() {
	return temporaryAddress;
}
public void setTemporaryAddress(String temporaryAddress) {
	this.temporaryAddress = temporaryAddress;
}
public int getPhoneNumber() {
	return phoneNumber;
}
public void setPhoneNumber(int phoneNumber) {
	this.phoneNumber = phoneNumber;
}
public String getCountry() {
	return Country;
}
public void setCountry(String country) {
	Country = country;
}
public String getEmail() { return email;	}
public void setEmail(String email) { this.email = email; }
	
}
