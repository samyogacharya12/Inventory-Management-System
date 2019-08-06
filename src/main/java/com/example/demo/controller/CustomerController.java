package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.*;
import com.example.demo.service.NotificationDetailServiceImpl;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDetailServiceImpl customerDetailServiceImpl;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;

	@Autowired
	private ProductDetailServiceImpl productDetailService;

	@Autowired
	private NotificationDetailServiceImpl notificationDetailService;

	@Autowired
	private ServletContext context;
	@GetMapping("/getCustomerForm")
	public String getCustomerForm()
	{
		return "AddCustomer.jsp";
	}
	
	@GetMapping("/getCustomerByName")
	public ModelAndView getCustomerByName(@RequestParam String customerName, Model model)
	{
		List<CustomerView> customerView=customerDetailServiceImpl.getCustomerByName(customerName);
		int totalcustomer=customerDetailServiceImpl.getTotalCustomer(customerName);
		model.addAttribute("totalcustomer", totalcustomer);
		int totalQuantity=customerDetailServiceImpl.sumOfQuantity(customerName);
		model.addAttribute("totalQuantity", totalQuantity);
		double totalAmount=customerDetailServiceImpl.sumOfAmount(customerName);
		model.addAttribute("totalAmount", totalAmount);
		int totalProduct=customerDetailServiceImpl.getTotalProduct(customerName);
		model.addAttribute("totalProduct", totalProduct);
		return new ModelAndView("customerList.jsp", "customerview", customerView);
	}
	@GetMapping("/getcustomerbyDate")
	public ModelAndView getCustomerByDate(@RequestParam(value="sellDate[]") String sellDate[], Model model)
	{
		List<CustomerView> customerView=customerDetailServiceImpl.getCustomerBuyDate(sellDate);
		int totalCustomer=customerDetailServiceImpl.getTotalProduct(sellDate);
		model.addAttribute("totalcustomer1", totalCustomer);
		Integer totalQuantity=customerDetailServiceImpl.sumOfQuantity(sellDate);
		if(totalQuantity==null)
		{
			totalQuantity=0;
			model.addAttribute("totalquantity1", totalQuantity);
		}
		else
		{
			model.addAttribute("totalquantity1", totalQuantity);
		}
		Double totalAmount=customerDetailServiceImpl.sumOfAmount(sellDate);
		if(totalAmount==null)
		{
			totalAmount=(double) 0;
			model.addAttribute("totalamount1", totalAmount);
		}
		else
		{
		model.addAttribute("totalamount1", totalAmount);
		}
		int totalProduct=customerDetailServiceImpl.getTotalProduct(sellDate);
		model.addAttribute("totalproduct1", totalProduct);
		return new ModelAndView("customerList.jsp", "buyDate", customerView);
	}
	
	@GetMapping("/getCustomerEditForm")
	public ModelAndView getCustomerEditForm(@RequestParam long customerId, @RequestParam long productId)
	{
		System.out.println(productId);
        CustomerView customerView=customerDetailServiceImpl.getCustomerById(customerId, productId);
		return new ModelAndView("customerpersonalEdit.jsp","customerview",customerView);
	}
	
	@GetMapping("/getCustomerProductEditForm")
	public ModelAndView getCustomerProductEditForm(@RequestParam long customerId, @RequestParam long productId)
	{
		CustomerView customerProduct=customerDetailServiceImpl.getCustomerById(customerId, productId);
//		Map<String, Object> map=new HashMap<>();
//		map.put("customerId", customerProduct.getCustomerId());
//		map.put("productId", customerProduct.getProductId());
//		map.put("quantity", customerProduct.getQuantity());
//		map.put("amount", customerProduct.getAmount());
//		map.put("buyDate", customerProduct.getBuyDate());
//		System.out.println(map);
//		customerDetailServiceImpl.insertIntoCustomerProductTemp(map);
		return new ModelAndView("customerproductEdit.jsp","customerproduct",customerProduct);
	}
	
	@PostMapping("/updatecustomerPersonalData")
	public String updateForm(@ModelAttribute Customer customer)
	{
		if(customer!=null)
		{
			customerDetailServiceImpl.updateIntoPersonalCustomer(customer);
		}
		return "customerpersonalEdit.jsp";
	}
	
	@PostMapping("/updateCustomerProduct")
	public String updatecustomerForm(@RequestParam double amount,@RequestParam long customerPurchaseId ,@RequestParam int productId, @RequestParam int quantity, @RequestParam long customerId)
	{

//	    Product product=productDetailService.getQuantityById(productId);
	    Product product=productDetailService.getProductById(productId);
        CustomerView customerView=customerDetailServiceImpl.getDataByCustomerId(customerId, customerPurchaseId);
//	    int dbquantity=product.getQuantity();
////		customerProduct=productDetailService.getQuantityByCustomerid(customerId, productId);
//
//	    int currentvalue=customerView.getQuantity();
//	    if(currentvalue==quantity)
//	    {
//	    	System.out.println("The given quantity are equal");
//	    }
//	    else if(currentvalue>quantity)
//	    {
//	    	currentvalue=currentvalue-quantity;
//	    	dbquantity=dbquantity+currentvalue;
//	    	System.out.println(dbquantity);
//	    	product.setQuantity(dbquantity);
//	    	if(dbquantity>=0)
//	    	{
//	    		productDetailService.updateIntoProduct(product);
//	    }
//	    	else
//	    	{
//	    		System.out.println("Sorry your data is illegel");
//	    	}
//	    }
//	    else if(currentvalue<quantity)
//	    {
//	    	currentvalue=quantity-currentvalue;
//	    	dbquantity=dbquantity-currentvalue;
//	    	product.setQuantity(dbquantity);
//	    	if(dbquantity>=0)
//	    	{
//	    		productDetailService.updateIntoProduct(product);
//	    }
//	    	else
//	    	{
//	    		System.out.println("sorry your data is illegel");
//	    	}
//
//	    }
		Integer currentQuantity=customerDetailServiceImpl.updateCustomerProductQuantity(customerPurchaseId, productId, quantity, customerId);
	     System.out.println(currentQuantity);
	    if(customerView!=null)
		{
			customerView.setQuantity(quantity);
			Map map1=customerDetailServiceImpl.calculationOfAmount(productId, quantity);
			customerView.setAmount((Double) map1.get("amount"));
			if(currentQuantity!=quantity)
			{
				Map<String, Object> map=new HashMap<>();
				map.put("productName", product.getProductName());
				map.put("currentAmount", amount);
				map.put("newAmount", customerView.getAmount());
				map.put("email", customerView.getEmail());
				map.put("currentQuantity", currentQuantity);
				map.put("quantity", quantity);
				notificationDetailService.updateProductNotification(map);
			}
		customerDetailServiceImpl.updateIntoCustomerProduct(customerView);
		}
		return "customerproductEdit.jsp";
	}
	
	@GetMapping("/getCustomerProductForm")
	public ModelAndView getCustomerForm(Model model,@RequestParam long customerId, @RequestParam long productId)
	{
//		List<PurchaseProduct> product=productDetailService.getAllProductInfo();
		List<Integer> product=customerDetailServiceImpl.getProductIdNotEqualToCustomerId(customerId);
	     model.addAttribute("product", product);
		CustomerView customerView=customerDetailServiceImpl.getCustomerById(customerId, productId);
		return new ModelAndView("customerproductadd.jsp","customer",customerView);
	}

	@GetMapping("/getNewCustomerProductForm")
	public String getNewCustomerProductForm(Model model)
	{
		List<PurchaseProduct> product=productDetailService.getAllProductInfo();
		model.addAttribute("product1", product);
		return "newcustomerproductadd.jsp";
	}

