package com.example.demo.model;

import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

public class Purchase {

    private long purchaseId;
    private long productId;
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date purchaseDate;
    private long userId;
    private String username;
    private double pastPrice;
    private double presentPrice;
    private double priceIncreament;

    public double getPastPrice() {
        return pastPrice;
    }
    public void setPastPrice(double pastPrice) {
        this.pastPrice = pastPrice;
    }
    public double getPresentPrice() {
        return presentPrice;
    }
    public void setPresentPrice(double presentPrice) {
        this.presentPrice = presentPrice;
    }
    public double getPriceIncreament() {
        return priceIncreament;
    }
    public void setPriceIncreament(double priceIncreament) {
        this.priceIncreament = priceIncreament;
    }
    public double getPriceDecreament() {
        return priceDecreament;
    }
    public void setPriceDecreament(double priceDecreament) {
        this.priceDecreament = priceDecreament;
    }

    private  double priceDecreament;

    public long getPurchaseId() {
        return purchaseId;
    }

    public void setPurchaseId(long purchaseId) {
        this.purchaseId = purchaseId;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public Date getPurchaseDate() {
        return purchaseDate;
    }

    public void setPurchaseDate(Date purchaseDate) {
        this.purchaseDate = purchaseDate;
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
