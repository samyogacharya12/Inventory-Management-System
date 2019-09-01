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

import org.apache.commons.collections4.iterators.ReverseListIterator;
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

import com.example.demo.model.Expense;
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
public class ExpenseRepositoryImpl extends JdbcDaoSupport implements ExpenseRepository {

	   @Autowired	
		DataSource datasource;
	    
	    @PostConstruct
	    public void initialize()
	    {
	    	setDataSource(datasource);
	    }
		
	
	@Override
	public List<Expense> getAllExpenseInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense";
		RowMapper<Expense> obj=new BeanPropertyRowMapper<Expense> (Expense.class);
		return this.getJdbcTemplate().query(sql, obj);
	}


	@Override
	public void insertIntoExpenses(Expense expense) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO expense(expense_id, expense_name, cost, expense_date, username) SELECT ?,?,?,?,?";
		getJdbcTemplate().update(sql, new Object[] {expense.getExpenseId(), expense.getExpenseName(), expense.getCost(), expense.getExpenseDate(), expense.getUsername()});
	}


	@Override
	public Expense getExpenseById(long expenseId) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_id=?";
		RowMapper<Expense> expense=new BeanPropertyRowMapper<Expense> (Expense.class);
		return this.getJdbcTemplate().queryForObject(sql, expense, expenseId);
	}


	@Override
	public void updateIntoExpenses(Expense expense) {
		// TODO Auto-generated method stub
		String sql="UPDATE expense SET expense_name=?, cost=?, expense_date=?::Date WHERE expense_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {expense.getExpenseName(), expense.getCost(), expense.getExpenseDate(), expense.getExpenseId()});
	}


	@Override
	public void deleteExpenses(long expenseId) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM expense WHERE expense_id=?";
		this.getJdbcTemplate().update(sql, expenseId);
	}


	@Override
	public List<Expense> getExpenseByExpenseName(String expenseName) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_name=?";
		List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, expenseName);
		List<Expense> expense=new ArrayList<Expense>();
		
		for(Map<String, Object> rows:row)
		{
			Expense obj=new Expense();
			obj.setExpenseId((long)rows.get("expense_id"));
			obj.setExpenseName((String)rows.get("expense_name"));
			obj.setCost((double)rows.get("cost"));
			obj.setExpenseDate((Date)rows.get("expense_date"));
			obj.setUsername((String) rows.get("username"));
			expense.add(obj);
		}
		return expense;
	}


	@Override
	public List<Expense> getExpenseByExpenseDate(String expenseStartDate, String expenseLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		   List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, expenseStartDate, expenseLastDate);
		    List<Expense> expense=new ArrayList<Expense>();
		    for(Map<String, Object> rows:row)
		    {
		    	Expense obj=new Expense();
		    	obj.setExpenseId((long)rows.get("expense_id"));
		    	obj.setExpenseName((String)rows.get("expense_name"));
		    	obj.setCost((double)rows.get("Cost"));
		    	obj.setExpenseDate((Date)rows.get("expense_date"));
		    	obj.setUsername((String) rows.get("username"));
		    expense.add(obj);
		    }
		return expense;
	}


	@Override
	public int countTotalId() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(expense_id) FROM expense";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}


	@Override
	public double totalCost() {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(cost) FROM expense";
		double total=this.getJdbcTemplate().queryForObject(sql, double.class);
		return total;
	}


	@Override
	public int countTotalId(String expenseStartDate, String expenseLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(expense_id) FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expenseStartDate, expenseLastDate);
		return total;
	}


	@Override
	public Double totalCost(String expenseStartDate, String expenseLastDate) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(cost) FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, expenseStartDate, expenseLastDate);
		return total;
	}


	@Override
	public int countTotalId(String expenseName) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(expense_id) FROM expense WHERE expense_name=?";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expenseName);
		return total;
	}


	@Override
	public double totalCost(String expenseName) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(cost) FROM expense WHERE expense_name=?";
		double total=this.getJdbcTemplate().queryForObject(sql, double.class, expenseName);
		return total;
	}
}
