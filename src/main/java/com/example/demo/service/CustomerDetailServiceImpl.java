package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;
import com.example.demo.repository.CustomerRepository;
@Service
public class CustomerDetailServiceImpl {

	@Autowired
	private CustomerRepository customerRepository;
	
	
	public Customer_Product getCustomerByid(long customer_id)
	{
		return customerRepository.getCustomerById(customer_id);
	}
	
	public List<Customer_View> getAllCustomerInfo()
	{
		return customerRepository.getAllCustomerInfo();
	}
	
	public void insertintocustomer(Customer customer)
	{
		customerRepository.insertintocustomer(customer);
	}
	
	public void insertintocustomerproduct(Customer_Product customerproduct)
	{
		customerRepository.insertintocustomerproduct(customerproduct);
	}
	
	public Customer_View getCustomerById(long customer_id, long product_id)
	{
		return customerRepository.getCustomerId(customer_id, product_id);
	}
	
	public void updateintopersonalcustomer(Customer customer)
	{
		customerRepository.updateintopersonalcustomer(customer);
	}
	
	public void updateintocustomerproduct(Customer_Product customerproduct) 
	{
      customerRepository.updateintocustomerproduct(customerproduct);                   
	}
	
	public void deleteintocustomerview(long customer_id, long product_id)
	{
		customerRepository.deleteintocustomerview(customer_id, product_id);
	}
	
	public void deleteintocustomer(long customer_id, long product_id)
	{
		customerRepository.deleteintocustomer(customer_id, product_id);
	}
	
	public List<Customer_View> getCustomerBuyDate(String buy_date[])
	{
		return customerRepository.getCustomerByBuyDate(buy_date);
	}
	
	public List<Customer_View> getCustomerByName(String customer_name)
	{
		return customerRepository.getCustomerByName(customer_name);
	}
	
	public int getTotalCustomer() {
		
		return customerRepository.getTotalCustomer();
	}
	
	public int sumOfQuantity() {
		
		return customerRepository.sumOfQuantity();
	}
	
	public double sumOfAmount() {
		
		return customerRepository.sumOfAmount();
	}
	
	public int getTotalProduct() {
		
		return customerRepository.getTotalProduct();
	}
	
	public int getTotalCustomer(String customer_name) {
		
		return customerRepository.getTotalCustomer(customer_name);
	}
	
	public int sumOfQuantity(String customer_name) {
		
		return customerRepository.sumOfQuantity(customer_name);
	}
	
	public double sumOfAmount(String customer_name) {
		
		return customerRepository.sumOfAmount(customer_name);
	}
	
	public int getTotalProduct(String customer_name) {
		
		return customerRepository.getTotalProduct(customer_name);
	}
	
	public int getTotalCustomer(String[] buy_date) {
		
		return customerRepository.getTotalCustomer(buy_date);	
	}
	
	public Double sumOfAmount(String[] buy_date) {
		
		return customerRepository.sumOfAmount(buy_date);
	}
	
	public Integer sumOfQuantity(String[] buy_date) {
		
		return customerRepository.sumOfQuantity(buy_date);
	}
	
	 public int getTotalProduct(String[] buy_date)
	 {
		 return customerRepository.getTotalProduct(buy_date);
	 }
	
		public int getPresentDate(String buy_date)
		{
			return customerRepository.getPresentDate(buy_date);
		}
	 
		  public int getNumberofCustomersToday(String buy_date){
			  return customerRepository.getNumberofCustomersToday(buy_date);
		  }
		
	public boolean createPdf(List<Customer_View> customers, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		return customerRepository.createPdf(customers, context, request, response);
}
	public boolean createExcel(List<Customer_View> customers, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
        	return customerRepository.createExcel(customers, context, request, response);
}
	
	public Double getPresentRevenue(String buy_date) {
		
		return customerRepository.getPresentRevenue(buy_date);
	}
	
	
}