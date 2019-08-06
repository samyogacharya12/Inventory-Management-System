package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

public class Trash {

	private long trashId;
	private long productId;
    private String productName;
    private String productType;
    private double price;
    @Min(value = 0L, message = "The value must be positive")
    private int quantity;
    private String image;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date magnifactureDate;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expiryDate;
	public long getProductId() {
		return productId;
	}
	public long getTrashId() {
		return trashId;
	}
	public void setTrashId(long trashId) {
		this.trashId = trashId;
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
	public void setProductType(String productType) {
		this.productType = productType;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
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
	
}
