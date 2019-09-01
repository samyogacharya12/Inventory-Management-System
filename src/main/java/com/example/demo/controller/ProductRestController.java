package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import com.example.demo.model.*;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ProductRestController {

	String uploaded_file="C:/Users/DELL/Desktop/New Files/";


	@Autowired
	private ProductDetailServiceImpl productDetailService;

	
	@Autowired
	private TrashDetailServiceImpl trashDetailService;

	@Autowired
	private PurchaseDetailServiceImpl purchaseDetailService;

	@Autowired
	private UserDetailServiceImpl userDetailService;



	@Autowired
	private ServletContext context;



	
//	@PostMapping("/save-product")
//	public String insertProduct(HttpServletRequest request ,@ModelAttribute Product product, @RequestParam MultipartFile file) throws IOException
//	{
//		String imageuploadpath=writeImagetoFile(file);
//		if(product!=null)
//		{
//			Date purchaseDate=new Date((System.currentTimeMillis()));
//			product.setImage(imageuploadpath);
//			productDetailService.insertIntoProduct(product);
//			Map userTempData=userDetailService.getUserTempData();
//			Projectuser projectuser=userDetailService.findByUsername((String) userTempData.get("username"));
////			productDetailService.calculateDiffferenceInProductPrice(product.getProductId(),product.getProductName(), product.getProductType(), product.getPrice());
//			Map<String, Object> map=new HashMap<>();
//			map.put("userId", projectuser.getUserId());
//			map.put("username", projectuser.getUsername());
//			map.put("productId", product.getProductId());
//			purchaseDetailService.insertIntoPurchase(map);
//			Integer count=productDetailService.countProductByNameAndType(product.getProductName(), product.getProductType());
//			System.out.println(count);
//			for(int i=0;i<=count;i++) {
//				List<PurchaseProduct> purchaseProducts = productDetailService.getProductByNameTypeAndPurchaseDate(purchaseDate);
//				List<PurchaseProduct> productList = productDetailService.getProductByNameTypeLessThanPurchaseDate(product.getProductName(), product.getProductType(), purchaseDate);
//				for (PurchaseProduct purchaseProduct : purchaseProducts) {
//					for (PurchaseProduct productList1 : productList) {
//						if (purchaseProduct.getProductId()!=productList1.getProductId()) {
//							if (purchaseProduct.getPrice() >= productList1.getPrice()) {
//								Double currentPrice = purchaseProduct.getPrice() - productList1.getPrice();
//								Map<String, Object> map1 = new HashMap<>();
//								map1.put("productId", purchaseProduct.getProductId());
//								map1.put("pastPrice", productList1.getPrice());
//								map1.put("presentPrice", purchaseProduct.getPrice());
//								map1.put("priceIncreament", currentPrice);
//								map1.put("priceDecreament", 0);
//								map1.put("referenceProductId", productList1.getProductId());
//								productDetailService.insertIntoProductAnalysis(map1);
//							} else if (productList1.getPrice() == purchaseProduct.getPrice()) {
//								System.out.println("The prices of a given product are equal");
//							} else if (purchaseProduct.getPrice() <= productList1.getPrice()) {
//								Double currentPrice = productList1.getPrice() - purchaseProduct.getPrice();
//								Map<String, Object> map1 = new HashMap<>();
//								map1.put("productId", purchaseProduct.getProductId());
//								map1.put("pastPrice", productList1.getPrice());
//								map1.put("presentPrice", purchaseProduct.getPrice());
//								map1.put("priceIncreament", 0);
//								map1.put("priceDecreament", currentPrice);
//								map1.put("referenceProductId", productList1.getProductId());
//								productDetailService.insertIntoProductAnalysis(map1);
//							} else {
//								System.out.println("The given data is empty");
//							}
//							purchaseDate = productList1.getPurchaseDate();
//							System.out.println(purchaseDate);
//						}
//					}
//				}
//			}
//			request.setAttribute("product_product_id", productDetailService.getAllProductInfo());
//		}
//		return "AddProduct.jsp";
//	}


	@PostMapping("/save-product")
	public ResponseEntity saveProduct(@Valid  Product product, @RequestParam MultipartFile file) throws IOException {
		String imageuploadpath=writeImagetoFile(file);
			Date purchaseDate=new Date((System.currentTimeMillis()));
			product.setImage(imageuploadpath);
			productDetailService.insertIntoProduct(product);
		    List<LogRecord> logRecordList=userDetailService.getListLogRecord(purchaseDate);
		    for(LogRecord logRecord:logRecordList) {
				Projectuser projectuser = userDetailService.findByUsername(logRecord.getUserName());
				Map<String, Object> map = new HashMap<>();
				map.put("userId", projectuser.getUserId());
				map.put("username", projectuser.getUsername());
				map.put("productId", product.getProductId());
				purchaseDetailService.insertIntoPurchase(map);
			}
			Integer count=productDetailService.countProductByNameAndType(product.getProductName(), product.getProductType());
			for(int i=0;i<=count;i++) {
				List<PurchaseProduct> purchaseProducts = productDetailService.getProductByNameTypeAndPurchaseDate(purchaseDate);
				System.out.println(purchaseProducts);
				List<PurchaseProduct> productList = productDetailService.getProductByNameTypeLessThanPurchaseDate(product.getProductName(), product.getProductType(), purchaseDate);
				for (PurchaseProduct purchaseProduct : purchaseProducts) {
					for (PurchaseProduct productList1 : productList) {
						if (purchaseProduct.getProductId()!=productList1.getProductId()) {
							if (purchaseProduct.getPrice() >= productList1.getPrice()) {
								Double currentPrice = purchaseProduct.getPrice() - productList1.getPrice();
								Map<String, Object> map1 = new HashMap<>();
								map1.put("productId", purchaseProduct.getProductId());
								map1.put("productName", purchaseProduct.getProductName());
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
								map1.put("productName", purchaseProduct.getProductName());
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
		     ServiceResponse serviceResponse=new ServiceResponse("success", product);
			return new ResponseEntity(serviceResponse, HttpStatus.OK);
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

	@GetMapping(value = "/addToTrash/{productId}")
   public ResponseEntity moveToTrash(@PathVariable long productId)
   {
      Product product=productDetailService.getProductById(productId);
	   Date expireDate = new Date(System.currentTimeMillis());
      if(product.getExpiryDate().before(expireDate)) {
		  product.setIsExpired("true");
		  productDetailService.updateExpiredProduct(product);
		  trashDetailService.insertIntoTrash(product);
	  }
      ServiceResponse serviceResponse=new ServiceResponse("success", productId);
      return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }


	@GetMapping(value = "/getProductEditForm/{productId}")
	public ResponseEntity<Product> getProductEditForm(@PathVariable long productId)
	{
		Product product=productDetailService.getProductById(productId);
		ServiceResponse serviceResponse=new ServiceResponse("success", product);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	@PostMapping(value="/update-product")
   public ResponseEntity updateProduct(@Valid Product product, @RequestParam MultipartFile file) throws IOException {
   	 String imageuploadpath="";
   	 if(file.getOriginalFilename().isEmpty())
	 {
		 Product prod=productDetailService.getProductById(product.getProductId());
			imageuploadpath=prod.getImage();
	 }
	 else
		{
			imageuploadpath=writeImagetoFile(file);
		}
	 product.setImage(imageuploadpath);
	 productDetailService.updateIntoProduct(product);
     ServiceResponse serviceResponse=new ServiceResponse("success", product);
     return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@GetMapping(value = "/getListProduct")
	public List<Map> listProduct()
	{
		List<Map> map=new ArrayList<>();
		List<PurchaseProduct> purchaseProducts=productDetailService.getAllProductInfo();
		int totalquantity=productDetailService.getTotalNoOfQuantity();
		int totalnoproduct=productDetailService.getTotalNoOfProduct();
		double totalnoprice=productDetailService.getSumOfPrice();
		Map<String, Object> map1=new HashMap();
		map1.put("purchaseProducts", purchaseProducts);
		map1.put("totalquantity", totalquantity);
		map1.put("totalnoproduct", totalnoproduct);
		map1.put("totalnoprice", totalnoprice);
		map.add(map1);
		return  map;
	}

	@GetMapping(value = "/getListProductAnalysis")
   public List<ProductAnalysis> listProductAnalysis(){
     List<ProductAnalysis> productAnalysis=productDetailService.getAllProductAnalysis();
     return productAnalysis;
   }


   @GetMapping(value="/getDataByName/{productName}")
   public ResponseEntity<List<ProductAnalysis>> getDataByName(@PathVariable String productName)
   {
     List<ProductAnalysis> productAnalysisList=productDetailService.getDataByName(productName);
     System.out.println(productAnalysisList);
      ServiceResponse<List<ProductAnalysis>> serviceResponse=new ServiceResponse("success",productAnalysisList);
      return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }





	@GetMapping("/getProductByName/{productName}")
    public List<Map> getProductByName(@PathVariable String productName)
	{
		List<Map> map=new ArrayList<>();
		Map<String, Object> map1=new HashMap();
		List<PurchaseProduct> findProduct=productDetailService.findByProductName(productName);
		Integer totalNoOfQuantity=productDetailService.getTotalNoOfQuantity(productName);
		if(totalNoOfQuantity==null)
		{
			totalNoOfQuantity=0;
			map1.put("totalNoOfQuantity", totalNoOfQuantity);
		}
		Integer totalNoOfProduct=productDetailService.getTotalNoOfProduct(productName);
		Double totalPrice=productDetailService.getSumOfPrice(productName);
	    map1.put("findProduct", findProduct);
	    map1.put("totalNoOfQuantity", totalNoOfQuantity);
	    map1.put("totalNoOfProduct", totalNoOfProduct);
	    map1.put("totalPrice", totalPrice);
	    map.add(map1);
		return map;
	}




	@GetMapping(value = "/delete-product/{productId}")
	public ResponseEntity deleteProduct(@PathVariable long productId)
	{
		purchaseDetailService.deletePurchase(productId);
		productDetailService.deleteproductinfo(productId);
		ServiceResponse serviceResponse=new ServiceResponse("success", productId);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
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
