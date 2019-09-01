package com.example.demo.repository;

import java.util.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.apache.poi.ss.formula.functions.T;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Product;
import com.example.demo.model.Trash;

@Repository
public class TrashRepositoryImpl extends JdbcDaoSupport implements TrashRepository {

	
	  @Autowired	
	DataSource datasource;
	
	   @PostConstruct
	    public void initialize()
	    {
	    	setDataSource(datasource);
	    }  
	   
	@Override
	public void insertIntoTrash(Product product) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO trash "+" (product_id,product_name,product_type,price,quantity,magnifacture_date,expiry_date, image) SELECT ?,?,?,?,?,?::DATE, ?::DATE, ?";
		this.getJdbcTemplate().update(sql, new Object[] {product.getProductId(), product.getProductName(), product.getProductName(), product.getPrice(), product.getQuantity(), product.getMagnifactureDate(), product.getExpiryDate(), product.getImage()});
		
	}
	@Override
	public List<Trash> getAllTrashInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM trash";
		RowMapper<Trash> rowMapper=new BeanPropertyRowMapper<Trash>(Trash.class);
		return this.getJdbcTemplate().query(sql, rowMapper);
	}

	@Override
	public List<Trash> getTrashByProductName(String productName) {
		String sql="SELECT * FROM trash WHERE product_name=?";
		List<Map<String, Object>> rows=getJdbcTemplate().queryForList(sql, productName);
		List<Trash> trashList=new ArrayList<>();
		for(Map<String, Object> row: rows)
		{
			Trash trash=new Trash();
			trash.setTrashId((Integer) row.get("trash_id"));
			trash.setProductId((Long) row.get("product_id"));
			trash.setProductName((String) row.get("product_name"));
			trash.setProductType((String) row.get("product_type"));
			trash.setPrice((Double) row.get("price"));
			trash.setQuantity((Integer) row.get("quantity"));
			trash.setMagnifactureDate((Date) row.get("magnifacture_date"));
			trash.setExpiryDate((Date) row.get("expiry_date"));
			trashList.add(trash);
		}
	   	return trashList;
	}

}
