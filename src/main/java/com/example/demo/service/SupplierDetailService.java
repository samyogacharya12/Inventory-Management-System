package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.SupplierProductAnalysis;
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

	public void insertIntoSupplierProductAnalysis(Map map)
	{
		supplierRepository.insertIntoSupplierProductAnalysis(map);
	}

	public List<SupplierProductAnalysis> getAllSupplierProductAnalysis() {
		return supplierRepository.getAllSupplierProductAnalysis();
	}

	 public  void calculateDifferenceInSupplierProductCost(long supplierId, long productId, String productName, String productType, double cost)
	 {
      List<SupplierView> supplierViews=getAllSupplierByProductNameAndType(productName, productType);
       for(SupplierView supplierView: supplierViews)
	   {
	   	if(supplierView.getProductId()!=productId) {
			if (cost >= supplierView.getCost()) {
				System.out.println(cost);
				System.out.println(supplierView.getCost());
				double costIncreament = cost - supplierView.getCost();
				Map<String, Object> map = new HashMap<>();
				map.put("supplierId", supplierId);
				map.put("productId", productId);
				map.put("productName", supplierView.getProductName());
				map.put("productType", supplierView.getProductType());
				map.put("pastCost", supplierView.getCost());
				map.put("presentCost", cost);
				map.put("costIncreament", costIncreament);
				map.put("costDecreament", 0);
				map.put("referenceSupplierId", supplierView.getSupplierId());
				map.put("referenceProductId", supplierView.getProductId());
				insertIntoSupplierProductAnalysis(map);
			} else if (cost == supplierView.getCost()) {
				System.out.println("The given data are equal");
			} else if (cost <= supplierView.getCost()) {
				System.out.println(cost);
				System.out.println(supplierView.getCost());
				double costDecreament = supplierView.getCost() - cost;
				Map<String, Object> map = new HashMap<>();
				map.put("supplierId", supplierId);
				map.put("productId", productId);
				map.put("productName", supplierView.getProductName());
				map.put("productType", supplierView.getProductType());
				map.put("pastCost", supplierView.getCost());
				map.put("presentCost", cost);
				map.put("costIncreament", 0);
				map.put("costDecreament", costDecreament);
				map.put("referenceSupplierId", supplierView.getSupplierId());
				map.put("referenceProductId", supplierView.getProductId());
				insertIntoSupplierProductAnalysis(map);
			} else {
				System.out.println("The Given Data is Empty");
			}
		}
	   }
	 }

	public List<SupplierProductAnalysis> getSupplierProductAnalysisBySupplierId(Integer supplierId) {
		return supplierRepository.getSupplierProductAnalysisBySupplierId(supplierId);
	}

	public List<SupplierView> getAllSupplierByProductNameAndType(String productName, String productType)
	{
		return supplierRepository.getAllSupplierByProductNameAndType(productName, productType);
	}


	public SupplierView getSupplierBySupplierIdAndUniqueId(long supplierId, long uniqueId) {
		return supplierRepository.getSupplierBySupplierIdAndUniqueId(supplierId, uniqueId);
	}


	public Supplier getPersonalSupplierByName(String supplierName) {
		return supplierRepository.getPersonalSupplierByName(supplierName);
	}

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
    
    public List<SupplierView> getSupplierByBuyDate(String supplyStartDate, String supplyLastDate)
    {
        return supplierRepository.getSupplierByBuyDate(supplyStartDate, supplyLastDate);
    }
    
    public List<Supplier> getAllSupplier()
    {
    	return supplierRepository.getAllSupplier();
    }
    
    public List<SupplierView> getSupplierByName(String supplierName)
    {
    	return supplierRepository.getSupplierByName(supplierName);
    }
    
    public List<SupplierView> getSupplierDistinctName()
    {
    	return supplierRepository.getSupplierDistinctName();
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
	public Double getSumofCost(String supplyStartDate, String supplyLastDate) {
		
		return supplierRepository.getSumofCost(supplyStartDate,  supplyLastDate);
	}
	
	public int getNoofSupplier(String supplyStartDate, String supplyLastDate) {
		
		return supplierRepository.getNoofSupplier(supplyStartDate, supplyLastDate);
	}
	
	public Integer getNoofQuantity(String supplyStartDate, String supplyLastDate) {
		
		return supplierRepository.getNoofQuantity(supplyStartDate, supplyLastDate);
	}
	
	public int getNoofProduct(String supplyStartDate, String supplyLastDate) {
		
		return supplierRepository.getNoofProduct(supplyStartDate, supplyLastDate);
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

	public Double getCostFromExpiredProduct(String supplyStartDate, String supplyLastDate)
	{
		return supplierRepository.getCostFromExpiredProduct(supplyStartDate, supplyLastDate);
	}
}
