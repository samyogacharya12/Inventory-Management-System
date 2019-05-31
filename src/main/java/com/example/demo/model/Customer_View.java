package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer_View {

	private long customer_id;
	private long customer_customer_id;
	private String customer_name;
	private int quantity;
	private double amount;
	private String permanent_address;
	private String temporary_address;
	private String country;
	private long product_id;
	private long product_product_id;
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date buy_date;
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public long getCustomer_customer_id() {
		return customer_customer_id;
	}
	public void setCustomer_customer_id(long customer_customer_id) {
		this.customer_customer_id = customer_customer_id;
	}
	
	public long getProduct_product_id() {
		return product_product_id;
	}
	public void setProduct_product_id(long product_product_id) {
		this.product_product_id = product_product_id;
	}
	private String product_name;
	private int phone_number;
	public int getPhone_number() {
		return phone_number;
	}
	public void setPhone_number(int phone_number) {
		this.phone_number = phone_number;
	}
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	
}
