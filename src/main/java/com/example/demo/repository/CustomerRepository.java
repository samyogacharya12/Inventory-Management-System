package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerProduct;
import com.example.demo.model.CustomerView;
import org.springframework.web.bind.annotation.RequestParam;

public interface CustomerRepository {

	 List<CustomerView> getAllCustomer();
	 CustomerProduct getCustomerById(long customerId);
	 List<Integer> getProductIdNotEqualToCustomerId(long customerId);
	 CustomerView getCustomerId(long customerId, long productId);
	 CustomerView getDataByCustomerId(long customerId ,long customerPurchaseId);
	 List<CustomerView> getAllCustomerInfo();
	  void insertIntoCustomer(Customer customer);
	   void insertIntoCustomerProduct(CustomerProduct customerproduct);
	   void updateIntoPersonalCustomer(Customer customer);
	   void updateIntoCustomerProduct(CustomerView customerproduct);
	   void deleteIntoCustomerView(long customerId, long productId);
	   void deleteIntoCustomer(long customerId, long productId);
	   List<CustomerView> getCustomerByBuyDate(String[] buyDate);
	   List<CustomerView> getCustomerByName(String customerName);
	   int getTotalCustomer();
	   int sumOfQuantity();
	   double sumOfAmount();
	   int getTotalProduct();
	   int getTotalCustomer(String customerName);
	   int sumOfQuantity(String customerName);
	   double sumOfAmount(String customerName);
	   int getTotalProduct(String customerName);
	   int getTotalCustomer(String[] buyDate);
	   Integer sumOfQuantity(String[] buyDate);
	   Double sumOfAmount(String[] buyDate);
	   int getTotalProduct(String[] buyDate);
	   int getPresentDate(String buyDate);
	   int getNumberofCustomersToday(String buyDate);
	   Double getPresentRevenue(String buyDate);
}
