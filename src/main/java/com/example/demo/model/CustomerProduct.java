package com.example.demo.model;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

public class CustomerProduct {

	private long customerPurchaseId;
	private long customerId;
	private long productId;
    private int quantity;
    private double amount;
	@JsonFormat(pattern="yyyy-MM-dd")
    private Date buyDate;
	private String username;

	public CustomerProduct()
	{

	}
	public long getCustomerPurchaseId() {
		return customerPurchaseId;
	}
	public void setCustomerPurchaseId(long customerPurchaseId) {
		this.customerPurchaseId = customerPurchaseId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public Date getBuyDate() {
		return buyDate;
	}
	public void setBuyDate(Date buyDate) {
		this.buyDate = buyDate;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public long getProductId() {
		return productId;
	}

	public void setProductId(long productId) {
		this.productId = productId;
	}
}
