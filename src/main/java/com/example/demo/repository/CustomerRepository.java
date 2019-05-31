package com.example.demo.repository;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Customer;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;

public interface CustomerRepository {

	public List<Customer> getAllCustomer();
	public Customer_Product getCustomerById(long customer_id);
	public Customer_View getCustomerId(long customer_id, long product_id);
	public List<Customer_View> getAllCustomerInfo();
	 public void insertintocustomer(Customer customer);
	  public void insertintocustomerproduct(Customer_Product customerproduct);
	  public void updateintopersonalcustomer(Customer customer);
	  public void updateintocustomerproduct(Customer_Product customerproduct);
	  public void deleteintocustomerview(long customer_id, long product_id);
	  public void deleteintocustomer(long customer_id, long product_id);
	  public List<Customer_View> getCustomerByBuyDate(String[] buy_date);
	  public List<Customer_View> getCustomerByName(String customer_name);
	  public int getTotalCustomer();
	  public int sumOfQuantity();
	  public double sumOfAmount();
	  public int getTotalProduct();
	  public int getTotalCustomer(String customer_name);
	  public int sumOfQuantity(String customer_name);
	  public double sumOfAmount(String customer_name);
	  public int getTotalProduct(String customer_name);
	  public int getTotalCustomer(String[] buy_date);
	  public Integer sumOfQuantity(String[] buy_date);
	  public Double sumOfAmount(String[] buy_date);
	  public int getTotalProduct(String[] buy_date);
	  public int getPresentDate(String buy_date);
	  public int getNumberofCustomersToday(String buy_date);
	  public Double getPresentRevenue(String buy_date);
	  public boolean createPdf(List<Customer_View> customers, ServletContext context, HttpServletRequest request, HttpServletResponse response);
	  public boolean createExcel(List<Customer_View> customers, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
