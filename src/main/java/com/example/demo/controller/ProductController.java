package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class ProductController {

	String uploaded_file="C:/Users/DELL/Desktop/New Files/";


	@Autowired
	private ProductDetailServiceImpl productDetailService;

	@Autowired
    private CustomerDetailServiceImpl customerDetailService;
	
	@Autowired
	private TrashDetailServiceImpl trashDetailService;

	@Autowired
	private PurchaseDetailServiceImpl purchaseDetailService;

	@Autowired
	private UserDetailServiceImpl userDetailService;


	private UserDetailsService userDetailsService;

	@Autowired
	private ServletContext context;


	@GetMapping("/addProduct")
	public String getProductForm()
	{
		return "AddProduct.jsp";
	}

	
	@PostMapping("/save-product")
	public String insertProduct(HttpServletRequest request ,@ModelAttribute Product product, @RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath=writeImagetoFile(file);
		if(product!=null)
		{
//			Date[] purchaseDate= {new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())};
			Date purchaseDate=new Date((System.currentTimeMillis()));
			product.setImage(imageuploadpath);
			productDetailService.insertIntoProduct(product);
			Map userTempData=userDetailService.getUserTempData();
			Projectuser projectuser=userDetailService.findByUsername((String) userTempData.get("username"));
//			productDetailService.calculateDiffferenceInProductPrice(product.getProductId(),product.getProductName(), product.getProductType(), product.getPrice());
			Map<String, Object> map=new HashMap<>();
			map.put("userId", projectuser.getUserId());
			map.put("username", projectuser.getUsername());
			map.put("productId", product.getProductId());
			purchaseDetailService.insertIntoPurchase(map);
			Integer count=productDetailService.countProductByNameAndType(product.getProductName(), product.getProductType());
			System.out.println(count);
			for(int i=0;i<=count;i++) {
				List<PurchaseProduct> purchaseProducts = productDetailService.getProductByNameTypeAndPurchaseDate(purchaseDate);
				List<PurchaseProduct> productList = productDetailService.getProductByNameTypeLessThanPurchaseDate(product.getProductName(), product.getProductType(), purchaseDate);
				for (PurchaseProduct purchaseProduct : purchaseProducts) {
					for (PurchaseProduct productList1 : productList) {
						if (purchaseProduct.getProductId()!=productList1.getProductId()) {
							if (purchaseProduct.getPrice() >= productList1.getPrice()) {
								Double currentPrice = purchaseProduct.getPrice() - productList1.getPrice();
								Map<String, Object> map1 = new HashMap<>();
								map1.put("productId", purchaseProduct.getProductId());
								map1.put("pastPrice", productList1.getPrice());
								map1.put("presentPrice", purchaseProduct.getPrice());
								map1.put("priceIncreament", currentPrice);
								map1.put("priceDecreament", 0);
								map1.put("referenceProductId", productList1.getProductId());
								productDetailService.insertIntoProductAnalysis(map1);
							} else if (productList1.getPrice() == purchaseProduct.getPrice()) {
								System.out.println("The prices of a given product are equal");
							} else if (purchaseProduct.getPrice() <= productList1.getPrice()) {
								Double currentPrice = productList1.getPrice() - purchaseProduct.getPrice();
								Map<String, Object> map1 = new HashMap<>();
								map1.put("productId", purchaseProduct.getProductId());
								map1.put("pastPrice", productList1.getPrice());
								map1.put("presentPrice", purchaseProduct.getPrice());
								map1.put("priceIncreament", 0);
								map1.put("priceDecreament", currentPrice);
								map1.put("referenceProductId", productList1.getProductId());
								productDetailService.insertIntoProductAnalysis(map1);
							} else {
								System.out.println("The given data is empty");
							}
							purchaseDate = productList1.getPurchaseDate();
							System.out.println(purchaseDate);
						}
					}
				}
			}
			request.setAttribute("product_product_id", productDetailService.getAllProductInfo());
		}
		return "AddProduct.jsp";
	}

//	public void calculateDifferenceInProductPrice(String productName, String productType)
//	{
//		Date purchaseDate=new Date((System.currentTimeMillis()));
//        Integer count=productDetailService.countProductByNameAndType(productName, productType);
//        System.out.println(count);
//        System.out.println(productName);
//        System.out.println(productType);
//        for(int i=0;i<=count;i++) {
//			List<PurchaseProduct> purchaseProducts = productDetailService.getProductByNameTypeAndPurchaseDate(purchaseDate);
//			System.out.println(purchaseProducts);
//			List<PurchaseProduct> productList = productDetailService.getProductByNameTypeLessThanPurchaseDate(productName, productType, purchaseDate);
//			System.out.println(productList);
//			for (PurchaseProduct purchaseProduct : purchaseProducts) {
//				for (PurchaseProduct productList1 : productList) {
//					if (purchaseProduct.getPrice() >= productList1.getPrice()) {
//						Double currentPrice = purchaseProduct.getPrice() - productList1.getPrice();
//						Map<String, Object> map = new HashMap<>();
//						map.put("productId", purchaseProduct.getProductId());
//						map.put("pastPrice", productList1.getPrice());
//						map.put("presentPrice", purchaseProduct.getPrice());
//						map.put("priceIncreament", currentPrice);
//						map.put("priceDecreament", 0);
//						map.put("referenceProductId", productList1.getProductId());
//						System.out.println(currentPrice);
//						productDetailService.insertIntoProductAnalysis(map);
//					} else if (productList1.getPrice() == purchaseProduct.getPrice()) {
//						System.out.println("The prices of a given product are equal");
//					} else if (purchaseProduct.getPrice() <= productList1.getPrice()) {
//						Double currentPrice = productList1.getPrice() - purchaseProduct.getPrice();
//						Map<String, Object> map = new HashMap<>();
//						map.put("productId", purchaseProduct.getProductId());
//						map.put("pastPrice", productList1.getPrice());
//						map.put("presentPrice", purchaseProduct.getPrice());
//						map.put("priceIncreament", 0);
//						map.put("priceDecreament", currentPrice);
//						map.put("referenceProductId", productList1.getProductId());
//						productDetailService.insertIntoProductAnalysis(map);
//					} else {
//						System.out.println("The given data is empty");
//					}
//					purchaseDate = productList1.getPurchaseDate();
//
//				}
//			}
//		}
//	}

	@GetMapping("/addToTrash")
	public String moveToTrash(@RequestParam long productId)
	{
		Product product=productDetailService.getProductById(productId);
		product.setIsExpired("true");
		productDetailService.updateExpiredProduct(product);
		trashDetailService.insertIntoTrash(product);
		return "redirect:/productList.jsp";
	}
	

	
	
	@GetMapping(value="/getProductEditForm")
	public ModelAndView getProductEditForm(@RequestParam long productId,Model model)
	{
		Product product=productDetailService.getProductById(productId);
		return new ModelAndView("productEdit.jsp", "productEdit", product);
	}
	
	@PostMapping("/update-product")
	public String updateproduct(@ModelAttribute Product product,@RequestParam MultipartFile file ) throws IOException
	{
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
		{
			Product prod=productDetailService.getProductById(product.getProductId());
			System.out.println(product.getMagnifactureDate());
			System.out.println(product.getExpiryDate());
			imageuploadpath=prod.getImage();
		}
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		if(product!=null)
		{
			product.setImage(imageuploadpath);
			productDetailService.updateIntoProduct(product);
		}
		
		return "redirect:/productEdit.jsp";
	}
	
	@GetMapping("/list-product")
	public ModelAndView listProduct(Model model)
	{
	  List<PurchaseProduct> purchaseProducts=productDetailService.getAllProductInfo();
	  int totalquantity=productDetailService.getTotalNoOfQuantity();
	  int totalnoproduct=productDetailService.getTotalNoOfProduct();
	  double totalnoprice=productDetailService.getSumOfPrice();
	  model.addAttribute("quantity", totalquantity);
	  model.addAttribute("totalproduct", totalnoproduct);
	  model.addAttribute("price", totalnoprice);
	  return new ModelAndView("productList.jsp", "purchaseProducts", purchaseProducts);
	}
	
	
	@GetMapping("/getProductByName")
	public ModelAndView getProduct(Model model,@RequestParam String productName) throws IOException
	{
		System.out.println(productName);
		List<PurchaseProduct> findproduct=productDetailService.findByProductName(productName);
		int totalquantity=productDetailService.getTotalNoOfQuantity(productName);
		System.out.println(totalquantity);
		model.addAttribute("totalquantity1", totalquantity);
		int totalproduct=productDetailService.getTotalNoOfProduct(productName);
		System.out.println(totalproduct);
		model.addAttribute("totalproduct1", totalproduct);
		double totalnoprice=productDetailService.getSumOfPrice(productName);
		model.addAttribute("price1", totalnoprice);
		return new ModelAndView("productList.jsp", "producter", findproduct);
	}
	
	@GetMapping(value= "/delete-product")
	public String deleteProduct(@RequestParam long productId)
	{
		productDetailService.deleteproductinfo(productId);
		return "redirect:/productList.jsp";
	}
	
	
	@GetMapping(value="/viewData")
	public String viewData(@RequestParam(value="sellDate[]") String[] sellDate, Model model) throws JsonProcessingException
	{
		List<CustomerView> customerView=customerDetailService.getCustomerBuyDate(sellDate);
		ObjectMapper objectMapper=new ObjectMapper();
		model.addAttribute("sales", objectMapper.writeValueAsString(customerView));
		model.addAttribute("date", sellDate);
		return "redirect:/chart.jsp";
	}
 
	@GetMapping(value="/createExcelProduct")
	public void createExcel(HttpServletRequest request, HttpServletResponse response)
	{
		List<PurchaseProduct> products=productDetailService.getAllProductInfo();
		boolean isflag=productDetailService.createExcelForProducts(products, context, request, response);
		if(isflag)
		{
			String filepath=request.getServletContext().getRealPath("/resources/reports/"+"products"+".xls");
			filedownload(filepath, response, "products.xls");
		}
	}
	

	@GetMapping(value="/createPdfProduct")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<PurchaseProduct> products=productDetailService.getAllProductInfo();
		boolean isflag=productDetailService.createPdfForProducts(products, context,request, response);
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
