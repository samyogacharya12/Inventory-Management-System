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

	@Autowired
	private ExcelService excelService;

	@Autowired
	private PdfService pdfService;

	public List<Expense> getAllExpenseInfo()
	{
		return expenseRepository.getAllExpenseInfo();
	}
	
	public void insertIntoExpenses(Expense expense) {
		
		expenseRepository.insertIntoExpenses(expense);
	}
	
	
	public Expense getExpenseById(long expense_id) {
		return expenseRepository.getExpenseById(expense_id);
	}
	
	
	public void updateIntoExpenses(Expense expense) {
		expenseRepository.updateIntoExpenses(expense);
	}
	
	public void deleteExpenses(long expense_id) {
		expenseRepository.deleteExpenses(expense_id);
	}
	
	public List<Expense> getExpenseByExpenseName(String expense_name) {
		return expenseRepository.getExpenseByExpenseName(expense_name);
	}
	
	
	public List<Expense> getExpenseByExpenseDate(String expenseStartDate, String expenseLastDate)
	{
		return expenseRepository.getExpenseByExpenseDate(expenseStartDate, expenseLastDate);
	}
	
	public int countTotalId() {
		
		return expenseRepository.countTotalId();
	}
	
	public double totalCost() {
		return expenseRepository.totalCost();
	}
	
	public int countTotalId(String expenseStartDate, String expenseLastDate) {
		return expenseRepository.countTotalId( expenseStartDate, expenseLastDate);
	}
	
	public Double totalCost(String expenseStartDate, String expenseLastDate) {
		
		return expenseRepository.totalCost(expenseStartDate, expenseLastDate);
	}
	
	
	public int countTotalId(String expense_name) {
		
		return expenseRepository.countTotalId(expense_name);
	}
	
	public double totalCost(String expense_name) {
	    return expenseRepository.totalCost(expense_name);
	}
	public boolean createPdfForExpenses(List<Expense> expenses, ServletContext context, HttpServletRequest request, HttpServletResponse response)
	{
		return pdfService.createPdfForExpenses(expenses, context, request, response);
    }
	
	 public boolean createExcelForExpenses(List<Expense> expenses, ServletContext context, HttpServletResponse response, HttpServletRequest request)
	 {
	 	return excelService.createExcelForExpenses(expenses, context, response, request);
	 }
}