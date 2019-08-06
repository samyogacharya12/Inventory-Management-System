package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseDetailServiceImpl;

@Controller
public class ExpenseController {

	@Autowired
	private ExpenseDetailServiceImpl expenseDetailService;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Autowired
	private ServletContext context;
	
	@GetMapping(value="/list-expense")
	public ModelAndView getExpenseReport(Model model)
	{
		List<Expense> expense=expenseDetailService.getAllExpenseInfo();
		int numberofid=expenseDetailService.countTotalId();
		model.addAttribute("total_id", numberofid);
		double cost=expenseDetailService.totalCost();
		model.addAttribute("cost", cost);
		return new ModelAndView("ExpenseReport.jsp","expense",expense);
	}
	
	@GetMapping(value="/expense-Form")
	public String expenseForm()
	{
		return "expenseForm.jsp";
	}
	
	@PostMapping(value="/save-expense")
	public String saveExpenseData(@ModelAttribute Expense expense)
	{
		Map map=userDetailService.getUserTempData();
		expense.setUsername((String) map.get("username"));
		expenseDetailService.insertIntoExpenses(expense);
		return "redirect:/expenseForm.jsp";
	}
	
	@GetMapping(value="/getExpenseEditForm")
	public ModelAndView getEzpenseEditForm(@RequestParam long expenseId)
	{
		Expense expense=expenseDetailService.getExpenseById(expenseId);
		return new ModelAndView("ExpenseEditForm.jsp", "expenseEdit", expense);
	}
	
	@PostMapping(value="/update-expense")
	public String updateUser(@ModelAttribute Expense expense)
	{
		expenseDetailService.updateIntoExpenses(expense);
		return "redirect:/ExpenseEditForm.jsp";
	}
	
	@GetMapping(value="/delete-eexpense")
	public String deleteuser(@RequestParam long expenseId)
	{
		System.out.println(expenseId);
		expenseDetailService.deleteExpenses(expenseId);
		return "redirect:/expenseForm.jsp";
	}
	
	@GetMapping(value="/getByExpenseName")
	public ModelAndView getExpenseByname(Model model,@RequestParam String expenseName)
	{
		System.out.println(expenseName);
		List<Expense> expense=expenseDetailService.getExpenseByExpenseName(expenseName);
		for(Expense expense1:expense)
		{
			System.out.println(expense1.getExpenseName());
			System.out.println(expense1.getCost());
		}
		int totalnumberofid2=expenseDetailService.countTotalId(expenseName);
		model.addAttribute("totalnumberofid2", totalnumberofid2);
		double cost2=expenseDetailService.totalCost(expenseName);
		model.addAttribute("cost2", cost2);
		return new ModelAndView("ExpenseReport.jsp", "expenseName", expense);
	}
	
	@GetMapping(value="/getExpenseByDate")
	public ModelAndView getDataByDate(Model model,@RequestParam(value="expenseDate[]") String[] expenseDate)
	{
		List<Expense> expense=expenseDetailService.getExpenseByExpenseDate(expenseDate);
		int numberofid1=expenseDetailService.countTotalId(expenseDate);
		model.addAttribute("total_id1", numberofid1);
		Double cost1=expenseDetailService.totalCost(expenseDate);
		if(cost1==null)
		{
			cost1=(double) 0;
			model.addAttribute("cost1", cost1);
		}
		else
		{
			model.addAttribute("cost1", cost1);
		}
		return new ModelAndView("ExpenseReport.jsp", "expenseDate", expense);
	}
	
	@GetMapping(value="/createPdfExpenses")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<Expense> expenses=expenseDetailService.getAllExpenseInfo();
		boolean isflag=expenseDetailService.createPdfForExpenses(expenses, context, request, response);
		if(isflag)
		{
			String fullpath=request.getServletContext().getRealPath("/resources/reports/"+"expenses"+".pdf");
			filedownload(fullpath, response, "expenses.pdf");
		}
	}
	
	@GetMapping(value="/createExcelExpenses")
	public void createExcel(HttpServletRequest request, HttpServletResponse response)
	{
		List<Expense> expenses=expenseDetailService.getAllExpenseInfo();
		boolean isflag=expenseDetailService.createExcelForExpenses(expenses, context, response, request);
		if(isflag)
		{
			String fullpath=request.getServletContext().getRealPath("/resources/reports/"+"expenses"+".xls");
			filedownload(fullpath, response, "expenses.xls");
		}
	}
	
	private void filedownload(String fullpath, HttpServletResponse response, String filename) {
		// TODO Auto-generated method stub
		File file=new File(fullpath);
		final int BUFFER_SIZE=4096;
		if(file.exists())
		{
			try {
				FileInputStream inputstream=new FileInputStream(file);
				String mimeType=context.getMimeType(fullpath);
				response.setContentType(mimeType);
				response.setHeader("content-disposition", "attachement; filename="+filename);
				OutputStream outputStream=response.getOutputStream();
				byte[] buffer=new byte[BUFFER_SIZE];
				int bytesread=-1;
				while((bytesread=inputstream.read(buffer))!=-1)
	{
		  outputStream.write(buffer,0, bytesread);
	}
				inputstream.close();
				outputStream.close();
				file.delete();				
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}

}
