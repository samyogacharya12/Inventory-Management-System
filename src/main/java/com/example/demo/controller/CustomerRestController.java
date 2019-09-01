package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.model.*;
import com.example.demo.service.NotificationDetailServiceImpl;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class CustomerRestController {

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


	@GetMapping(value="/getCustomerByName/{customerName}")
	public List<Map> getCustomerByName(@Valid @PathVariable String customerName)
	{
		List<Map> maps=new ArrayList<>();
		List<CustomerView> findCustomers=customerDetailServiceImpl.getCustomerByName(customerName);
		int totalCustomer=customerDetailServiceImpl.getTotalCustomer(customerName);
		int totalQuantity=customerDetailServiceImpl.sumOfQuantity(customerName);
		double totalAmount=customerDetailServiceImpl.sumOfAmount(customerName);
		int totalProduct=customerDetailServiceImpl.getTotalProduct(customerName);
		Map<String, Object> map=new HashMap<>();
		map.put("findCustomers", findCustomers);
		map.put("totalCustomer", totalCustomer);
		map.put("totalQuantity",totalQuantity);
		map.put("totalAmount", totalAmount);
		map.put("totalProduct", totalProduct);
		maps.add(map);
		return maps;
	}

	@GetMapping(value = "/getCustomerDataByDate/{sellStartDate}/{sellLastDate}")
	public List<Map> getCustomerDataByDate(@PathVariable String sellStartDate, @PathVariable String sellLastDate)
	{
		List<Map> mapList=new ArrayList<>();
		Map<String, Object> map=new HashMap();
		List<CustomerView> customerProductByDate=customerDetailServiceImpl.getCustomerBuyDate(sellStartDate, sellLastDate);
		Integer numberOfCustomer=customerDetailServiceImpl.getTotalCustomer(sellStartDate, sellLastDate);
		Integer totalQuantity=customerDetailServiceImpl.sumOfQuantity(sellStartDate, sellLastDate);
		if(totalQuantity==null)
		{
			totalQuantity=0;
			map.put("totalQuantity",totalQuantity);
		}
		Double totalAmount=customerDetailServiceImpl.sumOfAmount(sellStartDate, sellLastDate);
		if(totalAmount==null)
		{
			totalAmount=(double) 0;
			map.put("totalAmount", totalAmount);
		}
		Integer numberOfProduct=customerDetailServiceImpl.getTotalProduct(sellStartDate, sellLastDate);
		map.put("numberOfProduct", numberOfProduct);
		map.put("customerProductByDate", customerProductByDate);
		map.put("numberOfCustomer", numberOfCustomer);
		map.put("totalQuantity", totalQuantity);
		map.put("totalAmount", totalAmount);
		mapList.add(map);
		return mapList;
	}




	@PostMapping(value="/updateCustomerPersonalData")
	public ResponseEntity updateForm(@Valid @RequestBody Customer customer)
	{
		System.out.println(customer.getCustomerId());
		customerDetailServiceImpl.updateIntoPersonalCustomer(customer);
		ServiceResponse serviceResponse=new ServiceResponse("success", customer);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@PostMapping(value = "/updateCustomerProduct")
	public ResponseEntity updateCustomerProduct(@Valid @RequestBody CustomerProduct customerProduct)
	{
		Product product=productDetailService.getProductById(customerProduct.getProductId());
        CustomerView customerView=customerDetailServiceImpl.getDataByCustomerId(customerProduct.getCustomerId(), customerProduct.getCustomerPurchaseId());
		Integer currentQuantity=customerDetailServiceImpl.updateCustomerProductQuantity(customerProduct.getCustomerPurchaseId(), customerProduct.getProductId(), customerProduct.getQuantity(), customerProduct.getCustomerId());
	    if(customerView!=null)
		{
			customerView.setQuantity(customerProduct.getQuantity());
			Map map1=customerDetailServiceImpl.calculationOfAmount(customerProduct.getProductId(), customerProduct.getQuantity());
			customerView.setAmount((Double) map1.get("amount"));
			if(currentQuantity!=customerProduct.getQuantity())
			{
				Map<String, Object> map=new HashMap<>();
				map.put("productName", product.getProductName());
				map.put("currentAmount", customerProduct.getAmount());
				map.put("newAmount", customerView.getAmount());
				map.put("email", customerView.getEmail());
				map.put("currentQuantity", currentQuantity);
				map.put("quantity", customerProduct.getQuantity());
				notificationDetailService.updateProductNotification(map);
			}
		customerDetailServiceImpl.updateIntoCustomerProduct(customerView);
		}
	    ServiceResponse serviceResponse=new ServiceResponse("success", customerProduct);
	    return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	@GetMapping(value="/getCustomerPersonalEditForm/{customerId}/{productId}")
	 public ResponseEntity<CustomerView> getCustomerPersonalEditFOrm(@PathVariable long customerId, @PathVariable long productId)
	 {

	 	CustomerView customerView=customerDetailServiceImpl.getCustomerById(customerId, productId);
	 	ServiceResponse serviceResponse=new ServiceResponse("success", customerView);
	 	return new ResponseEntity(serviceResponse, HttpStatus.OK);
	 }


	@GetMapping(value="/getCustomerProductEditForm/{customerId}/{productId}")
    public ResponseEntity<CustomerView> getCustomerProductEditForm(@PathVariable long customerId,@PathVariable long productId)
	{
		CustomerView customerView=customerDetailServiceImpl.getCustomerById(customerId, productId);
		ServiceResponse serviceResponse=new ServiceResponse("success", customerView);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);

	}
	


	@PostMapping(value = "/saveCustomerProduct")
   public ResponseEntity saveCustomerProduct(@Valid @RequestBody CustomerProduct customerProduct)
   {
	   Product product=productDetailService.getProductById(customerProduct.getProductId());
		product=customerDetailServiceImpl.substractCustomerProductQuantity(product, customerProduct.getQuantity());
		if(customerProduct!=null & product.getQuantity()>=0)
		{
			Date logoutTime=new Date(System.currentTimeMillis());
			List<LogRecord> logRecordList=userDetailServiceImpl.getListLogRecord(logoutTime);
			for(LogRecord logRecord: logRecordList)
			{
				customerProduct.setUsername(logRecord.getUserName());
			}
			Map map1=customerDetailServiceImpl.calculationOfAmount(customerProduct.getProductId(), customerProduct.getQuantity());
			customerProduct.setAmount((Double) map1.get("amount"));
		customerDetailServiceImpl.insertintocustomerproduct(customerProduct);
		productDetailService.updateIntoProduct(product);
        notificationDetailService.sendProductNotification(customerProduct);
		}
		else
		{
			System.out.println("Sorry your data is illegel");
		}
       ServiceResponse serviceResponse=new ServiceResponse("success", customerProduct);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }



	@GetMapping(value="/getNewCustomerProductForm")
	public List<PurchaseProduct> getNewCustomerProductForm()
	{
		List<PurchaseProduct> purchaseProducts=productDetailService.getAllProductInfo();
		return purchaseProducts;
	}


	@GetMapping(value = "/getExistingCustomerProductForm/{customerId}/{productId}")
	public List<Map> getExistingCustomerProductForm(@PathVariable long customerId, @PathVariable long productId)
	{
		List<Map> mapList=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		List<Integer> product=customerDetailServiceImpl.getProductIdNotEqualToCustomerId(customerId);
		CustomerView customerView=customerDetailServiceImpl.getCustomerById(customerId, productId);
		map.put("product", product);
		map.put("customerView", customerView);
		mapList.add(map);
		return mapList;
	}





	@PostMapping("/saveNewCustomerProduct")
	public  ResponseEntity saveNewCustomerProduct(@Valid @RequestBody CustomerProduct customerProduct) throws MailException
	{
		Product product=productDetailService.getProductById(customerProduct.getProductId());
		product=customerDetailServiceImpl.substractCustomerProductQuantity(product, customerProduct.getQuantity());
		if( product.getQuantity()>=0)
		{
			Date logoutTime=new Date(System.currentTimeMillis());
			List<LogRecord> logRecords=userDetailServiceImpl.getListLogRecord(logoutTime);
			for(LogRecord logRecord: logRecords)
			{
				customerProduct.setUsername(logRecord.getUserName());
			}
			System.out.println(customerProduct.getCustomerId());
			System.out.println(customerProduct.getQuantity());
			System.out.println(customerProduct.getBuyDate());
			System.out.println(customerProduct.getUsername());
			Map map1=customerDetailServiceImpl.calculationOfAmount(customerProduct.getProductId(), customerProduct.getQuantity());
			customerProduct.setAmount((Double) map1.get("amount"));
			customerDetailServiceImpl.insertintocustomerproduct(customerProduct);
			productDetailService.updateIntoProduct(product);
			notificationDetailService.sendProductNotification(customerProduct);
		}
		else
		{
			System.out.println("Sorry your data is illegel");
		}

		ServiceResponse serviceResponse=new ServiceResponse("success", customerProduct);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
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



	@GetMapping(value = "/getListCustomer")
	public List<Map> getAllCustomerInfo()
	{
		List<Map> maps=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		List<CustomerView> customerView=customerDetailServiceImpl.getAllCustomerInfo();
		int sumOfQuantity=customerDetailServiceImpl.sumOfQuantity();
		double sumOfAmount=customerDetailServiceImpl.sumOfAmount();
		int countNoOfCustomer=customerDetailServiceImpl.getTotalCustomer();
		int countNoOfProduct=customerDetailServiceImpl.getTotalProduct();
		map.put("customerView", customerView);
		map.put("countNoOfCustomer", countNoOfCustomer);
		map.put("sumOfQuantity", sumOfQuantity);
		map.put("sumOfAmount", sumOfAmount);
		map.put("countNoOfProduct", countNoOfProduct);
		maps.add(map);
		return maps;
	}

	@PostMapping(value = "/save-customer")
   public ResponseEntity insertCustomer(@Valid @RequestBody  Customer customer)
   {
	   customerDetailServiceImpl.insertintocustomer(customer);
   	   ServiceResponse serviceResponse=new ServiceResponse("success", customer);
   	   return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }


	@GetMapping(value="/deleteCustomer/{customerId}/{productId}")
	 public ResponseEntity deleteCustomer(@PathVariable long customerId, @PathVariable long productId)
	 {
	 	customerDetailServiceImpl.deleteIntoCustomerView(customerId, productId);
	 	customerDetailServiceImpl.deleteIntoCustomer(customerId, productId);
	 	ServiceResponse serviceResponse=new ServiceResponse("success", customerId);
	 	return new ResponseEntity(serviceResponse, HttpStatus.OK);
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
