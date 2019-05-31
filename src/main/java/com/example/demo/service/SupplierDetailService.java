package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Supplier;
import com.example.demo.model.Supplier_Product;
import com.example.demo.model.Supplier_View;
import com.example.demo.repository.SupplierRepository;

@Service
public class SupplierDetailService {

	@Autowired
	private SupplierRepository supplierRepository;

    public void insertintosupplier(Supplier supplier)
    {
    	supplierRepository.insertintosupplier(supplier);
    	
    }
   
    public void insertintosupplierproduct(Supplier_Product supplierproduct)
    {
    	supplierRepository.insertintosupplierproduct(supplierproduct);
    }
    
    public List<Supplier_View> getAllSupplierInfo()
    {
    	return supplierRepository.getAllSupplierInfo();
    }
    
  
    public Supplier_View getSupplierById(long supplier_id, long product_id)
    {
    	return supplierRepository.getsupplierbyid(supplier_id, product_id);
    }
    
    public Supplier getSupplierId(long supplier_id)
    {
    	return supplierRepository.getsupplierid(supplier_id);
    }
    
    public void updateintosupplier(Supplier supplierview)
    {
    	supplierRepository.updateintosupplier(supplierview);
    }
    
    public void updateintosupplierview(Supplier_Product supplierview)
    {
    	supplierRepository.updateintosupplierview(supplierview);
    }
    
    public void deleteintosupplierview(long supplier_id, long product_id)
    {
    	supplierRepository.deleteintosupplierview(supplier_id, product_id);
    }
    
    public void deleteintosupplier(long supplier_id, long product_id)
    {
    	supplierRepository.deleteintosupplier(supplier_id, product_id);
    }
    
    public List<Supplier_View> getSupplierbybuydate(String[] buy_date)
    {
        return supplierRepository.getSupplierbybuydate(buy_date);
    }
    
    public List<Supplier> getAllSupplier()
    {
    	return supplierRepository.getAllSupplier();
    }
    
    public List<Supplier_View> getSupplierByName(String supplier_name)
    {
    	return supplierRepository.getSupplierByName(supplier_name);
    }
    
    public List<Supplier_View> getSupplierInformation()
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
	
	public double getSumofCost(String supplier_name) {
		return supplierRepository.getSumofCost(supplier_name);
	}
	
	  public int getNoofQuantity(String supplier_name)
	  {
		  return supplierRepository.getNoofQuantity(supplier_name);
	  }
	
	public int getNoofSupplier(String supplier_name) {
		
		return supplierRepository.getNoofSupplier(supplier_name);
	}
	
	public int getNoofProduct(String supplier_name)
	{
		return supplierRepository.getNoofProduct(supplier_name);
	}
	public Double getSumofCost(String[] buy_date) {
		
		return supplierRepository.getSumofCost(buy_date);
	}
	
	public int getNoofSupplier(String[] buy_date) {
		
		return supplierRepository.getNoofSupplier(buy_date);
	}
	
	public Integer getNoofQuantity(String[] buy_date) {
		
		return supplierRepository.getNoofQuantity(buy_date);
	}
	
	public int getNoofProduct(String[] buy_date) {
		
		return supplierRepository.getNoofProduct(buy_date);
	}
	
	
	public int NoofSupplier() {
		return supplierRepository.NoofSupplier();
	}
    
    public boolean createExcel(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response,
			HttpServletRequest request)
    {
    	return supplierRepository.createExcel(supplierproducts, context, response, request);
    }
    
    public boolean createPdf(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response, HttpServletRequest request)
    {
    	return supplierRepository.createPdf(supplierproducts, context, response, request);
    }
}
