package com.example.demo.service;

import com.example.demo.model.Customer_Product;
import com.example.demo.model.Product;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;

@Service
public class ProductDetailServiceImpl {


    @Autowired
    private ProductRepository productRepository;

    public void insertIntoProduct(Product product) {

        productRepository.insertIntoProduct(product);
    }


    public List<Product> findByProductName(String product_name) {

        return productRepository.findByProductName(product_name);
    }

    public void updateExpiredProduct(Product product) {

         productRepository.updateExpiredProduct(product);
    }

    public Product getProductById(long product_id) {

      return  productRepository.getProductById(product_id);
    }

    public List<Product> getAllProductInfo() {

        return productRepository.getAllProductInfo();
    }


    public void updateIntoProduct(Product product) {

        productRepository.updateIntoProduct(product);
    }

    public void deleteproductinfo(long product_id) {

        productRepository.deleteProductInfo(product_id);
    }

    public Product getQuantityById(long id) {

      return productRepository.getQuantityById(id);
    }

    public Customer_Product getQuantityByCustomerid(long customer_id, long product_id) {

        return productRepository.getQuantityByCustomerId(customer_id, product_id);
    }

    public int getTotalNoOfProduct() {

        return productRepository.getTotalNoOfProduct();
    }

    public double getSumOfPrice() {

        return productRepository.getSumOfPrice();
    }


    public int getTotalNoOfQuantity() {

        return productRepository.getTotalNoOfQuantity();
    }


    public int getTotalNoOfProduct(String product_name) {

        return productRepository.getTotalNoOfProduct(product_name);
    }


    public double getSumOfprice(String product_name) {

        return productRepository.getSumOfPrice(product_name);
    }

    public int getTotalNoOfQuantity(String product_name) {

        return productRepository.getTotalNoOfQuantity();
    }

    public List<String> getExpiredProduct(String expiry_date) {

        return productRepository.getExpiredProduct(expiry_date);
    }


    public int getNoOfExpiredProduct(String expiry_date) {

        return productRepository.getNoOfExpiredProduct(expiry_date);
    }


    public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {

        return productRepository.createPdf(products, context, request, response);
    }

    public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {

        return productRepository.createExcel(products, context, request, response);
    }




}
