package com.example.demo.repository;

import com.example.demo.model.Customer_Product;
import com.example.demo.model.Product;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ProductRepository {

    public void insertIntoProduct(Product product);
    List<Product> findByProductName(String product_name);
    public void updateExpiredProduct(Product product);
    Product getProductById(long product_id);
    public List<Product> getAllProductInfo();
    public void updateIntoProduct(Product product);
    public void deleteProductInfo(long product_id);
    public Product getQuantityById(long id);
    public Customer_Product getQuantityByCustomerId(long customer_id, long product_id);
    public int getTotalNoOfProduct();
    public double getSumOfPrice();
    public int getTotalNoOfQuantity();
    public int getTotalNoOfProduct(String product_name);
    public double getSumOfPrice(String product_name);
    public int getTotalNoOfQuantity(String product_name);
    public List<String> getExpiredProduct(String expiry_date);
    public int getNoOfExpiredProduct(String expiry_date);
    public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);
    public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);

}