//	@GetMapping("/getChart")
//	public String getChart()
//	{
//		return "barGraph.jsp";
//	}
	
//	@GetMapping("/processData")
//	public ModelAndView getChart(@RequestParam(value="sell_date[]") String sell_date[])
//	{
//		List<CustomerView> customerView=customerDetailServiceImpl.getCustomerBuyDate(sell_date);
//		return new ModelAndView("barGraph.jsp", "customerView", customerView);
//	}
	
	
	
	@PostMapping("/saveCustomerProduct")
	public String saveCustomerProduct(@ModelAttribute CustomerProduct customerProduct, @RequestParam int productId, @RequestParam int quantity) throws MailException
	{
		Product product=productDetailService.getProductById(productId);
//		int dbquantity=product.getQuantity();
//		dbquantity=dbquantity-quantity;
//		product.setQuantity(dbquantity);
		product=customerDetailServiceImpl.substractCustomerProductQuantity(product, quantity);
		if(customerProduct!=null & product.getQuantity()>=0)
		{
			Map map=userDetailServiceImpl.getUserTempData();
			customerProduct.setUsername((String) map.get("username"));
			Map map1=customerDetailServiceImpl.calculationOfAmount(productId, quantity);
			customerProduct.setAmount((Double) map1.get("amount"));
		customerDetailServiceImpl.insertintocustomerproduct(customerProduct);
		productDetailService.updateIntoProduct(product);
        notificationDetailService.sendProductNotification(customerProduct);
		}
		else
		{
			System.out.println("Sorry your data is illegel");
		}
		return "redirect:/customerList.jsp";
	}

	@PostMapping("/saveNewCustomerProduct")
	public String saveNewCustomerProduct(@ModelAttribute CustomerProduct customerProduct, @RequestParam int productId, @RequestParam int quantity) throws MailException
	{
		Product product=productDetailService.getProductById(productId);
//		System.out.println(product.getQuantity());
//		int dbquantity=product.getQuantity();
//		System.out.println(dbquantity);
//		dbquantity=dbquantity-quantity;
//		product.setQuantity(dbquantity);
	  	product=customerDetailServiceImpl.substractCustomerProductQuantity(product, quantity);
		if(customerProduct!=null & product.getQuantity()>=0)
		{
			Map map=userDetailServiceImpl.getUserTempData();
			System.out.println(map.get("username"));
			customerProduct.setUsername((String) map.get("username"));
			Map map1=customerDetailServiceImpl.calculationOfAmount(productId, quantity);
			customerProduct.setAmount((Double) map1.get("amount"));
			customerDetailServiceImpl.insertintocustomerproduct(customerProduct);
			productDetailService.updateIntoProduct(product);
			notificationDetailService.sendProductNotification(customerProduct);
		}
		else
		{
			System.out.println("Sorry your data is illegel");
		}
		return "redirect:/customerList.jsp";
	}

	
	@PostMapping("/saveRemainingCustomerProduct")
	public String addSupplierProduct(@ModelAttribute CustomerProduct customerproduct, @RequestParam int productId, @RequestParam int quantity)
	{
		if(customerproduct!=null)
		{
			customerDetailServiceImpl.insertintocustomerproduct(customerproduct);
		}
		Product product=productDetailService.getProductById(productId);
		System.out.println(product.getQuantity());
		int dbquantity=product.getQuantity();
		dbquantity=dbquantity-quantity;
		product.setQuantity(dbquantity);
		if(dbquantity!=-1)
		{
			productDetailService.updateIntoProduct(product);
		}
		return "customerList.jsp";
	}
	
