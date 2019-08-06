package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.CustomerProduct;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerView;
import com.example.demo.repository.CustomerRepository;
@Service
public class CustomerDetailServiceImpl {

	@Autowired
	private CustomerRepository customerRepository;

	@Autowired
	private PdfService pdfService;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private ProductDetailServiceImpl productDetailService;

      public Integer updateCustomerProductQuantity(long customerPurchaseId , long productId,  int quantity,  long customerId)
	{
		Product product=productDetailService.getProductById(productId);
		CustomerView customerView=getDataByCustomerId(customerId, customerPurchaseId);
		int dbquantity=product.getQuantity();
//		customerProduct=productDetailService.getQuantityByCustomerid(customerId, productId);

		int currentQuantity=customerView.getQuantity();
		if(currentQuantity==quantity)
		{
			System.out.println("The given quantity are equal");
		}
		else if(currentQuantity>quantity)
		{
			currentQuantity=currentQuantity-quantity;
			dbquantity=dbquantity+currentQuantity;
			System.out.println(dbquantity);
			product.setQuantity(dbquantity);
			if(dbquantity>=0)
			{
				productDetailService.updateIntoProduct(product);
			}
			else
			{
				System.out.println("Sorry your data is illegel");
			}
		}
		else if(currentQuantity<quantity)
		{
			currentQuantity=quantity-currentQuantity;
			dbquantity=dbquantity-currentQuantity;
			product.setQuantity(dbquantity);
			if(dbquantity>=0)
			{
				productDetailService.updateIntoProduct(product);
			}
			else
			{
				System.out.println("sorry your data is illegel");
			}

		}
		System.out.println(customerView.getQuantity());
		return customerView.getQuantity();
	}


	public CustomerProduct getCustomerById(long customerId)
	{
		return customerRepository.getCustomerById(customerId);
	}

	public List<Integer> getProductIdNotEqualToCustomerId(long customerId) {
	 return  customerRepository.getProductIdNotEqualToCustomerId(customerId);
	}

	public List<CustomerView> getAllCustomerInfo()
	{
		return customerRepository.getAllCustomerInfo();
	}
	
	public void insertintocustomer(Customer customer)
	{
		customerRepository.insertIntoCustomer(customer);
	}
	
	public void insertintocustomerproduct(CustomerProduct customerproduct)
	{
		customerRepository.insertIntoCustomerProduct(customerproduct);
	}


	public CustomerView getCustomerById(long customerId, long productId)
	{
		return customerRepository.getCustomerId(customerId, productId);
	}
	public CustomerView getDataByCustomerId(long customerId, long customerPurchaseId) {

		return customerRepository.getDataByCustomerId(customerId, customerPurchaseId);
	}

    public Product substractCustomerProductQuantity(Product product, int quantity)
	{
		int dbQuantity=product.getQuantity();
		dbQuantity=dbQuantity-quantity;
		product.setQuantity(dbQuantity);
		return product;
	}

	public Map calculationOfAmount(int productId, int quantity)
	{
		Product product=productDetailService.getProductById(productId);
		double amount=product.getPrice()*quantity;
		Map<String, Object> map=new HashMap<>();
		map.put("amount", amount);
		return map;
	}
	
	public void updateIntoPersonalCustomer(Customer customer)
	{
		customerRepository.updateIntoPersonalCustomer(customer);
	}
	
	public void updateIntoCustomerProduct(CustomerView customerproduct)
	{
      customerRepository.updateIntoCustomerProduct(customerproduct);
	}
	
	public void deleteIntoCustomerView(long customerId, long productId)
	{
		customerRepository.deleteIntoCustomerView(customerId, productId);
	}
	
	public void deleteIntoCustomer(long customerId, long productId)
	{
		customerRepository.deleteIntoCustomer(customerId, productId);
	}
	
	public List<CustomerView> getCustomerBuyDate(String buyDate[])
	{
		return customerRepository.getCustomerByBuyDate(buyDate);
	}
	
	public List<CustomerView> getCustomerByName(String customerName)
	{
		return customerRepository.getCustomerByName(customerName);
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
	
	public int getTotalCustomer(String customerName) {
		
		return customerRepository.getTotalCustomer(customerName);
	}
	
	public int sumOfQuantity(String customerName) {
		
		return customerRepository.sumOfQuantity(customerName);
	}
	
	public double sumOfAmount(String customerName) {
		
		return customerRepository.sumOfAmount(customerName);
	}
	
	public int getTotalProduct(String customerName) {
		
		return customerRepository.getTotalProduct(customerName);
	}
	
	public int getTotalCustomer(String[] buyDate) {
		
		return customerRepository.getTotalCustomer(buyDate);
	}
	
	public Double sumOfAmount(String[] buyDate) {
		
		return customerRepository.sumOfAmount(buyDate);
	}
	
	public Integer sumOfQuantity(String[] buyDate) {
		
		return customerRepository.sumOfQuantity(buyDate);
	}
	
	 public int getTotalProduct(String[] buyDate)
	 {
		 return customerRepository.getTotalProduct(buyDate);
	 }
	
		public int getPresentDate(String buyDate)
		{
			return customerRepository.getPresentDate(buyDate);
		}
	 
		  public int getNumberofCustomersToday(String buyDate){
			  return customerRepository.getNumberofCustomersToday(buyDate);
		  }
		
	public boolean createPdfForCustomers(List<CustomerView> customers, ServletContext context, HttpServletRequest request,
										 HttpServletResponse response) {
      	return pdfService.createPdfForCustomers(customers, context, request,response);
}
	public boolean createExcelForCustomers(List<CustomerView> customers, ServletContext context, HttpServletRequest request,
										   HttpServletResponse response) {
      	return excelService.createExcelForCustomers(customers, context, request, response);
}
	
	public Double getPresentRevenue(String buyDate) {
		
		return customerRepository.getPresentRevenue(buyDate);
	}
	
	
}