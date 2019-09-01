package com.example.demo.model;

public class SupplierProductAnalysis {

    private int supplierId;
    private int productId;
    private String productName;
    private String productType;
    private double pastCost;
    private double presentCost;
    private double costIncreament;
    private double costDecreament;
    private int referenceSupplierId;
    private int referenceProductId;
    public int getReferenceProductId() {
        return referenceProductId;
    }

    public void setReferenceProductId(int referenceProductId) {
        this.referenceProductId = referenceProductId;
    }


    public int getSupplierId() {
        return supplierId;
    }

    public void setSupplierId(int supplierId) {
        this.supplierId = supplierId;
    }

    public int getProductId() {
        return productId;
    }

    public void setProductId(int productId) {
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

    public double getPastCost() {
        return pastCost;
    }

    public void setPastCost(double pastCost) {
        this.pastCost = pastCost;
    }

    public double getPresentCost() {
        return presentCost;
    }

    public void setPresentCost(double presentCost) {
        this.presentCost = presentCost;
    }

    public double getCostIncreament() {
        return costIncreament;
    }

    public void setCostIncreament(double costIncreament) {
        this.costIncreament = costIncreament;
    }

    public double getCostDecreament() {
        return costDecreament;
    }

    public void setCostDecreament(double costDecreament) {
        this.costDecreament = costDecreament;
    }

    public int getReferenceSupplierId() {
        return referenceSupplierId;
    }

    public void setReferenceSupplierId(int referenceSupplierId) {
        this.referenceSupplierId = referenceSupplierId;
    }
}
