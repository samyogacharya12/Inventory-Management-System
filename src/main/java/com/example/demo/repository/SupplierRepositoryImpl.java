package com.example.demo.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Supplier;
import com.example.demo.model.SupplierProduct;
import com.example.demo.model.SupplierView;
import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

@Repository
public class SupplierRepositoryImpl extends JdbcDaoSupport implements SupplierRepository {

    @Autowired	
	DataSource datasource;
    
    @PostConstruct
    public void initialize()
    {
    	setDataSource(datasource);
    }
	
	@Override
	public void insertIntoSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO supplier "+" (supplier_id, supplier_name, supplier_type, permanent_address, temporary_address, image) SELECT ?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierType(), supplier.getPermanentAddress(), supplier.getTemporaryAddress(), supplier.getImage()});
	}
	
	@Override
	public void insertIntoSupplierProduct(SupplierProduct supplierproduct) {
		// TODO Auto-generated method stub
	    String sql="INSERT INTO supplier_product "+" (supplier_supplier_id, product_product_id, quantity, cost, buy_date, username) SELECT ?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplierproduct.getSupplierId(), supplierproduct.getProductId(), supplierproduct.getQuantity(), supplierproduct.getCost(), supplierproduct.getBuyDate(), supplierproduct.getUsername()});
	}
	

	@Override
	public List<SupplierView> getAllSupplierInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.permanent_address, s.temporary_address,h.quantity, h.cost,h.buy_date,h.username,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<SupplierView> rowmapper=new BeanPropertyRowMapper<SupplierView> (SupplierView.class);
		return this.getJdbcTemplate().query(sql,rowmapper);
	}

	@Override
	public SupplierView getSupplierBySupplierIdAndProductId(long supplierId, long productId) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,h.quantity,h.cost,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_id=? and product_id=?";
		RowMapper<SupplierView> rowmapper=new BeanPropertyRowMapper<SupplierView> (SupplierView.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, supplierId, productId);
	}

	@Override
	public void updateIntoSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String sql="UPDATE supplier SET supplier_name=?,supplier_type=?,permanent_address=?, temporary_address=?,image=? WHERE supplier_id=?";
	    getJdbcTemplate().update(sql, new Object[] {supplier.getSupplierName(), supplier.getSupplierType(),supplier.getPermanentAddress(), supplier.getTemporaryAddress(),supplier.getImage(),supplier.getSupplierId()});
	}

	@Override
	public void updateIntoSupplierView(SupplierProduct supplierview) {
		// TODO Auto-generated method stub
	   String sql="UPDATE supplier_product SET product_product_id=?, quantity=?, cost=? WHERE supplier_supplier_id=?";
	   getJdbcTemplate().update(sql, new Object[] {supplierview.getProductId(),supplierview.getQuantity(),supplierview.getCost(),supplierview.getSupplierId()});
	}
	

	@Override
	public void deleteIntoSupplierView(long supplierId, long productId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM supplier_product WHERE supplier_supplier_id=? AND product_product_id=?";
		getJdbcTemplate().update(sql, supplierId, productId);
	}

	@Override
	public void deleteIntoSupplier(long supplierId, long productId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM supplier WHERE supplier_id IN(SELECT B.supplier_supplier_id FROM supplier_product B INNER JOIN product p ON B.product_product_id=p.product_id WHERE supplier_id=? AND B.product_product_id=?)";
	    getJdbcTemplate().update(sql, supplierId, productId);
	}

	@Override
	public Supplier getSupplierId(long supplierId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM supplier WHERE supplier_id=?";
		RowMapper<Supplier> rowmapper=new BeanPropertyRowMapper<Supplier> (Supplier.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, supplierId);
	}

	@Override
	public List<SupplierView> getSupplierByBuyDate(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.image, s.permanent_address, s.temporary_address, h.quantity,h.cost,h.buy_date,h.username,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE buy_date>=?::DATE and buy_date<=?::DATE ";
	    List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, buyDate);
	    List<SupplierView> supplier_view=new ArrayList<SupplierView>();
	    for(Map<String, Object> rows:row)
	    {
	    	SupplierView obj=new SupplierView();
	    	obj.setSupplierId((int)rows.get("supplier_id"));
	    	System.out.println(obj.getSupplierId());
	    	obj.setSupplierName((String)rows.get("supplier_name"));
	    	System.out.println(obj.getSupplierName());
	    	obj.setSupplierType((String)rows.get("supplier_type"));
	        obj.setImage((String)rows.get("image"));
	        obj.setPermanentAddress((String)rows.get("permanent_address"));
	        obj.setTemporaryAddress((String)rows.get("temporary_address"));
	        obj.setQuantity((int)rows.get("quantity"));
	        obj.setCost((double)rows.get("cost"));
	        obj.setBuyDate((Date)rows.get("buy_date"));
	       obj.setProductId((long)rows.get("product_id"));
	       obj.setProductName((String)rows.get("product_name"));
	       obj.setUsername((String) rows.get("username"));
	       supplier_view.add(obj);
	    }
	    return supplier_view;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM supplier";
		RowMapper<Supplier> rowmapper=new BeanPropertyRowMapper<Supplier> (Supplier.class);
		return this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public List<SupplierView> getSupplierByName(String supplierName) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,h.quantity,h.cost,h.buy_date,h.username,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_name=?";
		List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, supplierName);
		List<SupplierView> supplier_view=new ArrayList<SupplierView>();
		for(Map<String, Object> rows:row)
		{
			SupplierView obj=new SupplierView();
			obj.setSupplierId((int)rows.get("supplier_id"));
			obj.setSupplierName((String)rows.get("supplier_name"));
			obj.setSupplierType((String)rows.get("supplier_type"));
			obj.setImage((String)rows.get("image"));
			obj.setPermanentAddress((String)rows.get("permanent_address"));
			obj.setTemporaryAddress((String)rows.get("temporary_address"));
			obj.setQuantity((int)rows.get("quantity"));
            obj.setCost((double)rows.get("cost"));
            obj.setBuyDate((Date)rows.get("buyDate"));
            obj.setProductId((long)rows.get("product_id"));
            obj.setProductName((String)rows.get("product_name"));
            obj.setUsername((String) rows.get("username"));
            supplier_view.add(obj);
		}
		return supplier_view;
	}

	@Override
	public List<SupplierView> getSupplierInformaton() {
		// TODO Auto-generated method stub
		String sql="SELECT DISTINCT(s.supplier_name) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<SupplierView> rowmapper=new BeanPropertyRowMapper<SupplierView> (SupplierView.class);
		return  this.getJdbcTemplate().query(sql,rowmapper);
	}
	
	
	@Override
	public double getSumOfCost() {
		// TODO Auto-generated method stub
	  String sql="SELECT SUM(cost) FROM supplier_product";
	  double total=this.getJdbcTemplate().queryForObject(sql, double.class);
		return total;
	}
	
	@Override
	public int getNoofSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(supplier_supplier_id) FROM supplier_product";
	    int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
	
	@Override
	public int getNoofQuantity() {
		// TODO Auto-generated method stub
	   String sql="SELECT SUM(quantity) FROM supplier_product";
	   int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}


	@Override
	public int getNoofProduct() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_product_id) FROM supplier_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}	

	@Override
	public double getSumofCost(String supplierName) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.cost) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
		double total=this.getJdbcTemplate().queryForObject(sql, Double.class, supplierName);
		return total;
	}

	@Override
	public int getNoofSupplier(String supplierName) {
		// TODO Auto-generated method stub
		 String sql="SELECT COUNT(h.supplier_supplier_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplierName);
		return total;
	}

	@Override
	public int getNoofQuantity(String supplierName) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplierName);
		return total;
	}

	@Override
	public int getNoofProduct(String supplierName) {
		// TODO Auto-generated method stub
	  String sql="SELECT COUNT(h.product_product_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
	  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplierName);
	  return total;
	}

	@Override
	public Double getCostFromExpiredProduct(String[] buyDate) {
		String sql="SELECT SUM(cost) FROM supplier_product s INNER JOIN product p on s.product_product_id=p.product_id WHERE buy_date>=?::Date and buy_date<=?::Date and p.is_expired='true'";
        Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buyDate);
    	return total;
	}

	@Override
	public Double getSumofCost(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.cost) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buyDate);
		return total;
	}

	@Override
	public int getNoofSupplier(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.supplier_supplier_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public int NoofSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(supplier_id) FROM supplier";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
	
	
	
	@Override
	public Integer getNoofQuantity(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public int getNoofProduct(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.product_product_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}
	
	
}