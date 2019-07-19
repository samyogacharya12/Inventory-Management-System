package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;
import com.example.demo.model.Product;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
public class CustomerController {

	@Autowired
	private CustomerDetailServiceImpl customerDetailServiceImpl;
	
	@Autowired
	private UserDetailServiceImpl userDetailServiceImpl;
	
	@Autowired
	private ServletContext context;
	@GetMapping("/customer_form")
	public String getCustomerForm()
	{
		return "customer.jsp";
	}
	
	@GetMapping("/getCustomerByName")
	public ModelAndView getCustomerByName(@RequestParam String customer_name, Model model)
	{
		List<Customer_View> customer_view=customerDetailServiceImpl.getCustomerByName(customer_name);
		int totalcustomer=customerDetailServiceImpl.getTotalCustomer(customer_name);
		model.addAttribute("totalcustomer", totalcustomer);
		int totalquantity=customerDetailServiceImpl.sumOfQuantity(customer_name);
		model.addAttribute("totalquantity", totalquantity);
		double totalamount=customerDetailServiceImpl.sumOfAmount(customer_name);
		model.addAttribute("totalamount", totalamount);
		int totalproduct=customerDetailServiceImpl.getTotalProduct(customer_name);
		model.addAttribute("totalproduct", totalproduct);
		return new ModelAndView("customerList.jsp", "customerview", customer_view);
	}
	@GetMapping("/getcustomerDate")
	public ModelAndView getCustomerByDate(@RequestParam(value="buy_date[]") String buy_date[], Model model)
	{
		List<Customer_View> customer_view=customerDetailServiceImpl.getCustomerBuyDate(buy_date);
		int totalcustomer=customerDetailServiceImpl.getTotalProduct(buy_date);
		model.addAttribute("totalcustomer1", totalcustomer);
		Integer totalquantity=customerDetailServiceImpl.sumOfQuantity(buy_date);
		if(totalquantity==null)
		{
			totalquantity=0;
			model.addAttribute("totalquantity1", totalquantity);
		}
		else
		{
			model.addAttribute("totalquantity1", totalquantity);
		}
		Double totalamount=customerDetailServiceImpl.sumOfAmount(buy_date);
		if(totalamount==null)
		{
			totalamount=(double) 0;
			model.addAttribute("totalamount1", totalamount);
		}
		else
		{
		model.addAttribute("totalamount1", totalamount);
		}
		int totalproduct=customerDetailServiceImpl.getTotalProduct(buy_date);
		model.addAttribute("totalproduct1", totalproduct);
		return new ModelAndView("customerList.jsp", "buy_date", customer_view);
	}
	
	@GetMapping("/customer")
	public ModelAndView getCustomerEditForm(@RequestParam long customer_id, @RequestParam long product_id)
	{
		System.out.println(product_id);
        Customer_View customer_view=customerDetailServiceImpl.getCustomerById(customer_id, product_id);
		return new ModelAndView("customerpersonalEdit.jsp","customerview",customer_view);
	}
	
	@GetMapping("/customerproduct")
	public ModelAndView getCustomerProductEditForm(@RequestParam long customer_id, @RequestParam long product_id)
	{
		Customer_View customer_product=customerDetailServiceImpl.getCustomerById(customer_id, product_id);
		return new ModelAndView("customerproductEdit.jsp","customerproduct",customer_product);
	}
	
	@PostMapping("/update_customerPersonal")
	public String updateForm(@ModelAttribute Customer customer)
	{
		if(customer!=null)
		{
			customerDetailServiceImpl.updateintopersonalcustomer(customer);
		}
		return "customerpersonalEdit.jsp";
	}
	
	@PostMapping("/update_customerproduct")
	public String updatecustomerForm(@ModelAttribute Customer_Product customerproduct, @RequestParam long product_product_id, @RequestParam int quantity, @RequestParam long customer_customer_id)
	{
	    Product product=userDetailServiceImpl.getquantitybyid(product_product_id);
	    int dbquantity=product.getQuantity();
	    customerproduct=userDetailServiceImpl.getquantitybycustomerid(customer_customer_id, product_product_id);
	    int currentvalue=customerproduct.getQuantity();
	    if(currentvalue==quantity)
	    {
	    	System.out.println("The given quantity are equal");
	    }
	    else if(currentvalue>quantity)
	    {
	    	currentvalue=currentvalue-quantity;
	    	dbquantity=dbquantity+currentvalue;
	    	System.out.println(dbquantity);
	    	product.setQuantity(dbquantity);
	    	if(dbquantity>=0)
	    	{
	    	userDetailServiceImpl.updateintoproduct(product);
	    }
	    	else
	    	{
	    		System.out.println("Sorry your data is illegel");
	    	}
	    }
	    else if(currentvalue<quantity)
	    {
	    	currentvalue=quantity-currentvalue;
	    	dbquantity=dbquantity-currentvalue;
	    	product.setQuantity(dbquantity);
	    	if(dbquantity>=0)
	    	{
	    	userDetailServiceImpl.updateintoproduct(product);
	    }
	    	else
	    	{
	    		System.out.println("sorry your data is illegel");
	    	}
	    	
	    }
	    if(customerproduct!=null)
		{
	    	customerproduct.setQuantity(quantity);
		customerDetailServiceImpl.updateintocustomerproduct(customerproduct);
		}
		return "customerproductEdit.jsp";
	}
	
