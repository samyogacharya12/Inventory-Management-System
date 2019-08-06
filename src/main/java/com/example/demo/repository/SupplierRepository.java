package com.example.demo.repository;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Supplier;
import com.example.demo.model.SupplierProduct;
import com.example.demo.model.SupplierView;

public interface SupplierRepository {

	 void insertIntoSupplierProduct(SupplierProduct supplierproduct);
	 void insertIntoSupplier(Supplier supplier);
     List<SupplierView> getAllSupplierInfo();
     SupplierView getSupplierBySupplierIdAndProductId(long supplierId, long productId);
     Supplier getSupplierId(long supplierId);
     void updateIntoSupplier(Supplier supplier);
     void updateIntoSupplierView(SupplierProduct supplierview);
     void deleteIntoSupplierView(long supplierId, long productId);
     void deleteIntoSupplier(long supplierId, long productId);
     List<SupplierView> getSupplierByBuyDate(String[] buyDate);
     List<Supplier> getAllSupplier();
     List<SupplierView> getSupplierByName(String supplierName);
     List<SupplierView> getSupplierInformaton();
     double getSumOfCost();
     int getNoofSupplier();
     int getNoofQuantity();
     int getNoofProduct();
     int NoofSupplier();
     double getSumofCost(String supplierName);
     int getNoofSupplier(String supplierName);
     int getNoofQuantity(String supplierName);
     int getNoofProduct(String supplierName);
    Double getCostFromExpiredProduct(String[] buyDate);
    Double getSumofCost(String[] buyDate);
    int getNoofSupplier(String[] buyDate);
    Integer getNoofQuantity(String[] buyDate);
     int getNoofProduct(String[] buyDate);
}

