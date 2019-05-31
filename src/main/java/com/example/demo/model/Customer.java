package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Max;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer {
private long customer_id;
private String customer_name;
@DateTimeFormat(pattern="yyyy-MM-dd")
private Date buy_date;
private String permanent_address;
private String temporary_address;
@Max(999999999)
private int phone_number;
private String Country;
public long getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(long customer_id) {
	this.customer_id = customer_id;
}
public String getCustomer_name() {
	return customer_name;
}
public void setCustomer_name(String customer_name) {
	this.customer_name = customer_name;
}
public Date getBuy_date() {
	return buy_date;
}
public void setBuy_date(Date buy_date) {
	this.buy_date = buy_date;
}
public String getPermanent_address() {
	return permanent_address;
}
public void setPermanent_address(String permanent_address) {
	this.permanent_address = permanent_address;
}
public String getTemporary_address() {
	return temporary_address;
}
public void setTemporary_address(String temporary_address) {
	this.temporary_address = temporary_address;
}
public int getPhone_number() {
	return phone_number;
}
public void setPhone_number(int phone_number) {
	this.phone_number = phone_number;
}
public String getCountry() {
	return Country;
}
public void setCountry(String country) {
	Country = country;
}

	
}
