package com.example.demo.model;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

public class Expense {

	private long expense_id;
	private String expense_name;
	private double cost;
	@DateTimeFormat(pattern="yyyy-MM-dd")
	private Date expense_date;
	public long getExpense_id() {
		return expense_id;
	}
	public void setExpense_id(long expense_id) {
		this.expense_id = expense_id;
	}
	public String getExpense_name() {
		return expense_name;
	}
	public void setExpense_name(String expense_name) {
		this.expense_name = expense_name;
	}
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}
	public Date getExpense_date() {
		return expense_date;
	}
	public void setExpense_date(Date expense_date) {
		this.expense_date = expense_date;
	}
	
	
}
