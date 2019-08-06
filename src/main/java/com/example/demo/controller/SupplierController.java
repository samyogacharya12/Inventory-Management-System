package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.*;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

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
	private ProductDetailServiceImpl productDetailService;

	@Autowired
	private CustomerDetailServiceImpl customerDetailServiceImpl;
	@GetMapping("/getSupplierForm")
	public String getForm()
	{
		return "supplierForm.jsp";
	}

	@GetMapping("/getSupplierProductAddForm")
	public ModelAndView getSupplierAddForm(@RequestParam long supplierId, Model model)
	{
		List<PurchaseProduct> product1=productDetailService.getAllProductInfo();
		System.out.println(product1);
        model.addAttribute("product1", product1);
		Supplier supplier=supplierDetailService.getSupplierId(supplierId);
		return new ModelAndView("supplierproductAdd.jsp", "supplierAdd", supplier);
	}

	@GetMapping("/addSupplierProduct")
	public ModelAndView addSupplierProduct(@RequestParam long supplierId, Model model)
	{
		List<PurchaseProduct> product1=productDetailService.getAllProductInfo();
		System.out.println(product1);
        model.addAttribute("product1", product1);
		Supplier supplier=supplierDetailService.getSupplierId(supplierId);
		return new ModelAndView("supplierproductAdd.jsp", "supplierAdd", supplier);
	}

	@PostMapping("/save-supplier")
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

	@PostMapping("/save-supplierproduct")
	public String insertIntoSupplierProduct(@ModelAttribute SupplierProduct supplierproduct, @RequestParam long productId)
	{
		Map map=userDetailService.getUserTempData();
		Product product=productDetailService.getProductById(productId);
		int quantity=product.getQuantity();
		supplierproduct.setQuantity(quantity);
		supplierproduct.setUsername((String) map.get("username"));
		supplierDetailService.insertIntoSupplierProduct(supplierproduct);
    	return "redirect:/supplierproductAdd.jsp";
	}


	@GetMapping("/list-supplier")
	public ModelAndView getSupplier(Model model)
	{
		List<SupplierView> supplier=supplierDetailService.getAllSupplierInfo();
		List<SupplierView> supplier1=supplierDetailService.getSupplierInformation();
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

	@GetMapping("/getSupplierBySupplierIdAndProductId")
	public ModelAndView getSupplierEditForm(@RequestParam long supplierId, @RequestParam long productId)
	{
		SupplierView supplier=supplierDetailService.getSupplierBySupplierIdAndProductId(supplierId, productId);
		return new ModelAndView("supplierEdit.jsp", "supplierEdit", supplier);
	}

	@GetMapping("/getSupplierEditForm")
	public ModelAndView getSupplierEditForm(@RequestParam long supplierId)
	{
		Supplier supplier=supplierDetailService.getSupplierId(supplierId);
		return new ModelAndView("supplierpersonalEdit.jsp", "supplierEdit", supplier);

	}


	@GetMapping("/getSupplierProductEditForm")
	public ModelAndView getSupplierproductEditForm(@RequestParam long supplierId, @RequestParam long productId)
	{
		System.out.println(supplierId);
		System.out.println(productId);
		SupplierView supplier=supplierDetailService.getSupplierBySupplierIdAndProductId(supplierId, productId);
		return new ModelAndView("supplierproductEdit.jsp","supplierproduct",supplier);
	}

	@GetMapping("/supplierproductadd")
	public ModelAndView AddSupplierProductForm()
	{
		List<Supplier> supplier=supplierDetailService.getAllSupplier();
		return new ModelAndView("supplierproduct.jsp", "supplier", supplier);
	}

	@PostMapping("/update-supplierpersonal")
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
			System.out.println(suppliers.getSupplierName());
            supplierDetailService.updateIntoSupplier(suppliers);
            System.out.println(suppliers.getSupplierName());
		}
		return "redirect:/supplierpersonalEdit.jsp";
	}


	@PostMapping("/update-supplierproduct")
	public String updateSupplierProduct(@ModelAttribute Supplier suppliers,@RequestParam long supplierId,@RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
     {
	      Supplier supplier=supplierDetailService.getSupplierId(supplierId);
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
	public String updateSupplierProduct(@ModelAttribute SupplierProduct supplierproduct, @RequestParam long supplierId)
	{
		supplierDetailService.updateIntoSupplierView(supplierproduct);
		return "redirect:/supplierproductEdit.jsp";
	}

	@GetMapping("/delete-supplier")
	public String deleteSupplier(@RequestParam long supplierId, @RequestParam long productId)
	{
		supplierDetailService.deleteIntoSupplierView(supplierId, productId);
		supplierDetailService.deleteIntoSupplier(supplierId, productId);
		return "supplierList.jsp";
	}


	@GetMapping("/getSupplierByDate")
	public ModelAndView getSupplierByDate(Model model ,@RequestParam(value="buyDate[]") String buyDate[])
	{
		System.out.println(buyDate.toString());
		List<SupplierView> supplier=supplierDetailService.getSupplierInformation();
		model.addAttribute("supplier2", supplier);
	List<SupplierView> supplierproduct=supplierDetailService.getSupplierByBuyDate(buyDate);

	int totalsupplier=supplierDetailService.getNoofSupplier(buyDate);
	model.addAttribute("totalsupplier1", totalsupplier);
	Integer totalquantity=supplierDetailService.getNoofQuantity(buyDate);
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
	Double totalcost=supplierDetailService.getSumofCost(buyDate);
	if(totalcost==null)
	{
		totalcost=(double) 0;
		model.addAttribute("totalcost1", totalcost);
	}
	else
	{
		model.addAttribute("totalcost1", totalcost);
	}
	int totalproduct=supplierDetailService.getNoofProduct(buyDate);
	model.addAttribute("totalproduct1", totalproduct);

		return new ModelAndView("supplierList.jsp", "getSupplierByDate", supplierproduct);
	}

	@GetMapping("/getSupplierByName")
	public ModelAndView getSupplierByName(@RequestParam String supplierName,Model model)
	{
		System.out.println(supplierName);
		List<SupplierView> supplier1=supplierDetailService.getSupplierInformation();
		model.addAttribute("supplier1", supplier1);
		List<SupplierView> findSupplier=supplierDetailService.getSupplierByName(supplierName);
        double totalproductcost=supplierDetailService.getSumofCost(supplierName);
        model.addAttribute("totalcost", totalproductcost);
        int totalsupplier=supplierDetailService.getNoofSupplier(supplierName);
        System.out.println(totalsupplier);
        model.addAttribute("totalsupplier", totalsupplier);
        int totalquantity=supplierDetailService.getNoofQuantity(supplierName);
        model.addAttribute("totalquantity", totalquantity);
        int totalproduct=supplierDetailService.getNoofProduct(supplierName);
        model.addAttribute("totalproduct", totalproduct);
		return new ModelAndView("supplierList.jsp", "getSupplierByName", findSupplier);
	}


	@GetMapping("/getSupplierPersonalInfo")
	public ModelAndView getSupplierPersonalInfo()
	{
		List<Supplier> supplier=supplierDetailService.getAllSupplier();
		return new ModelAndView("supplierpersonal.jsp", "supplierinfo", supplier);
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
