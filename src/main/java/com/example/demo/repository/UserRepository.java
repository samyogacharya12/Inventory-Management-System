package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import com.example.demo.model.Projectuser;
import com.example.demo.model.Usertemp;

public interface UserRepository {

  Projectuser findByUsername(String username);
  Projectuser getUserIdByUsername(String username);
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
  void insetIntoUserTemp(Map map);
  Map getUserTempData();
  void deleteIntoUserTemp(String username);
//  public void updateintoproduct(Product product);
//  public void deleteproductinfo(long product_id);
//  public Product getquantitybyid(long id);
//  public CustomerProduct getquantitybycustomerid(long customer_id, long product_id);
//  public int getTotalNoOfProduct();
//  public double getSumOfPrice();
//  public int getTotalNoOfQuantity();
//  public int getTotalNoOfProduct(String product_name);
//  public double getSumOfPrice(String product_name);
  public int getTotalNoOfQuantity(String product_name);
  public int getNoofUsers();
//  public List<String> getExpiredProduct(String expiry_date);
//  public int getNoOfExpiredProduct(String expiry_date);
//  public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);
//  public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
