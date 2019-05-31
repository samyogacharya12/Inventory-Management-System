package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Supplier_View {

	private long supplier_id;
	private String supplier_name;
	private String supplier_type;
	private int quantity;
	private double cost;
	private String temporary_address;
	private String permanent_address;
	private long product_product_id;
	private long product_id;
	private String image;
	private String product_name;
	private long supplier_supplier_id;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date buy_date;
	public long getSupplier_id() {
		return supplier_id;
	}	
	public Date getBuy_date() {
		return buy_date;
	}
	public void setBuy_date(Date buy_date) {
		this.buy_date = buy_date;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getSupplier_supplier_id() {
		return supplier_supplier_id;
	}
	public void setSupplier_supplier_id(long supplier_supplier_id) {
		this.supplier_supplier_id = supplier_supplier_id;
	}
	public void setSupplier_id(long supplier_id) {
		this.supplier_id = supplier_id;
	}
	public String getSupplier_name() {
		return supplier_name;
	}
	public void setSupplier_name(String supplier_name) {
		this.supplier_name = supplier_name;
	}
	public String getSupplier_type() {
		return supplier_type;
	}
	public void setSupplier_type(String supplier_type) {
		this.supplier_type = supplier_type;
	}
	public int getQuantity() {
		return quantity;
	}
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public String getTemporary_address() {
		return temporary_address;
	}
	public void setTemporary_address(String temporary_address) {
		this.temporary_address = temporary_address;
	}
	public String getPermanent_address() {
		return permanent_address;
	}
	public void setPermanent_address(String permanent_address) {
		this.permanent_address = permanent_address;
	}
	public long getProduct_product_id() {
		return product_product_id;
	}
	public void setProduct_product_id(long product_product_id) {
		this.product_product_id = product_product_id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	
	public long getProduct_id() {
		return product_id;
	}
	public void setProduct_id(long product_id) {
		this.product_id = product_id;
	}


}
