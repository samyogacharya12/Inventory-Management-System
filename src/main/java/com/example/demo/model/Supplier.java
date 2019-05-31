package com.example.demo.model;

public class Supplier {

	private long supplier_id;
	private String supplier_name;
	private String supplier_type;
	private String temporary_address;
	private String permanent_address;
    private String image;
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public long getSupplier_id() {
		return supplier_id;
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
}
