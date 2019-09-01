package com.example.demo.repository;

import com.example.demo.model.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface ProductRepository {

     void insertIntoProduct(Product product);
     void insertIntoProductAnalysis(Map map);
     List<ProductAnalysis> getAllProductAnalysis();
     Integer countProductByNameAndType(String productName, String productType);
     List<PurchaseProduct> getProductByNameTypeAndPurchaseDate(Date purchaseDate);
     List<PurchaseProduct> getProductByNameTypeLessThanPurchaseDate(String productName, String productType, Date purchaseDate);
     List<PurchaseProduct> findByProductName(String productName);
     void updateExpiredProduct(Product product);
     Product getProductById(long productId);
     List<PurchaseProduct> getAllProductInfo();
     List<ProductAnalysis> getDataByName(String productName);
     void updateIntoProduct(Product product);
     void deleteProductInfo(long productId);
//     Product getQuantityById(long id);
     CustomerProduct getQuantityByCustomerId(long customerId, long productId);
     int getTotalNoOfProduct();
     double getSumOfPrice();
     int getTotalNoOfQuantity();
     int getTotalNoOfProduct(String productName);
     double getSumOfPrice(String productName);
     Integer getTotalNoOfQuantity(String productName);
     List<Product> getExpiredProduct(String expiryDate);
     int getNoOfExpiredProduct(String expiryDate);

}
