package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
	private ServletContext context;
	
	@GetMapping(value="/get_expenses")
	public ModelAndView getExpenseReport(Model model)
	{
		List<Expense> expense=expenseDetailService.getAllExpenseInfo();
		int numberofid=expenseDetailService.countTotalId();
		model.addAttribute("total_id", numberofid);
		double cost=expenseDetailService.totalCost();
		model.addAttribute("cost", cost);
		return new ModelAndView("ExpenseReport.jsp","expense",expense);
	}
	
	@GetMapping(value="/expense_Form")
	public String expenseForm()
	{
		return "expenseForm.jsp";
	}
	
	@PostMapping(value="/save_expense")
	public String saveExpenseData(@ModelAttribute Expense expense)
	{
		expenseDetailService.InsertIntoExpenses(expense);
		return "redirect:/expenseForm.jsp";
	}
	
	@GetMapping(value="/expense")
	public ModelAndView getUserEditForm(@RequestParam long expense_id)
	{
		Expense expense=expenseDetailService.getexpensebyid(expense_id);
		return new ModelAndView("ExpenseEditForm.jsp", "expenseEdit", expense);
	}
	
	@PostMapping(value="/update_expense")
	public String updateUser(@ModelAttribute Expense expense)
	{
		expenseDetailService.UpdateIntoExpenses(expense);
		return "redirect:/ExpenseEditForm.jsp";
	}
	
	@GetMapping(value="/deleteexpense")
	public String deleteuser(@RequestParam long expense_id)
	{
		System.out.println(expense_id);
		expenseDetailService.DeleteExpenses(expense_id);
		return "redirect:/expenseForm.jsp";
	}
	
	@GetMapping(value="/getByExpenseName")
	public ModelAndView getExpenseByname(Model model,@RequestParam String expense_name)
	{
		System.out.println(expense_name);
		List<Expense> expense=expenseDetailService.getExpenseByExpenseName(expense_name);
		int totalnumberofid2=expenseDetailService.countTotalId(expense_name);
		model.addAttribute("totalnumberofid2", totalnumberofid2);
		double cost2=expenseDetailService.totalCost(expense_name);
		model.addAttribute("cost2", cost2);
		return new ModelAndView("ExpenseReport.jsp", "expense_name", expense);
	}
	
	@GetMapping(value="/getDataByDate")
	public ModelAndView getDataByDate(Model model,@RequestParam(value="expense_date[]") String[] expense_date)
	{
		List<Expense> expense=expenseDetailService.getExpenseByExpenseDate(expense_date);
		int numberofid1=expenseDetailService.countTotalId(expense_date);
		model.addAttribute("total_id1", numberofid1);
		Double cost1=expenseDetailService.totalCost(expense_date);
		if(cost1==null)
		{
			cost1=(double) 0;
			model.addAttribute("cost1", cost1);
		}
		else
		{
			model.addAttribute("cost1", cost1);
		}
		return new ModelAndView("ExpenseReport.jsp", "expense_date", expense);
	}
	
	@GetMapping(value="/createPdfExpenses")
	public void createPdf(HttpServletRequest request, HttpServletResponse response)
	{
		List<Expense> expenses=expenseDetailService.getAllExpenseInfo();
		boolean isflag=expenseDetailService.createPdf(expenses, context, request, response);
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
		boolean isflag=expenseDetailService.createExcel(expenses, context, response, request);
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
