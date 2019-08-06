package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import java.util.Date;

public class PurchaseProduct {

    private long productId;
    private String productName;
    private String productType;
    private double price;
    @Min(value = 0L, message = "The value must be positive")
    private int quantity;
    private String image;
    private String isExpired;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date magnifactureDate;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date expiryDate;
    private long purchaseId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date purchaseDate;
    private long userId;
    private String username;

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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

    public String getIsExpired() {
        return isExpired;
    }

    public void setIsExpired(String isExpired) {
        this.isExpired = isExpired;
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

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

}