	@GetMapping("/getCustomer")
	public ModelAndView getCustomerForm(@RequestParam long customer_id, @RequestParam long product_id)
	{
		System.out.println(customer_id);
		Customer_View customer_view=customerDetailServiceImpl.getCustomerById(customer_id, product_id);
		return new ModelAndView("customerproductadd.jsp","customer",customer_view);
	}
	
	
	@GetMapping("/getChart")
	public String getChart()
	{
		return "barGraph.jsp";
	}
	
	@GetMapping("/processData")
	public ModelAndView getChart(@RequestParam(value="sell_date[]") String sell_date[])
	{
		List<Customer_View> customerView=customerDetailServiceImpl.getCustomerBuyDate(sell_date);
		return new ModelAndView("barGraph.jsp", "customerView", customerView);
	}
	
	
	
	@PostMapping("/save_customerproduct")
	public String postSupplierProductForm(@ModelAttribute Customer_Product customerproduct, @RequestParam int product_product_id, @RequestParam int quantity)
	{
		Product product=userDetailServiceImpl.getquantitybyid(product_product_id);
		System.out.println(product.getQuantity());
		int dbquantity=product.getQuantity();
		System.out.println(dbquantity);
		dbquantity=dbquantity-quantity;
		product.setQuantity(dbquantity);
		if(customerproduct!=null & dbquantity>=0)
		{
		customerDetailServiceImpl.insertintocustomerproduct(customerproduct);
		userDetailServiceImpl.updateintoproduct(product);
		}
		else
		{
			System.out.println("Sorry your data is illegel");
		}
		return "redirect:/customerproduct.jsp";
	}
	
	@PostMapping("/saveCustomerProduct")
	public String addSupplierProductForm(@ModelAttribute Customer_Product customerproduct, @RequestParam int product_product_id, @RequestParam int quantity)
	{
		if(customerproduct!=null)
		{
			customerDetailServiceImpl.insertintocustomerproduct(customerproduct);
		}
		Product product=userDetailServiceImpl.getquantitybyid(product_product_id);
		System.out.println(product.getQuantity());
		int dbquantity=product.getQuantity();
		dbquantity=dbquantity-quantity;
		product.setQuantity(dbquantity);
		if(dbquantity!=-1)
		{
			userDetailServiceImpl.updateintoproduct(product);
		}
		return "customerList.jsp";
	}
	
	@GetMapping("/getCustomerForm")
	public String getCustomerProductForm()
	{
		return "redirect:/customerproduct.jsp";
	}
	
	
	@GetMapping("/list_Customer")
	public ModelAndView getAllCustomerInfo(Model model)
	{
		List<Customer_View> customerview=customerDetailServiceImpl.getAllCustomerInfo();
		
		int customer_id=customerDetailServiceImpl.getTotalCustomer();
		model.addAttribute("customer_id", customer_id);
		int quantity=customerDetailServiceImpl.sumOfQuantity();
		model.addAttribute("quantity", quantity);
		double amount=customerDetailServiceImpl.sumOfAmount();
		model.addAttribute("amount", amount);
		int product_id=customerDetailServiceImpl.getTotalProduct();
		model.addAttribute("product_id", product_id);
		return new ModelAndView("customerList.jsp","customers",customerview);
	}
	
	@PostMapping("/save_customer")
	public String insertcustomer(HttpServletRequest request, @ModelAttribute Customer customer)
	{
		if(customer!=null)
		{
			customerDetailServiceImpl.insertintocustomer(customer);
	        System.out.println(customer.getCustomer_id());
	        request.setAttribute("customer_customer_id", customer.getCustomer_id());
		}
		return "customerproduct.jsp";
	}

	@GetMapping("/deletecustomer")
	public String deleteSupplier(@RequestParam long customer_id,@RequestParam long product_id)
	{
		customerDetailServiceImpl.deleteintocustomerview(customer_id, product_id);
		customerDetailServiceImpl.deleteintocustomer(customer_id, product_id);
		return "customerList.jsp";
	}
	
	@GetMapping(value="/createPdf")
	public void downloadPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<Customer_View> customers=customerDetailServiceImpl.getAllCustomerInfo();
		System.out.println("data");
		boolean isflag=customerDetailServiceImpl.createPdf(customers, context, request, response);
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
		List<Customer_View> customerview=customerDetailServiceImpl.getAllCustomerInfo();
		boolean isflag=customerDetailServiceImpl.createExcel(customerview, context, request, response);
		if(isflag)
		{
			String fullPath=request.getServletContext().getRealPath("/resources/reports/"+"customers" +".xls");
			filedownload(fullPath, response, "customers.xls");
		}
		
	}
	
}
