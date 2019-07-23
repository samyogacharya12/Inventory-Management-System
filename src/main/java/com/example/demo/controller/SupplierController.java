package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Product;
import com.example.demo.model.Supplier;
import com.example.demo.model.Supplier_Product;
import com.example.demo.model.Supplier_View;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
public class SupplierController {

	String uploaded_file="C:/Users/DELL/Desktop/New Files/";
	
	@Autowired
	private SupplierDetailService supplierDetailService;
	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private ServletContext context;
	
	@Autowired
	private CustomerDetailServiceImpl customerDetailServiceImpl;
	@GetMapping("/supplier_Form")
	public String getForm()
	{	
		return "supplierForm.jsp";
	}

	@GetMapping("/getSupplier")
	public ModelAndView getSupplierId(@RequestParam long supplier_id, Model model)
	{
		List<Product> product1=userDetailService.getAllProductInfo();
		System.out.println(product1);
        model.addAttribute("product1", product1);
		Supplier supplier=supplierDetailService.getSupplierId(supplier_id);
		return new ModelAndView("supplierproductAdd.jsp", "supplierAdd", supplier);
	}
	
	@GetMapping("/addSupplierProduct")
	public ModelAndView addSupplierProduct(@RequestParam long supplier_id, Model model)
	{
		List<Product> product1=userDetailService.getAllProductInfo();
		System.out.println(product1);
        model.addAttribute("product1", product1);
		Supplier supplier=supplierDetailService.getSupplierId(supplier_id);
		return new ModelAndView("supplierproductAdd.jsp", "supplierAdd", supplier);
	}
	
	@PostMapping("/save_supplier")
	public String postForm(HttpServletRequest request, @ModelAttribute Supplier supplier, @RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath=writeImagetoFile(file);
		if(supplier!=null)
		{
			supplier.setImage(imageuploadpath);
			supplierDetailService.insertIntoSupplier(supplier);
		}	
		return "supplierForm.jsp";
	}
	
	@PostMapping("/add_supplierproduct")
	public String postSupplierProductForm(@ModelAttribute Supplier_Product supplierproduct, @RequestParam long product_id)
	{	
		Product product=userDetailService.getquantitybyid(product_id);
		int quantity=product.getQuantity();
		supplierproduct.setQuantity(quantity);
		supplierDetailService.insertIntoSupplierProduct(supplierproduct);
    	return "redirect:/supplierproductAdd.jsp"; 	
	}
	
	
	@GetMapping("/list_supplier")
	public ModelAndView getSupplier(Model model)
	{	
		List<Supplier_View> supplier=supplierDetailService.getAllSupplierInfo();
		List<Supplier_View> supplier1=supplierDetailService.getSupplierInformation();
		System.out.println(supplier1);
		 double supplier2=supplierDetailService.getSupplierCost();
		 int supplier3=supplierDetailService.getNoofSupplier();
		 int supplier4=supplierDetailService.getNoofQuantity();
		 int supplier5=supplierDetailService.getNoofProduct();
		model.addAttribute("supplier4", supplier2);
		model.addAttribute("supplier5", supplier3);
		model.addAttribute("supplier3", supplier1);
		model.addAttribute("supplier6", supplier4);
		model.addAttribute("supplier7", supplier5);
		return new ModelAndView("supplierList.jsp", "supplier", supplier);
	}
	
	@GetMapping("/supplier")
	public ModelAndView getSupplierEditForm(@RequestParam long supplier_id, @RequestParam long product_id)
	{
		Supplier_View supplier=supplierDetailService.getSupplierBySupplierIdAndProductId(supplier_id, product_id);
		return new ModelAndView("supplierEdit.jsp", "supplierEdit", supplier);	
	}
	
	@GetMapping("/supplierEdit")
	public ModelAndView getSupplierEditForm(@RequestParam long supplier_id)
	{
		Supplier supplier=supplierDetailService.getSupplierId(supplier_id);
		return new ModelAndView("supplierpersonalEdit.jsp", "supplierEdit", supplier);
		
	}
    
	
	@GetMapping("/supplierproduct")
	public ModelAndView getSupplierproductEditForm(@RequestParam long supplier_id, @RequestParam long product_id)
	{
		System.out.println(supplier_id);
		System.out.println(product_id);
		Supplier_View supplier=supplierDetailService.getSupplierBySupplierIdAndProductId(supplier_id, product_id);
		return new ModelAndView("supplierproductEdit.jsp","supplierproduct",supplier);
	}
	
	@GetMapping("/supplierproductadd")
	public ModelAndView AddSupplierProductForm()
	{
		List<Supplier> supplier=supplierDetailService.getAllSupplier();
		return new ModelAndView("supplierproduct.jsp", "supplier", supplier);
	}
	
