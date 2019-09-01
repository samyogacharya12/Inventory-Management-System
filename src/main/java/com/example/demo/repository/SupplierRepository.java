package com.example.demo.repository;

import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Supplier;
import com.example.demo.model.SupplierProduct;
import com.example.demo.model.SupplierProductAnalysis;
import com.example.demo.model.SupplierView;

public interface SupplierRepository {


     Supplier getPersonalSupplierByName(String supplierName);
	 void insertIntoSupplierProduct(SupplierProduct supplierproduct);
     void insertIntoSupplierProductAnalysis(Map map);
     List<SupplierProductAnalysis> getAllSupplierProductAnalysis();
     List<SupplierProductAnalysis> getSupplierProductAnalysisBySupplierId(Integer supplierId);
	 void insertIntoSupplier(Supplier supplier);
	 List<SupplierView> getAllSupplierByProductNameAndType(String productName, String productType);
     List<SupplierView> getAllSupplierInfo();
     SupplierView getSupplierBySupplierIdAndProductId(long supplierId, long productId);
     SupplierView getSupplierBySupplierIdAndUniqueId(long supplierId, long uniqueId);
     Supplier getSupplierId(long supplierId);
     void updateIntoSupplier(Supplier supplier);
     void updateIntoSupplierView(SupplierProduct supplierview);
     void deleteIntoSupplierView(long supplierId, long productId);
     void deleteIntoSupplier(long supplierId, long productId);
     List<SupplierView> getSupplierByBuyDate(String supplyStartDate, String supplyLastDate);
     List<Supplier> getAllSupplier();
     List<SupplierView> getSupplierByName(String supplierName);
     List<SupplierView> getSupplierDistinctName();
     double getSumOfCost();
     int getNoofSupplier();
     int getNoofQuantity();
     int getNoofProduct();
     int NoofSupplier();
     double getSumofCost(String supplierName);
     int getNoofSupplier(String supplierName);
     int getNoofQuantity(String supplierName);
     int getNoofProduct(String supplierName);
    Double getCostFromExpiredProduct(String supplyStartDate, String supplyLastDate);
    Double getSumofCost(String supplyStartDate, String supplyLastDate);
    int getNoofSupplier(String supplyStartDate, String supplyLastDate);
    Integer getNoofQuantity(String supplyStartDate, String supplyLastDate);
     int getNoofProduct(String supplyStartDate, String supplyLastDate);
}

