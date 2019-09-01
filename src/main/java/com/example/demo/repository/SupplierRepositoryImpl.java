package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import javax.sql.DataSource;

import com.example.demo.model.SupplierProductAnalysis;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Supplier;
import com.example.demo.model.SupplierProduct;
import com.example.demo.model.SupplierView;

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
		String sql="INSERT INTO supplier "+" (supplier_id, supplier_name, supplier_type, permanent_address, temporary_address, email ,image) SELECT ?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplier.getSupplierId(), supplier.getSupplierName(), supplier.getSupplierType(), supplier.getPermanentAddress(), supplier.getTemporaryAddress(),supplier.getEmail() ,supplier.getImage()});
	}


	@Override
	public Supplier getPersonalSupplierByName(String supplierName) {
    	String sql="SELECT * FROM supplier where supplier_name=?";
		return getJdbcTemplate().queryForObject(sql, new Object[]{supplierName}, new RowMapper<Supplier>() {
			@Override
			public Supplier mapRow(ResultSet rs, int rowNum) throws SQLException {
				Supplier supplier=new Supplier();
				supplier.setSupplierId(rs.getLong("supplier_id"));
				supplier.setSupplierName(rs.getString("supplier_name"));
				supplier.setSupplierType(rs.getString("supplier_type"));
				supplier.setPermanentAddress(rs.getString("permanent_address"));
				supplier.setTemporaryAddress(rs.getString("temporary_address"));
				supplier.setImage(rs.getString("image"));
				return supplier;
			}
		});
	}

	@Override
	public void insertIntoSupplierProduct(SupplierProduct supplierproduct) {
		// TODO Auto-generated method stub
	    String sql="INSERT INTO supplier_product "+" (supplier_supplier_id, product_product_id, quantity, cost, buy_date,supplier_unique_id ,username) SELECT ?,?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplierproduct.getSupplierId(), supplierproduct.getProductId(), supplierproduct.getQuantity(), supplierproduct.getCost(), supplierproduct.getBuyDate(), supplierproduct.getSupplierUniqueId(),supplierproduct.getUsername()});
	}

	@Override
	public void insertIntoSupplierProductAnalysis(Map map) {
		String sql="INSERT INTO supplier_product_analysis "+"(supplier_id, product_id, product_name, product_type, past_cost, present_cost, cost_increament, cost_decreament, reference_supplier_id, reference_product_id) SELECT ?,?,?,?,?,?,?,?,?,?";
		getJdbcTemplate().update(sql, new Object[]{map.get("supplierId"), map.get("productId"), map.get("productName"), map.get("productType"), map.get("pastCost"), map.get("presentCost"), map.get("costIncreament"), map.get("costDecreament"), map.get("referenceSupplierId"), map.get("referenceProductId")});
	}

	@Override
	public List<SupplierProductAnalysis> getAllSupplierProductAnalysis() {
		String sql="SELECT * FROM supplier_product_analysis";
		RowMapper<SupplierProductAnalysis> rowMapper=new BeanPropertyRowMapper<>(SupplierProductAnalysis.class);
    	return this.getJdbcTemplate().query(sql, rowMapper);
	}




	@Override
	public List<SupplierView> getAllSupplierInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.permanent_address, s.temporary_address,h.quantity,h.supplier_unique_id ,h.cost,h.buy_date,h.username,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id";
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
	public SupplierView getSupplierBySupplierIdAndUniqueId(long supplierId, long uniqueId) {
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,s.email,h.quantity,h.cost,h.buy_date,h.supplier_unique_id,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_id=? and supplier_unique_id=?";
		RowMapper<SupplierView> rowmapper=new BeanPropertyRowMapper<SupplierView> (SupplierView.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, supplierId, uniqueId);
	}

	@Override
	public void updateIntoSupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String sql="UPDATE supplier SET supplier_name=?,supplier_type=?,permanent_address=?, temporary_address=?,email=?,image=? WHERE supplier_id=?";
	    getJdbcTemplate().update(sql, new Object[] {supplier.getSupplierName(), supplier.getSupplierType(),supplier.getPermanentAddress(), supplier.getTemporaryAddress(),supplier.getEmail(),supplier.getImage(),supplier.getSupplierId()});
	}

	@Override
	public void updateIntoSupplierView(SupplierProduct supplierview) {
		// TODO Auto-generated method stub
	   String sql="UPDATE supplier_product SET product_product_id=?, quantity=?, cost=?, buy_date=? WHERE supplier_supplier_id=?";
	   getJdbcTemplate().update(sql, new Object[] {supplierview.getProductId(),supplierview.getQuantity(),supplierview.getCost(),supplierview.getBuyDate(),supplierview.getSupplierId()});
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
	public List<SupplierView> getSupplierByBuyDate(String supplyStartDate, String supplyLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.image, s.permanent_address, s.temporary_address, h.quantity,h.cost,h.supplier_unique_id,h.buy_date,h.username,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE buy_date>=?::DATE and buy_date<=?::DATE ";
	    List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, supplyStartDate, supplyLastDate);
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
	        obj.setSupplierUniqueId((Integer) rows.get("supplier_unique_id"));
	       obj.setProductId((long)rows.get("product_id"));
	       obj.setProductName((String)rows.get("product_name"));
	       obj.setUsername((String) rows.get("username"));
	       supplier_view.add(obj);
	    }
	    return supplier_view;
	}

	@Override
	public List<SupplierView> getAllSupplierByProductNameAndType(String productName, String productType) {
		String sql="SELECT s.supplier_id, h.cost, i.product_id, i.product_name, i.product_type FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE product_name=? and product_type=? and is_expired='false'";
		List<Map<String, Object>> rows=getJdbcTemplate().queryForList(sql, productName, productType);
		List<SupplierView> supplierViews=new ArrayList<>();
		for(Map<String, Object> row: rows)
		{
			SupplierView supplierView=new SupplierView();
			supplierView.setSupplierId((int)row.get("supplier_id"));
			supplierView.setCost((double)row.get("cost"));
			supplierView.setProductId((long)row.get("product_id"));
			supplierView.setProductName((String) row.get("product_name"));
			supplierView.setProductType((String) row.get("product_type"));
			supplierViews.add(supplierView);
		}
    	return supplierViews;
	}
	@Override
	public List<SupplierProductAnalysis> getSupplierProductAnalysisBySupplierId(Integer supplierId) {
	   String sql="SELECT * FROM supplier_product_analysis WHERE supplier_id=?";
	   List<Map<String, Object>> rows=getJdbcTemplate().queryForList(sql, supplierId);
	   List<SupplierProductAnalysis> supplierProductAnalyses=new ArrayList<>();
	   for(Map<String, Object> row: rows)
	   {
	   	SupplierProductAnalysis supplierProductAnalysis=new SupplierProductAnalysis();
	   	supplierProductAnalysis.setSupplierId((Integer) row.get("supplier_id"));
	   	supplierProductAnalysis.setProductId((Integer) row.get("product_id"));
	   	supplierProductAnalysis.setProductName((String) row.get("product_name"));
	   	supplierProductAnalysis.setPastCost((Double) row.get("past_cost"));
	   	supplierProductAnalysis.setPresentCost((Double) row.get("present_cost"));
	   	supplierProductAnalysis.setCostIncreament((Double)row.get("cost_increament"));
	   	supplierProductAnalysis.setCostDecreament((Double) row.get("cost_decreament"));
	   	supplierProductAnalysis.setReferenceSupplierId((Integer) row.get("reference_supplier_id"));
	   	supplierProductAnalysis.setReferenceProductId((Integer) row.get("reference_product_id"));
	   	supplierProductAnalyses.add(supplierProductAnalysis);
	   }
		return supplierProductAnalyses;
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
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,h.quantity,h.cost,h.buy_date,h.supplier_unique_id,h.username,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_name=?";
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
            obj.setSupplierUniqueId((int)rows.get("supplier_unique_id"));
            obj.setBuyDate((Date)rows.get("buy_date"));
            obj.setProductId((long)rows.get("product_id"));
            obj.setProductName((String)rows.get("product_name"));
            obj.setUsername((String) rows.get("username"));
            supplier_view.add(obj);
		}
		return supplier_view;
	}

	@Override
	public List<SupplierView> getSupplierDistinctName() {
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
	public Double getCostFromExpiredProduct(String supplyStartDate, String supplyLastDate) {
		String sql="SELECT SUM(cost) FROM supplier_product s INNER JOIN product p on s.product_product_id=p.product_id WHERE buy_date>=?::Date and buy_date<=?::Date and p.is_expired='true'";
        Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, supplyStartDate, supplyLastDate);
    	return total;
	}

	@Override
	public Double getSumofCost(String supplyStartDate, String supplyLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.cost) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, supplyStartDate, supplyLastDate);
		return total;
	}

	@Override
	public int getNoofSupplier(String supplyStartDate, String supplyLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.supplier_supplier_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplyStartDate, supplyLastDate);
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
	public Integer getNoofQuantity(String supplyStartDate, String supplyLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplyStartDate, supplyLastDate);
		return total;
	}

	@Override
	public int getNoofProduct(String supplyStartDate, String supplyLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.product_product_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplyStartDate,supplyLastDate);
		return total;
	}
	
	
}