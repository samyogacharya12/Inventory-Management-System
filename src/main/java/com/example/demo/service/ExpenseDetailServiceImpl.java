package com.example.demo.service;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.model.Expense;
import com.example.demo.repository.ExpenseRepository;

@Service
public class ExpenseDetailServiceImpl {

	@Autowired
	private ExpenseRepository expenseRepository;
	
	
	public List<Expense> getAllExpenseInfo()
	{
		return expenseRepository.getAllExpenseInfo();
	}
	
	public void InsertIntoExpenses(Expense expense) {
		
		expenseRepository.InsertIntoExpenses(expense);
	}
	
	
	public Expense getexpensebyid(long expense_id) {
		return expenseRepository.getexpensebyid(expense_id);
	}
	
	
	public void UpdateIntoExpenses(Expense expense) {
		expenseRepository.UpdateIntoExpenses(expense);
	}
	
	public void DeleteExpenses(long expense_id) {
		expenseRepository.DeleteExpenses(expense_id);
	}
	
	public List<Expense> getExpenseByExpenseName(String expense_name) {
		return expenseRepository.getExpenseByExpenseName(expense_name);
	}
	
	
	public List<Expense> getExpenseByExpenseDate(String[] expense_date)
	{
		return expenseRepository.getExpenseByExpenseDate(expense_date);
	}
	
	public int countTotalId() {
		
		return expenseRepository.countTotalId();
	}
	
	public double totalCost() {
		return expenseRepository.totalCost();
	}
	
	public int countTotalId(String[] expense_date) {
		return expenseRepository.countTotalId(expense_date);
	}
	
	public Double totalCost(String[] expense_date) {
		
		return expenseRepository.totalCost(expense_date);
	}
	
	
	public int countTotalId(String expense_name) {
		
		return expenseRepository.countTotalId(expense_name);
	}
	
	public double totalCost(String expense_name) {
	    return expenseRepository.totalCost(expense_name);
	}
	public boolean createPdf(List<Expense> expenses, ServletContext context, HttpServletRequest request, HttpServletResponse response)
	{
	return expenseRepository.createPdf(expenses, context, request, response);
    }
	
	 public boolean createExcel(List<Expense> expenses, ServletContext context, HttpServletResponse response, HttpServletRequest request)
	 {
		 return expenseRepository.createExcel(expenses, context, response, request);
	 }
}