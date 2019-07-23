package com.example.demo.repository;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Supplier;
import com.example.demo.model.Supplier_Product;
import com.example.demo.model.Supplier_View;

public interface SupplierRepository {

	public void insertIntoSupplierProduct(Supplier_Product supplierproduct);
	public void insertIntoSupplier(Supplier supplier);
    public List<Supplier_View> getAllSupplierInfo();
    public Supplier_View getSupplierBySupplierIdAndProductId(long supplier_id, long product_id);
    public Supplier getSupplierId(long supplier_id);
    public void updateIntoSupplier(Supplier supplier);
    public void updateIntoSupplierView(Supplier_Product supplierview);
    public void deleteIntoSupplierView(long supplier_id, long product_id);
    public void deleteIntoSupplier(long supplier_id, long product_id);
    public List<Supplier_View> getSupplierByBuyDate(String[] buy_date);
    public List<Supplier> getAllSupplier();
    public List<Supplier_View> getSupplierByName(String supplier_name);
    public List<Supplier_View> getSupplierInformaton();
    public double getSumOfCost();
    public int getNoofSupplier();
    public int getNoofQuantity();
    public int getNoofProduct();
    public int NoofSupplier();
    public double getSumofCost(String supplier_name);
    public int getNoofSupplier(String supplier_name);
    public int getNoofQuantity(String supplier_name);
    public int getNoofProduct(String supplier_name);
    public Double getSumofCost(String[] buy_date);
    public int getNoofSupplier(String[] buy_date);
    public Integer getNoofQuantity(String[] buy_date);
    public int getNoofProduct(String[] buy_date);
    public boolean createExcel(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response, HttpServletRequest request);
    public boolean createPdf(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response, HttpServletRequest request);
}

