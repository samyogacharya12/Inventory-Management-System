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

import com.example.demo.model.CustomerProduct;
import com.example.demo.model.CustomerView;
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

import com.example.demo.model.Customer;
import com.example.demo.model.CustomerView;
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
public class CustomerRepositoryImpl extends JdbcDaoSupport implements CustomerRepository {

	 @Autowired	
		DataSource datasource;
	    
	    @PostConstruct
	    public void initialize()
	    {
	    	setDataSource(datasource);
	    }
	
	@Override
	public List<CustomerView> getAllCustomer() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM customer";
		RowMapper<CustomerView> rowmapper=new BeanPropertyRowMapper<CustomerView> (CustomerView.class);
		return this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public List<CustomerView> getAllCustomerInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, c.country,h.quantity,h.buy_date,h.amount,h.username,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<CustomerView> rowmapper=new BeanPropertyRowMapper<CustomerView> (CustomerView.class);
		return  this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public void insertIntoCustomer(Customer customer) {
		// TODO Auto-generated method stub
	String sql="INSERT INTO customer "+"(customer_id, customer_name, permanent_address, temporary_address, phone_number, country) SELECT ?,?,?,?,?,?";
	getJdbcTemplate().update(sql, new Object[] {customer.getCustomerId(), customer.getCustomerName(), customer.getTemporaryAddress(), customer.getPermanentAddress(), customer.getPhoneNumber(), customer.getCountry()});
	}
	@Override
	public void insertIntoCustomerProduct(CustomerProduct customerProduct) {
		// TODO Auto-generated method stub
	String sql="INSERT INTO customer_product "+"(customer_customer_id, product_product_id, quantity, buy_date ,amount, username) SELECT ?,?,?,?,?,?";
	getJdbcTemplate().update(sql, new Object[] {customerProduct.getCustomerId(), customerProduct.getProductId(), customerProduct.getQuantity(),customerProduct.getBuyDate(),customerProduct.getAmount(), customerProduct.getUsername()});
	}

	@Override
	public CustomerProduct getCustomerById(long customerId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM customer_product WHERE customer_customer_id=?";
		RowMapper<CustomerProduct> rowmapper=new BeanPropertyRowMapper<CustomerProduct>(CustomerProduct.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, customerId);
	}



	@Override
	public CustomerView getCustomerId(long customerId, long productId) {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, c.country,c.email,h.customer_purchase_id,h.customer_customer_id,h.product_product_id,h.quantity,h.amount,h.buy_date,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id WHERE customer_id=? and product_id=?";
		RowMapper<CustomerView> rowmapper=new BeanPropertyRowMapper<CustomerView>(CustomerView.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, customerId, productId);
	}

	@Override
	public CustomerView getDataByCustomerId(long customerId, long customerPurchaseId) {
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, c.country,c.email,h.customer_customer_id,h.product_product_id,h.quantity,h.amount,h.buy_date,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id WHERE customer_id=? and customer_purchase_id=?";
		RowMapper<CustomerView> rowmapper=new BeanPropertyRowMapper<CustomerView>(CustomerView.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, customerId, customerPurchaseId);
	}

	@Override
	public List<CustomerView> getCustomerByBuyDate(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number,c.country, h.customer_customer_id, h.product_product_id, h.quantity, h.amount,h.buy_date, h.username ,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id WHERE buy_date>=?::Date and buy_date<=?::Date";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, buyDate);
        List<CustomerView> customerViews=new ArrayList<CustomerView>();
        for(Map<String, Object> rows:row)
        {
        	CustomerView obj=new CustomerView();
        	obj.setCustomerId((long)rows.get("customer_id"));
        	obj.setCustomerName((String)rows.get("customer_name"));
        	obj.setPermanentAddress((String)rows.get("permanent_address"));
        	obj.setTemporaryAddress((String)rows.get("temporary_address"));
        	obj.setPhoneNumber((int)rows.get("phone_number"));
        	obj.setCountry((String)rows.get("country"));
        	obj.setBuyDate((Date)rows.get("buyDate"));
        	obj.setCustomerId((long)rows.get("customer_customer_id"));
        	obj.setProductId((long)rows.get("product_product_id"));
        	obj.setQuantity((int)rows.get("quantity"));
        	obj.setAmount((double)rows.get("amount"));
        	obj.setProductId((long)rows.get("product_id"));
        	obj.setProductName((String)rows.get("product_name"));
        	obj.setUsername((String) rows.get("username"));
        	customerViews.add(obj);
        }
        return customerViews;
	}