	@PostMapping("/update_supplierpersonal")
	public String updateSupplierData(@ModelAttribute Supplier suppliers, @RequestParam long supplier_id, @RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
		{
			Supplier supplier=supplierDetailService.getSupplierId(supplier_id);
			imageuploadpath=supplier.getImage();
		}
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		if(suppliers!=null)
		{
			suppliers.setImage(imageuploadpath);
			System.out.println(suppliers.getSupplier_name());
            supplierDetailService.updateIntoSupplier(suppliers);
            System.out.println(suppliers.getSupplier_name());
		}
		return "redirect:/supplierpersonalEdit.jsp";
	}
	
	
	@PostMapping("/update_supplier")
	public String updateSupplier(@ModelAttribute Supplier suppliers,@RequestParam long supplier_id,@RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
     {
	      Supplier supplier=supplierDetailService.getSupplierId(supplier_id);
	      imageuploadpath=supplier.getImage();
     }
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		
		if(suppliers!=null)
		{
			suppliers.setImage(imageuploadpath);
			supplierDetailService.updateIntoSupplier(suppliers);
		}
		
		return "redirect:/supplierEdit.jsp";
		
	}
	
	@PostMapping("/update_supplierproduct")
	public String updateSupplierProduct(@ModelAttribute Supplier_Product supplierproduct, @RequestParam long supplier_id)
	{
		supplierDetailService.updateIntoSupplierView(supplierproduct);
		return "redirect:/supplierproductEdit.jsp";
	}
	
	@GetMapping("/deletesupplier")
	public String deleteSupplier(@RequestParam long supplier_id, @RequestParam long product_id)
	{
		supplierDetailService.deleteIntoSupplierView(supplier_id, product_id);
		supplierDetailService.deleteIntoSupplier(supplier_id, product_id);
		return "supplierList.jsp";
	}
	
	
	@GetMapping("/getDate")
	public ModelAndView getSupplier(Model model ,@RequestParam(value="buy_date[]") String buy_date[])
	{
		System.out.println(buy_date.toString());
		List<Supplier_View> supplier=supplierDetailService.getSupplierInformation();
		model.addAttribute("supplier2", supplier);
	List<Supplier_View> supplierproduct=supplierDetailService.getSupplierByBuyDate(buy_date);
	
	int totalsupplier=supplierDetailService.getNoofSupplier(buy_date);
	model.addAttribute("totalsupplier1", totalsupplier);
	Integer totalquantity=supplierDetailService.getNoofQuantity(buy_date);
	System.out.println(totalquantity);
	if(totalquantity==null)
	{
		totalquantity=0;
		System.out.println(totalquantity);
		model.addAttribute("totalquantity1", totalquantity);
	}
	else
	{
	model.addAttribute("totalquantity1", totalquantity);
	}
	Double totalcost=supplierDetailService.getSumofCost(buy_date);
	if(totalcost==null)
	{
		totalcost=(double) 0;
		model.addAttribute("totalcost1", totalcost);
	}
	else
	{
		model.addAttribute("totalcost1", totalcost);
	}
	int totalproduct=supplierDetailService.getNoofProduct(buy_date);
	model.addAttribute("totalproduct1", totalproduct);
	
		return new ModelAndView("supplierList.jsp", "begin_date", supplierproduct);
	}
	
	@GetMapping("/getSupplierByName")
	public ModelAndView getSupplierByName(@RequestParam String supplier_name,Model model)
	{
		System.out.println(supplier_name);
		List<Supplier_View> supplier1=supplierDetailService.getSupplierInformation();
		model.addAttribute("supplier1", supplier1);
		List<Supplier_View> findSupplier=supplierDetailService.getSupplierByName(supplier_name);
        double totalproductcost=supplierDetailService.getSumofCost(supplier_name);
        model.addAttribute("totalcost", totalproductcost);
        int totalsupplier=supplierDetailService.getNoofSupplier(supplier_name);
        System.out.println(totalsupplier);
        model.addAttribute("totalsupplier", totalsupplier);
        int totalquantity=supplierDetailService.getNoofQuantity(supplier_name);
        model.addAttribute("totalquantity", totalquantity);
        int totalproduct=supplierDetailService.getNoofProduct(supplier_name);
        model.addAttribute("totalproduct", totalproduct);
		return new ModelAndView("supplierList.jsp", "supply", findSupplier);
	}
	
	
	@GetMapping("/supplier_Report")
	public ModelAndView getSupplierPersonalInfo()
	{
		List<Supplier> supplier=supplierDetailService.getAllSupplier();
		return new ModelAndView("supplierpersonal.jsp", "supplierinfo", supplier);
	}
	
	@GetMapping(value="/createExcelSupplier")
	public void createExcelSupplier(HttpServletRequest request, HttpServletResponse response)
	{
		List<Supplier_View> supplierproducts=supplierDetailService.getAllSupplierInfo();
		boolean isflag=supplierDetailService.createExcel(supplierproducts, context, response, request);
		if(isflag)
		{
			String filepath=request.getServletContext().getRealPath("/resources/reports/"+"suppliers"+".xls");
			filedownload(filepath, response, "suppliers.xls");
		}
	}
	@GetMapping(value="/createPdfSupplier")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<Supplier_View> supplierproducts=supplierDetailService.getAllSupplierInfo();
		boolean isflag=supplierDetailService.createPdf(supplierproducts, context, response, request);
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
