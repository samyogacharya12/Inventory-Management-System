package com.example.demo.model;


import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;


public class Product {

	private long productId;
    private String productName;
    private String productType;
    private Double price;
    @Min(value = 0L, message = "The value must be positive")
    private int quantity;
    private String image;
    private String isExpired;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date magnifactureDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expiryDate;

	public String getIsExpired() {
		return isExpired;
	}
	public void setIsExpired(String isExpired) {
		this.isExpired = isExpired;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public long getProductId() {
		return productId;
	}
	public void setProductId(long productId) {
		this.productId = productId;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getProductType() {
		return productType;
	}
	public Date getMagnifactureDate() {
		return magnifactureDate;
	}
	public void setMagnifactureDate(Date magnifactureDate) {
		this.magnifactureDate = magnifactureDate;
	}
	public Date getExpiryDate() {
		return expiryDate;
	}
	public void setExpiryDate(Date expiryDate) {
		this.expiryDate = expiryDate;
	}
	public void setProductType(String productType) { this.productType = productType;
	}
	public Double getPrice() { return price;
	}
	public void setPrice(Double price) { this.price = price;
	}
}
