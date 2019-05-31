package com.example.demo.model;

import java.util.Date;

import javax.validation.constraints.Min;

import org.springframework.format.annotation.DateTimeFormat;

public class Product {

	private long product_id;
    private String product_name;
    private String product_type;
    private double price;
    @Min(value = 0L, message = "The value must be positive")
    private int quantity;
    private String image;
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
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date magnifacture_date;
	@DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expiry_date;
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
	public String getProduct_type() {
		return product_type;
	}
	public void setProduct_type(String product_type) {
		this.product_type = product_type;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public Date getMagnifacture_date() {
		return magnifacture_date;
	}
	public void setMagnifacture_date(Date magnifacture_date) {
		this.magnifacture_date = magnifacture_date;
	}
	public Date getExpiry_date() {
		return expiry_date;
	}
	public void setExpiry_date(Date expiry_date) {
		this.expiry_date = expiry_date;
	}
    
}
