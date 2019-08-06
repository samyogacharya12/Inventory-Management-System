package com.example.demo.repository;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.model.Expense;

public interface ExpenseRepository {

	    List<Expense> getAllExpenseInfo();
	    void insertIntoExpenses(Expense expense);
	    Expense getExpenseById(long expenseId);
	    void updateIntoExpenses(Expense expense);
	    void deleteExpenses(long expenseId);
	    List<Expense> getExpenseByExpenseName(String expenseName);
	    List<Expense> getExpenseByExpenseDate(String[] expenseDate);
	    int countTotalId();
	    double totalCost();
	    int countTotalId(String[] expenseDate);
	    Double totalCost(String[] expenseDate);
	    int countTotalId(String expenseName);
	   double totalCost(String expenseName);
}
