package com.example.demo.repository;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

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
	public void insertintotrash(Product product) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO trash "+" (product_id,product_name,product_type,price,quantity,magnifacture_date,expiry_date, image) SELECT ?,?,?,?,?,?::DATE, ?::DATE, ?";
		this.getJdbcTemplate().update(sql, new Object[] {product.getProduct_id(), product.getProduct_name(), product.getProduct_name(), product.getPrice(), product.getQuantity(), product.getMagnifacture_date(), product.getExpiry_date(), product.getImage()});
		
	}
	@Override
	public List<Trash> getAllTrashInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM trash";
		RowMapper<Trash> rowMapper=new BeanPropertyRowMapper<Trash>(Trash.class);
		return this.getJdbcTemplate().query(sql, rowMapper);
	}

}
