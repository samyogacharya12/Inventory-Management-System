package com.example.demo.repository;

import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;
import com.example.demo.model.Product;
import com.example.demo.model.Projectuser;
import com.example.demo.model.Supplier_View;
import com.example.demo.model.Trash;

public interface UserRepository {

  Projectuser findByUsername(String username);
//  List<Product> findByProductName(String product_name);
  public void Insert(Projectuser projectuser);
//  public void updateExpiredProduct(Product product);
  Projectuser getUserById(long id);
  public void deleteUserInfo(long user_id);
//  Product getproductbyid(long product_id);
  public List<Projectuser> getAllUserInfo();
//  public List<Product> getAllProductInfo();
//  public void insertintoproduct(Product product);
  public void updateIntoUser(Projectuser user);
//  public void updateintoproduct(Product product);
//  public void deleteproductinfo(long product_id);
//  public Product getquantitybyid(long id);
//  public Customer_Product getquantitybycustomerid(long customer_id, long product_id);
//  public int getTotalNoOfProduct();
//  public double getSumOfPrice();
//  public int getTotalNoOfQuantity();
//  public int getTotalNoOfProduct(String product_name);
//  public double getSumOfprice(String product_name);
  public int getTotalNoOfQuantity(String product_name);
  public int getNoofUsers();
//  public List<String> getExpiredProduct(String expiry_date);
//  public int getNoOfExpiredProduct(String expiry_date);
//  public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);
//  public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
