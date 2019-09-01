package com.example.demo.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;
import java.util.*;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import com.example.demo.model.LogRecord;
import com.example.demo.model.ServiceResponse;
import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.model.Expense;
import com.example.demo.service.ExpenseDetailServiceImpl;

@RestController
public class ExpenseRestController {

	@Autowired
	private ExpenseDetailServiceImpl expenseDetailService;

	@Autowired
	private UserDetailServiceImpl userDetailService;

	@Autowired
	private ServletContext context;


	@GetMapping(value = "/getListExpenses")
	public List<Map> getExpenseList()
	{
		List<Map> mapList=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		List<Expense> expenseList=expenseDetailService.getAllExpenseInfo();
		Integer numberOfExpenses=expenseDetailService.countTotalId();
		Double totalCost=expenseDetailService.totalCost();
		map.put("numberOfExpenses", numberOfExpenses);
		map.put("expenseList", expenseList);
		map.put("totalCost", totalCost);
		mapList.add(map);
		return  mapList;
	}



	@PostMapping(value = "/save-expense")
	public ResponseEntity saveExpenseData(@Valid @RequestBody Expense expense)
	{
		Date logoutTime=new Date(System.currentTimeMillis());
		List<LogRecord> logRecords=userDetailService.getListLogRecord(logoutTime);
		for(LogRecord logRecord: logRecords)
		{
			expense.setUsername(logRecord.getUserName());
		}
		expenseDetailService.insertIntoExpenses(expense);
		ServiceResponse serviceResponse=new ServiceResponse("success", expense);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@PostMapping(value="/update-expense")
    public ResponseEntity updateExpense(@Valid @RequestBody Expense expense)
	{
		expenseDetailService.updateIntoExpenses(expense);
		ServiceResponse serviceResponse=new ServiceResponse("success", expense);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}




	@GetMapping(value="/delete-expense/{expenseId}")
	public ResponseEntity deleteExpense(@PathVariable long expenseId)
	{
		expenseDetailService.deleteExpenses(expenseId);
		ServiceResponse serviceResponse=new ServiceResponse("success", expenseId);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}


	@GetMapping(value="/getByExpenseName/{expenseName}")
   public List<Map> getExpenseByName(@PathVariable String expenseName)
   {
   	 List<Map> expenseList=new ArrayList<>();
   	 Map<String, Object> map=new HashMap();
   	 List<Expense> expenseByExpenseName=expenseDetailService.getExpenseByExpenseName(expenseName);
   	 Integer numberOfExpense=expenseDetailService.countTotalId(expenseName);
   	 Double totalCost=expenseDetailService.totalCost(expenseName);
   	 map.put("expenseByExpenseName", expenseByExpenseName);
   	 map.put("numberOfExpense", numberOfExpense);
   	 map.put("totalCost", totalCost);
   	 expenseList.add(map);
   	 return expenseList;
   }


	@GetMapping(value="/getExpenseEditForm/{expenseId}")
	public ResponseEntity<Expense> getExpenseEditForm(@PathVariable long expenseId)
	{
		Expense expense=expenseDetailService.getExpenseById(expenseId);
		ServiceResponse serviceResponse=new ServiceResponse("success", expense);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}




	@GetMapping(value="/getExpenseDataByDate/{expenseStartDate}/{expenseLastDate}")
    public List<Map> getDataByExpenseDate(@PathVariable String expenseStartDate, @PathVariable String expenseLastDate)
	{
		List<Map> mapList=new ArrayList<>();
		Map<String, Object> map=new HashMap<>();
		List<Expense> expenseByExpenseDate=expenseDetailService.getExpenseByExpenseDate(expenseStartDate, expenseLastDate);
	    Integer numberOfExpense=expenseDetailService.countTotalId(expenseStartDate, expenseLastDate);
	    Double totalCost=expenseDetailService.totalCost(expenseStartDate, expenseLastDate);
	    if(totalCost==null)
		{
			totalCost=(double)0;
			map.put("totalCost", totalCost);
		}
	    map.put("expenseByExpenseDate", expenseByExpenseDate);
	    map.put("numberOfExpense", numberOfExpense);
	    map.put("totalCost", totalCost);
	    mapList.add(map);
	    return mapList;
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
