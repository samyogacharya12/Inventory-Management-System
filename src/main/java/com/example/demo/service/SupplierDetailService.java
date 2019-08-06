package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supplier;
import com.example.demo.model.SupplierProduct;
import com.example.demo.model.SupplierView;
import com.example.demo.repository.SupplierRepository;

@Service
public class SupplierDetailService {

	@Autowired
	private SupplierRepository supplierRepository;

	@Autowired
	private ExcelService excelService;

	@Autowired
	private PdfService pdfService;

    public void insertIntoSupplier(Supplier supplier)
    {
    	supplierRepository.insertIntoSupplier(supplier);
    	
    }
   
    public void insertIntoSupplierProduct(SupplierProduct supplierproduct)
    {
    	supplierRepository.insertIntoSupplierProduct(supplierproduct);
    }
    
    public List<SupplierView> getAllSupplierInfo()
    {
    	return supplierRepository.getAllSupplierInfo();
    }
    
  
    public SupplierView getSupplierBySupplierIdAndProductId(long supplierId, long productId)
    {
    	return supplierRepository.getSupplierBySupplierIdAndProductId(supplierId, productId);
    }
    
    public Supplier getSupplierId(long supplierId)
    {
    	return supplierRepository.getSupplierId(supplierId);
    }
    
    public void updateIntoSupplier(Supplier supplierview)
    {
    	supplierRepository.updateIntoSupplier(supplierview);
    }
    
    public void updateIntoSupplierView(SupplierProduct supplierview)
    {
    	supplierRepository.updateIntoSupplierView(supplierview);
    }
    
    public void deleteIntoSupplierView(long supplierId, long productId)
    {
    	supplierRepository.deleteIntoSupplierView(supplierId, productId);
    }
    
    public void deleteIntoSupplier(long supplierId, long productId)
    {
    	supplierRepository.deleteIntoSupplier(supplierId, productId);
    }
    
    public List<SupplierView> getSupplierByBuyDate(String[] buyDate)
    {
        return supplierRepository.getSupplierByBuyDate(buyDate);
    }
    
    public List<Supplier> getAllSupplier()
    {
    	return supplierRepository.getAllSupplier();
    }
    
    public List<SupplierView> getSupplierByName(String supplierName)
    {
    	return supplierRepository.getSupplierByName(supplierName);
    }
    
    public List<SupplierView> getSupplierInformation()
    {
    	return supplierRepository.getSupplierInformaton();
    }
    
    public double getSupplierCost()
    {
    	return supplierRepository.getSumOfCost();
    }
    
    public int getNoofSupplier() {
    	return supplierRepository.getNoofSupplier();
    }
    
	public int getNoofQuantity() {
		
		return supplierRepository.getNoofQuantity();
	}
	
	public int getNoofProduct() {
		
		return supplierRepository.getNoofProduct();
	}
	
	public double getSumofCost(String supplierName) {
		return supplierRepository.getSumofCost(supplierName);
	}
	
	  public int getNoofQuantity(String supplierName)
	  {
		  return supplierRepository.getNoofQuantity(supplierName);
	  }
	
	public int getNoofSupplier(String supplierName) {
		
		return supplierRepository.getNoofSupplier(supplierName);
	}
	
	public int getNoofProduct(String supplierName)
	{
		return supplierRepository.getNoofProduct(supplierName);
	}
	public Double getSumofCost(String[] buyDate) {
		
		return supplierRepository.getSumofCost(buyDate);
	}
	
	public int getNoofSupplier(String[] buyDate) {
		
		return supplierRepository.getNoofSupplier(buyDate);
	}
	
	public Integer getNoofQuantity(String[] buyDate) {
		
		return supplierRepository.getNoofQuantity(buyDate);
	}
	
	public int getNoofProduct(String[] buyDate) {
		
		return supplierRepository.getNoofProduct(buyDate);
	}
	
	
	public int NoofSupplier() {
		return supplierRepository.NoofSupplier();
	}
    
    public boolean createExcelForSuppliers(List<SupplierView> supplierproducts, ServletContext context, HttpServletResponse response,
										   HttpServletRequest request)
    {
    	return excelService.createExcelForSuppliers(supplierproducts, context, response, request);
    }
    
    public boolean createPdfForSuppliers(List<SupplierView> supplierproducts, ServletContext context, HttpServletResponse response, HttpServletRequest request)
    {
    	return pdfService.createPdfForSuppliers(supplierproducts, context, response, request);
    }

	public Double getCostFromExpiredProduct(String[] buy_date)
	{
		return supplierRepository.getCostFromExpiredProduct(buy_date);
	}
}
