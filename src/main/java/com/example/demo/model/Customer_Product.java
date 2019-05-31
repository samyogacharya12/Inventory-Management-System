package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Customer_Product {

	private long customer_customer_id;
	private long product_product_id;
    private int quantity;
    private double amount;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date buy_date;
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
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
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	
}
