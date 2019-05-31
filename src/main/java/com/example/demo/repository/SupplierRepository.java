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

	public void insertintosupplierproduct(Supplier_Product supplierproduct);
	public void insertintosupplier(Supplier supplier);
    public List<Supplier_View> getAllSupplierInfo();
    public Supplier_View getsupplierbyid(long supplier_id, long product_id);
    public Supplier getsupplierid(long supplier_id);
    public void updateintosupplier(Supplier supplier);
    public void updateintosupplierview(Supplier_Product supplierview);
    public void deleteintosupplierview(long supplier_id, long product_id);
    public void deleteintosupplier(long supplier_id, long product_id);
    public List<Supplier_View> getSupplierbybuydate(String[] buy_date);
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

