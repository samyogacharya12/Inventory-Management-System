package com.example.demo.repository;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Expense;
import com.example.demo.model.Supplier;
import com.example.demo.model.Supplier_View;

public interface ExpenseRepository {

	   public List<Expense> getAllExpenseInfo();
	   public void InsertIntoExpenses(Expense expense);
	   public Expense getexpensebyid(long expense_id);
	   public void UpdateIntoExpenses(Expense expense);
	   public void DeleteExpenses(long expense_id);
	   public List<Expense> getExpenseByExpenseName(String expense_name);
	   public List<Expense> getExpenseByExpenseDate(String[] expense_date);
	   public int countTotalId();
	   public double totalCost();
	   public int countTotalId(String[] expense_date);
	   public Double totalCost(String[] expense_date);
	   public int countTotalId(String expense_name);
	   public double totalCost(String expense_name);
	   public boolean createExcel(List<Expense> expenses, ServletContext context, HttpServletResponse response, HttpServletRequest request);
	   public boolean createPdf(List<Expense> expenses, ServletContext context, HttpServletRequest request, HttpServletResponse response);
}
