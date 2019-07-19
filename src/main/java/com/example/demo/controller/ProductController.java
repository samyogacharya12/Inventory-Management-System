package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;
import com.example.demo.model.Product;
import com.example.demo.model.Supplier_View;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.TrashDetailServiceImpl;
import com.example.demo.service.UserDetailServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductController {

	String uploaded_file="C:/Users/DELL/Desktop/New Files/";
	@Autowired
	private UserDetailServiceImpl userDetailService;
	
	@Autowired
    private CustomerDetailServiceImpl customerDetailService;
	
	@Autowired
	private TrashDetailServiceImpl trashDetailService;
	
	@Autowired
	private ServletContext context;
	
	@PostMapping("/save_data")
	public String insertProduct(HttpServletRequest request,@ModelAttribute Product product, @RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath=writeImagetoFile(file);
		if(product!=null)
		{
			product.setImage(imageuploadpath);
			userDetailService.insertintorpoduct(product);
			request.setAttribute("product_product_id", userDetailService.getAllProductInfo());
		}
		return "product.jsp";
	}
	@GetMapping("/trashproduct")
	public String moveToTrash(@RequestParam long product_id)
	{
		Product product=userDetailService.getproductbyId(product_id);
		product.setIs_expired("true");
		userDetailService.updateExpiredProduct(product);
//		userDetailService.deleteproduct(product_id);
		trashDetailService.insertintotrash(product);
		return "redirect:/productList.jsp";
	}
	

	@GetMapping("/addproduct")
	public String customer()
	{
		return "product.jsp";
	}
	
	
	@GetMapping(value="/product")
	public ModelAndView getProductEditForm(@RequestParam long product_id,Model model)
	{
		Product product=userDetailService.getproductbyId(product_id);
	    Date date=product.getMagnifacture_date();
	    DateFormat dateFormat=new SimpleDateFormat("MM/dd/yyyy");
	    String date1=dateFormat.format(date);
	    model.addAttribute("magnifacture_date", date1);
	    System.out.println(date1);
		return new ModelAndView("productEdit.jsp", "productEdit", product);
	}
	
	@PostMapping("/update_product")
	public String updateproduct(@ModelAttribute Product product,@RequestParam MultipartFile file ) throws IOException
	{
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
		{
			Product prod=userDetailService.getproductbyId(product.getProduct_id());
			System.out.println(product.getMagnifacture_date());
			System.out.println(product.getExpiry_date());
			imageuploadpath=prod.getImage();
		}
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		if(product!=null)
		{
			product.setImage(imageuploadpath);
			userDetailService.updateintoproduct(product);
		}
		
		return "redirect:/productEdit.jsp";
	}
	
	@GetMapping("/list_product")
	public ModelAndView List_Product(Model model)
	{
	  List<Product> product=userDetailService.getAllProductInfo();
	  int totalquantity=userDetailService.getTotalNoOfQuantity();
	  int totalnoproduct=userDetailService.getTotalNoOfProduct();
	  double totalnoprice=userDetailService.getSumOfPrice();
	  model.addAttribute("quantity", totalquantity);
	  model.addAttribute("totalproduct", totalnoproduct);
	  model.addAttribute("price", totalnoprice);
	  return new ModelAndView("productList.jsp", "product", product);
	}
	
	
	@GetMapping("/getProductByName")
	public ModelAndView get_product(Model model,@RequestParam String product_name) throws IOException
	{
		System.out.println(product_name);
		List<Product> findproduct=userDetailService.findByProductName(product_name);
		int totalquantity=userDetailService.getTotalNoOfQuantity(product_name);
		System.out.println(totalquantity);
		model.addAttribute("totalquantity1", totalquantity);
		int totalproduct=userDetailService.getTotalNoOfProduct(product_name);
		System.out.println(totalproduct);
		model.addAttribute("totalproduct1", totalproduct);
		double totalnoprice=userDetailService.getSumOfPrice(product_name);
		model.addAttribute("price1", totalnoprice);
		return new ModelAndView("productList.jsp", "producter", findproduct);
	}
	
	@GetMapping(value="/deleteproduct")
	public String deleteProduct(@RequestParam long product_id)
	{
		userDetailService.deleteproduct(product_id);
		return "redirect:/productList.jsp";
	}
	
	
	@GetMapping(value="/viewData")
	public String viewData(@RequestParam(value="sell_date[]") String[] sell_date, Model model) throws JsonProcessingException
	{
		List<Customer_View> customerView=customerDetailService.getCustomerBuyDate(sell_date);
		ObjectMapper objectMapper=new ObjectMapper();
		model.addAttribute("sales", objectMapper.writeValueAsString(customerView));
		model.addAttribute("date", sell_date);
		return "redirect:/chart.jsp";
	}
 
	@GetMapping(value="/createExcelProduct")
	public void createExcel(HttpServletRequest request, HttpServletResponse response)
	{
		List<Product> products=userDetailService.getAllProductInfo();
		boolean isflag=userDetailService.createExcel(products, context, request, response);
		if(isflag)
		{
			String filepath=request.getServletContext().getRealPath("/resources/reports/"+"products"+".xls");
			filedownload(filepath, response, "products.xls");
		}
	}
	

	@GetMapping(value="/createPdfProduct")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<Product> products=userDetailService.getAllProductInfo();
		boolean isflag=userDetailService.createPdf(products, context,request, response);
		if(isflag)
		{
			String fullpath=request.getServletContext().getRealPath("/resources/reports/"+"products"+".pdf");
            filedownload(fullpath, response, "products.pdf");      
		}
	}
	
	

	private void filedownload(String fullpath, HttpServletResponse response, String filename) {
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

	public String writeImagetoFile(MultipartFile file) throws IOException
	{
		String imageuploadPath=uploaded_file +file.getOriginalFilename();
		byte[] bytes=file.getBytes();
		try {
			BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(imageuploadPath));
			bout.write(bytes);
			bout.flush();
			bout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		return imageuploadPath;
	}
	
}
