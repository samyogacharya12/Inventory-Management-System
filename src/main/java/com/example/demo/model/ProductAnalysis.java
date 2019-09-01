package com.example.demo.model;

public class ProductAnalysis {

    private long productId;
    private String productName;
    private double pastPrice;
    private double presentPrice;
    private double priceIncreament;
    private double priceDecreament;
    private long referenceProductId;


    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public double getPresentPrice() {
        return presentPrice;
    }

    public void setPresentPrice(double presentPrice) {
        this.presentPrice = presentPrice;
    }

    public long getProductId() {
        return productId;
    }

    public void setProductId(long productId) {
        this.productId = productId;
    }

    public double getPastPrice() {
        return pastPrice;
    }

    public void setPastPrice(double pastPrice) {
        this.pastPrice = pastPrice;
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

    public long getReferenceProductId() {
        return referenceProductId;
    }

    public void setReferenceProductId(long referenceProductId) {
        this.referenceProductId = referenceProductId;
    }
}