//	@GetMapping("/getNewCustomerProductForm")
//	public String getNewCustomerProductForm()
//	{
//		return "redirect:/customerproduct.jsp";
//	}
	
	
	@GetMapping("/list-Customer")
	public ModelAndView getAllCustomerInfo(Model model)
	{
		List<CustomerView> customerView=customerDetailServiceImpl.getAllCustomerInfo();
		
		int customerId=customerDetailServiceImpl.getTotalCustomer();
		model.addAttribute("customerId", customerId);
		int quantity=customerDetailServiceImpl.sumOfQuantity();
		model.addAttribute("quantity", quantity);
		double amount=customerDetailServiceImpl.sumOfAmount();
		model.addAttribute("amount", amount);
		int productId=customerDetailServiceImpl.getTotalProduct();
		model.addAttribute("productId", productId);
		return new ModelAndView("customerList.jsp","customers",customerView);
	}
	
	@PostMapping("/saveCustomer")
	public String insertCustomer(HttpServletRequest request, @ModelAttribute Customer customer)
	{
		if(customer!=null)
		{
			customerDetailServiceImpl.insertintocustomer(customer);
	        System.out.println(customer.getCustomerId());
	        request.setAttribute("customer_customer_id", customer.getCustomerId());
		}
		return "customerproduct.jsp";
	}

	@GetMapping("/deleteCustomer")
	public String deleteCustomer(@RequestParam long customerId,@RequestParam long productId)
	{
		customerDetailServiceImpl.deleteIntoCustomerView(customerId, productId);
		customerDetailServiceImpl.deleteIntoCustomer(customerId, productId);
		return "customerList.jsp";
	}
	
	@GetMapping(value="/createPdf")
	public void downloadPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<CustomerView> customers=customerDetailServiceImpl.getAllCustomerInfo();
		System.out.println("data");
		boolean isflag=customerDetailServiceImpl.createPdfForCustomers(customers, context, request, response);
        if(isflag)
        {
        	String fullpath=request.getServletContext().getRealPath("/resources/reports/"+"customers"+".pdf");
            filedownload(fullpath, response, "customers.pdf");      
        }
	}

	private void filedownload(String fullpath, HttpServletResponse response, String filename) 
	{
		// TODO Auto-generated method stub
		File file=new File(fullpath);
		final int BUFFER_SIZE=4096;
		if(file.exists())
		{
			try {
				FileInputStream inputstream=new FileInputStream(file);
				String mimeType=context.getMimeType(fullpath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachement; filename="+filename);
				OutputStream outputStream=response.getOutputStream();
				byte[] buffer=new byte[BUFFER_SIZE];
				int bytesread=-1;
				while((bytesread=inputstream.read(buffer))!=-1)
	{
		  outputStream.write(buffer,0, bytesread);
	}
				inputstream.close();
				outputStream.close();
				file.delete();				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
		
	}
	
	@GetMapping(value="/createExcel")
	public void createExcel(HttpServletRequest request, HttpServletResponse response)
	{
		List<CustomerView> customerView=customerDetailServiceImpl.getAllCustomerInfo();
		boolean isflag=customerDetailServiceImpl.createExcelForCustomers(customerView, context, request, response);
		if(isflag)
		{
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"customers" +".xls");
			filedownload(fullPath, response, "customers.xls");
		}
		
	}
	
}