	@Override
	public List<CustomerView> getCustomerByName(String customerName) {
		// TODO Auto-generated method stub
	  String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, h.customer_customer_id, h.product_product_id, h.quantity, h.amount,  h.username  ,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id =i.product_id WHERE customer_name=?";
	  List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, customerName);
	  List<CustomerView> customer_view=new ArrayList<CustomerView>();
	  for(Map<String, Object> rows:row)
	  {
		  CustomerView obj=new CustomerView();
      	obj.setCustomerId((long)rows.get("customer_id"));
      	obj.setCustomerName((String)rows.get("customerName"));
      	obj.setPermanentAddress((String)rows.get("permanent_address"));
      	obj.setTemporaryAddress((String)rows.get("temporary_address"));
      	obj.setPhoneNumber((int)rows.get("phone_number"));
      	obj.setCountry((String)rows.get("country"));
      	obj.setBuyDate((Date)rows.get("buyDate"));
      	obj.setCustomerId((long)rows.get("customer_customer_id"));
      	obj.setProductId((long)rows.get("product_product_id"));
      	obj.setQuantity((int)rows.get("quantity"));
      	obj.setAmount((double)rows.get("amount"));
      	obj.setProductId((long)rows.get("product_id"));
      	obj.setProductName((String)rows.get("product_name"));
      	obj.setUsername((String) rows.get("username"));
      	customer_view.add(obj);
	  }
		return customer_view;
	}

	@Override
	public void updateIntoPersonalCustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql="UPDATE customer SET customer_name=?, permanent_address=?, temporary_address=?, phone_number=?, country=? WHERE customer_id=?";
		getJdbcTemplate().update(sql, new Object[] {customer.getCustomerName(), customer.getPermanentAddress(), customer.getTemporaryAddress(), customer.getPhoneNumber(), customer.getCountry(), customer.getCustomerId()});
	}


	@Override
	public void updateIntoCustomerProduct(CustomerView customerProduct) {
		// TODO Auto-generated method stub
        String sql="UPDATE customer_product SET  quantity=?, buy_date=?, amount=? WHERE customer_customer_id=? and product_product_id=?";
        getJdbcTemplate().update(sql, new Object[] {customerProduct.getQuantity(), customerProduct.getBuyDate(), customerProduct.getAmount(), customerProduct.getCustomerId(), customerProduct.getProductId()});
	}

	@Override
	public void deleteIntoCustomerView(long customerId, long productId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM customer_product WHERE customer_customer_id=? and product_product_id=?";
		getJdbcTemplate().update(sql, customerId, productId);
	}

	@Override
	public void deleteIntoCustomer(long customerId, long productId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM customer WHERE customer_id IN(SELECT B.customer_customer_id FROM customer_product B INNER JOIN product p ON B.product_product_id=p.product_id WHERE customer_id=? and b.product_product_id=?)";
		getJdbcTemplate().update(sql, customerId, productId);
	}


	@Override
	public int getTotalCustomer() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(customer_customer_id) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public List<Integer> getProductIdNotEqualToCustomerId(long customerId) {
	    String sql="SELECT DISTINCT(product_product_id) FROM customer_product WHERE customer_customer_id!=?";
	    return  this.getJdbcTemplate().queryForList(sql, new Object[] {customerId}, Integer.class);

	}

	@Override
	public int sumOfQuantity() {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(quantity) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public double sumOfAmount() {
		// TODO Auto-generated method stub
	  String sql="SELECT SUM(amount) FROM customer_product";
	  double total=this.getJdbcTemplate().queryForObject(sql, Double.class);
		return total;
	}

	@Override
	public int getTotalProduct() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_product_id) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public int getTotalCustomer(String customerName) {
		// TODO Auto-generated method stub
		  String sql="SELECT  COUNT(h.customer_customer_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customerName);
		  return total;
	}

	@Override
	public int sumOfQuantity(String customerName) {
		// TODO Auto-generated method stub
		  String sql="SELECT SUM(h.quantity) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customerName);
		  return total;
	}

	@Override
	public double sumOfAmount(String customerName) {
		// TODO Auto-generated method stub
		 String sql="SELECT SUM(h.amount) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		 double total=this.getJdbcTemplate().queryForObject(sql, Double.class, customerName);
		 return total;
	}

	@Override
	public int getTotalProduct(String customerName) {
		// TODO Auto-generated method stub
		 String sql="SELECT COUNT(h.product_product_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		 int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customerName);
		 return total;
	}

	@Override
	public int getTotalCustomer(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.customer_customer_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public Integer sumOfQuantity(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public Double sumOfAmount(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(amount) FROM customer_product  WHERE buy_date>=?::Date and buy_date<=?::Date";
        Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buyDate);
		return total;
	}

	@Override
	public int getTotalProduct(String[] buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.product_product_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public int getPresentDate(String buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(quantity) FROM Customer_Product WHERE buy_date=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	  public int getNumberofCustomersToday(String buyDate){
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(customer_customer_id) FROM customer_product WHERE buy_date=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buyDate);
		return total;
	}

	@Override
	public Double getPresentRevenue(String buyDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(amount) FROM customer_product WHERE buy_date=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buyDate);
		return total;
	}
}
