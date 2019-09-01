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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class SupplierRestController {

	String uploaded_file="C:/Users/DELL/Desktop/New Files/";

	@Autowired
	private SupplierDetailService supplierDetailService;
	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private ServletContext context;

	@Autowired
	private ProductDetailServiceImpl productDetailService;


	@Autowired
	private NotificationDetailServiceImpl notificationDetailService;


	@PostMapping("/save-supplier")
	public ResponseEntity saveSupplierPersonal(@Valid Supplier supplier, @RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath=writeImagetoFile(file);
		supplier.setImage(imageuploadpath);
		supplierDetailService.insertIntoSupplier(supplier);
		ServiceResponse<Supplier> serviceResponse=new ServiceResponse<>("success", supplier);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	   @PostMapping("/save-supplierproduct")
      public ResponseEntity insertIntoSupplierProduct(@Valid @RequestBody SupplierProduct supplierProduct)
	  {
		  Date date=new Date(System.currentTimeMillis());
	    List<LogRecord> logRecordList=userDetailService.getListLogRecord(date);
	    for(LogRecord logRecord: logRecordList)
		{
			supplierProduct.setUsername(logRecord.getUserName());
		}
	  	 Product product=productDetailService.getProductById(supplierProduct.getProductId());
	     int quantity=product.getQuantity();
	  	supplierProduct.setQuantity(quantity);
	  	 supplierDetailService.insertIntoSupplierProduct(supplierProduct);
		  supplierDetailService.calculateDifferenceInSupplierProductCost(supplierProduct.getSupplierId(),supplierProduct.getProductId(), product.getProductName(), product.getProductType(), supplierProduct.getCost());
         System.out.println(supplierProduct);
          notificationDetailService.sendNotificationToSupplier(supplierProduct);
	  	 ServiceResponse<SupplierProduct> serviceResponse=new ServiceResponse<>("success", supplierProduct);
	  	return new ResponseEntity(serviceResponse, HttpStatus.OK);
	  }


	@GetMapping(value = "/getListSupplier")
	public List<Map> getSupplierList()
	{
		List<Map> map=new ArrayList<>();
		List<SupplierView> supplierViews=supplierDetailService.getAllSupplierInfo();
        List<SupplierView> supplierViews1=supplierDetailService.getSupplierDistinctName();
        int numberofSupplier=supplierDetailService.getNoofSupplier();
        double supplierCost=supplierDetailService.getSupplierCost();
        int supplierQuantity=supplierDetailService.getNoofQuantity();
        int supplierProduct=supplierDetailService.getNoofProduct();
        Map map1=new HashMap();
        map1.put("supplierviews", supplierViews);
        map1.put("supplierViews1", supplierViews1);
        map1.put("numberofSupplier", numberofSupplier);
        map1.put("supplierCost", supplierCost);
        map1.put("supplierQuantity", supplierQuantity);
        map1.put("supplierProduct", supplierProduct);
        map.add(map1);
        return  map;
	}

	   @GetMapping(value = "/getSupplierProductAddForm/{supplierId}")
	   public List<Map> getSupplierProductAddForm(@PathVariable long supplierId)
	   {
	   	 List<Map> mapList=new ArrayList<>();
	   	 Map<String, Object> map=new HashMap();
	   	 List<PurchaseProduct> purchaseProducts=productDetailService.getAllProductInfo();
	   	 Supplier supplier=supplierDetailService.getSupplierId(supplierId);
	   	 map.put("purchaseProducts", purchaseProducts);
	   	 map.put("supplier", supplier);
	   	 mapList.add(map);
	   	 return mapList;
	   }


	@GetMapping(value="/getSupplierEditForm/{supplierId}")
   public ResponseEntity<Supplier> getSupplierEditForm(@PathVariable long supplierId)
   {
   	System.out.println(supplierId);
   	 Supplier supplier=supplierDetailService.getSupplierId(supplierId);
   	 ServiceResponse serviceResponse=new ServiceResponse("success", supplier);
   	 return new ResponseEntity(serviceResponse, HttpStatus.OK);
   }



	@GetMapping(value = "/getSupplierBySupplierIdAndUniqueId/{supplierId}/{supplierUniqueId}")
	public ResponseEntity<SupplierView> getSupplierEditForm(@PathVariable long supplierId, @PathVariable long supplierUniqueId)
	{
		SupplierView supplierView=supplierDetailService.getSupplierBySupplierIdAndUniqueId(supplierId, supplierUniqueId);
		ServiceResponse serviceResponse=new ServiceResponse("success", supplierView);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	  @GetMapping(value = "/getSupplierProductEditForm/{supplierId}/{supplierUniqueId}")
	  public List<Map> getSupplierProductEditForm(@PathVariable long supplierId, @PathVariable long supplierUniqueId)
	  {
	  	List<Map> mapList=new ArrayList<>();
	  	Map<String, Object> map=new HashMap<>();
	  	SupplierView supplierView=supplierDetailService.getSupplierBySupplierIdAndUniqueId(supplierId, supplierUniqueId);
	  	List<PurchaseProduct> purchaseProducts=productDetailService.getAllProductInfo();
	  	map.put("supplierView", supplierView);
	  	map.put("purchaseProducts", purchaseProducts);
	  	mapList.add(map);
	  	return mapList;
	  }




	@PostMapping("/update-supplierpersonal")
     public ResponseEntity updateSupplierPersonal(@Valid Supplier supplier,@RequestParam MultipartFile file) throws IOException {
		 String imageuploadpath="";
		 if(file.getOriginalFilename().isEmpty()) {

		 	Supplier supplier1=supplierDetailService.getSupplierId(supplier.getSupplierId());
		 	imageuploadpath=supplier1.getImage();
		 }
		 else
		 {
			 imageuploadpath = writeImagetoFile(file);
		 }
		supplier.setImage(imageuploadpath);
		supplierDetailService.updateIntoSupplier(supplier);
        ServiceResponse<Supplier> response=new ServiceResponse("success", supplier);
		 return new ResponseEntity(response, HttpStatus.OK);

	 }

	@PostMapping(value = "/update-supplierproduct")
    public ResponseEntity updateSupplierProduct(@Valid @RequestBody SupplierProduct supplierProduct)
	{
		System.out.println(supplierProduct);
		System.out.println(supplierProduct.getSupplierId());
		supplierDetailService.updateIntoSupplierView(supplierProduct);
		ServiceResponse serviceResponse=new ServiceResponse("success", supplierProduct);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@GetMapping("/delete-supplier/{supplierId}/{productId}")
	public  ResponseEntity deleteSupplier(@PathVariable long supplierId, @PathVariable long productId)
	{
		supplierDetailService.deleteIntoSupplierView(supplierId, productId);
		supplierDetailService.deleteIntoSupplier(supplierId, productId);
		ServiceResponse serviceResponse=new ServiceResponse("success", supplierId);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	@GetMapping("/getSupplierDataByDate/{supplyStartDate}/{supplyLastDate}")
	public List<Map> getSupplierDataByDate(@PathVariable String supplyStartDate, @PathVariable String supplyLastDate)
	{
		List<Map> mapList=new ArrayList<>();
		Map<String, Object> map=new HashMap();
		List<SupplierView> supplierDistinctName=supplierDetailService.getSupplierDistinctName();
		List<SupplierView> supplierProductByDate=supplierDetailService.getSupplierByBuyDate(supplyStartDate, supplyLastDate);
		int numberofSupplier=supplierDetailService.getNoofSupplier(supplyStartDate, supplyLastDate);
		Integer totalQuantity=supplierDetailService.getNoofQuantity(supplyStartDate, supplyLastDate);
		if(totalQuantity==null)
	   {
		   totalQuantity=0;
		map.put("totalQuantity", totalQuantity);
	   }
		Double totalCost=supplierDetailService.getSumofCost(supplyStartDate, supplyLastDate);
		if(totalCost==null)
	{
		totalCost=(double) 0;
		map.put("totalCost", totalCost);
	}
		int numberofProduct=supplierDetailService.getNoofProduct(supplyStartDate, supplyLastDate);
		map.put("numberOfSupplier", numberofSupplier);
		map.put("supplierDistinctName", supplierDistinctName);
		map.put("supplierProductByDate", supplierProductByDate);
		map.put("totalQuantity", totalQuantity);
		map.put("totalCost", totalCost);
		map.put("numberOfProduct", numberofProduct);
		mapList.add(map);
		return mapList;
	}


	@GetMapping("/getSupplierByName/{supplierName}")
    public List<Map> getSupplierByName(@PathVariable String supplierName)
	{
		List<Map> maps=new ArrayList<>();
		Map<String, Object> map=new HashMap();
		List<SupplierView> supplierDistinctName=supplierDetailService.getSupplierDistinctName();
		List<SupplierView> findSupplier=supplierDetailService.getSupplierByName(supplierName);
		double totalsuppliercost=supplierDetailService.getSumofCost(supplierName);
		int totalsupplier=supplierDetailService.getNoofSupplier(supplierName);
		int supplierquantity=supplierDetailService.getNoofQuantity(supplierName);
		int supplierproduct=supplierDetailService.getNoofProduct(supplierName);
		map.put("supplierDistinctName", supplierDistinctName);
		map.put("findSupplier", findSupplier);
		map.put("totalsuppliercost", totalsuppliercost);
        map.put("totalsupplier", totalsupplier);
        map.put("supplierquantity", supplierquantity);
        map.put("supplierproduct", supplierproduct);
        maps.add(map);
        return maps;
	}

	@GetMapping(value="/getSupplierProductAnalysisList")
   public List<SupplierProductAnalysis> getAllSupplierAnalysisData()
   {
   	 List<SupplierProductAnalysis> supplierProductAnalyses=supplierDetailService.getAllSupplierProductAnalysis();
   	 return supplierProductAnalyses;
   }


	@GetMapping(value="/gePersonalSupplierByName/{supplierName}")
    public ResponseEntity<Supplier> getPersonalSupplierByName(@PathVariable String supplierName)
	{
       Supplier supplier=supplierDetailService.getPersonalSupplierByName(supplierName);
       ServiceResponse<Supplier> supplierServiceResponse=new ServiceResponse("success", supplier);
       return new ResponseEntity(supplierServiceResponse, HttpStatus.OK);
	}


	@GetMapping(value="/getSupplierAnalysisDataBySupplierId/{supplierId}")
	 public List<SupplierProductAnalysis> getSupplierProductAnalysisBySupplierId(@PathVariable Integer supplierId)
	 {
	 	List<SupplierProductAnalysis> supplierProductAnalyses=supplierDetailService.getSupplierProductAnalysisBySupplierId(supplierId);
	 	return supplierProductAnalyses;
	 }



	@GetMapping("/getSupplierPersonalList")
	public  List<Supplier> getSupplierPersonalInfo()
	{
		List<Supplier> suppliers=supplierDetailService.getAllSupplier();
		return suppliers;
	}



	@GetMapping(value="/createExcelSupplier")
	public void createExcelSupplier(HttpServletRequest request, HttpServletResponse response)
	{
		List<SupplierView> supplierproducts=supplierDetailService.getAllSupplierInfo();
		boolean isflag=supplierDetailService.createExcelForSuppliers(supplierproducts, context, response, request);
		if(isflag)
		{
			String filepath=request.getServletContext().getRealPath("/resources/reports/"+"suppliers"+".xls");
			filedownload(filepath, response, "suppliers.xls");
		}
	}
	@GetMapping(value="/createPdfSupplier")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<SupplierView> supplierproducts=supplierDetailService.getAllSupplierInfo();
		boolean isflag=supplierDetailService.createPdfForSuppliers(supplierproducts, context, response, request);
		if(isflag)
		{
			String filepath=request.getServletContext().getRealPath("/resources/reports/"+"suppliers"+".pdf");
			filedownload(filepath, response, "suppliers.pdf");
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
