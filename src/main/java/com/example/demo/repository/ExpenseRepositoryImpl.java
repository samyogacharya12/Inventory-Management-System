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
import com.example.demo.model.Supplier_View;
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
	public void InsertIntoExpenses(Expense expense) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO expense(expense_id, expense_name, cost, expense_date) SELECT ?,?,?,?";
		getJdbcTemplate().update(sql, new Object[] {expense.getExpense_id(), expense.getExpense_name(), expense.getCost(), expense.getExpense_date()});
	}


	@Override
	public Expense getexpensebyid(long expense_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_id=?";
		RowMapper<Expense> expense=new BeanPropertyRowMapper<Expense> (Expense.class);
		return this.getJdbcTemplate().queryForObject(sql, expense, expense_id);
	}


	@Override
	public void UpdateIntoExpenses(Expense expense) {
		// TODO Auto-generated method stub
		String sql="UPDATE expense SET expense_name=?, cost=?, expense_date=?::Date WHERE expense_id=?";
		this.getJdbcTemplate().update(sql, new Object[] {expense.getExpense_name(), expense.getCost(), expense.getExpense_date(), expense.getExpense_id()});
	}


	@Override
	public void DeleteExpenses(long expense_id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM expense WHERE expense_id=?";
		this.getJdbcTemplate().update(sql, expense_id);
	}


	@Override
	public List<Expense> getExpenseByExpenseName(String expense_name) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_name=?";
		List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, expense_name);
		List<Expense> expense=new ArrayList<Expense>();
		
		for(Map<String, Object> rows:row)
		{
			Expense obj=new Expense();
			obj.setExpense_id((long)rows.get("expense_id"));
			obj.setExpense_name((String)rows.get("expense_name"));
			obj.setCost((double)rows.get("cost"));
			obj.setExpense_date((Date)rows.get("expense_date"));
			expense.add(obj);
		}
		return expense;
	}


	@Override
	public boolean createPdf(List<Expense> expenses, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
		// TODO Auto-generated method stub
		Document document=new Document(PageSize.A4, 15, 15, 45, 30);
		try
		{
			String filepath=context.getRealPath("/resources/reports");
			File file=new File(filepath);
			boolean exists=new File(filepath).exists();
			if(!exists)
			{
				new File(filepath).mkdirs();
			}
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"expenses"+".pdf"));
		    document.open();
		    
		    Font mainfont=FontFactory.getFont("Arial", 10, BaseColor.BLACK);
		    
		    Paragraph paragraph=new Paragraph("All Expenses", mainfont);
		    paragraph.setAlignment(Element.ALIGN_CENTER);
		    paragraph.setIndentationLeft(50);
		    paragraph.setIndentationRight(50);
		    paragraph.setSpacingAfter(10);
		    document.add(paragraph);
		    
		    PdfPTable table=new PdfPTable(4);
		    table.setWidthPercentage(100);
		    table.setSpacingBefore(10f);
		    table.setSpacingAfter(10);
		    
		    Font tableHeader=FontFactory.getFont("Arial",10,BaseColor.BLACK);
		    Font tableBody=FontFactory.getFont("Arial",9,BaseColor.BLACK);
		    
		    float[] columnWidths= {2f, 2f, 2f, 2f};
		    table.setWidths(columnWidths);
		    
		    
		    PdfPCell expense_id=new PdfPCell(new Paragraph("expense_id", tableHeader));
		    expense_id.setBorderColor(BaseColor.BLACK);
		    expense_id.setPaddingLeft(10);
		    expense_id.setPaddingRight(10);
		    expense_id.setHorizontalAlignment(Element.ALIGN_CENTER);
		    expense_id.setVerticalAlignment(Element.ALIGN_CENTER);
		    expense_id.setBackgroundColor(BaseColor.GRAY);
		    expense_id.setExtraParagraphSpace(5);
		    table.addCell(expense_id);
		    
		    PdfPCell expense_name=new PdfPCell(new Paragraph("expense_name", tableHeader));
		    expense_name.setBorderColor(BaseColor.BLACK);
		    expense_name.setPaddingLeft(10);
		    expense_name.setPaddingRight(10);
		    expense_name.setHorizontalAlignment(Element.ALIGN_CENTER);
		    expense_name.setVerticalAlignment(Element.ALIGN_CENTER);
		    expense_name.setBackgroundColor(BaseColor.GRAY);
		    expense_name.setExtraParagraphSpace(5);
		    table.addCell(expense_name);
		    
		    PdfPCell cost=new PdfPCell(new Paragraph("cost", tableHeader));
		    cost.setBorderColor(BaseColor.BLACK);
		    cost.setPaddingLeft(10);
		    cost.setPaddingRight(10);
		    cost.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cost.setVerticalAlignment(Element.ALIGN_CENTER);
		    cost.setBackgroundColor(BaseColor.GRAY);
		    cost.setExtraParagraphSpace(5);
		    table.addCell(cost);
		    
		    PdfPCell expense_date=new PdfPCell(new Paragraph("expense date", tableHeader));
		    expense_date.setBorderColor(BaseColor.BLACK);
		    expense_date.setPaddingLeft(10);
		    expense_date.setPaddingRight(10);
		    expense_date.setHorizontalAlignment(Element.ALIGN_CENTER);
		    expense_date.setVerticalAlignment(Element.ALIGN_CENTER);
		    expense_date.setBackgroundColor(BaseColor.GRAY);
		    expense_date.setExtraParagraphSpace(5);
		    table.addCell(expense_date);
		    
		    for(Expense expense:expenses)
		    {
		    	 String expense_id1=String.valueOf(expense.getExpense_id());
		    	 PdfPCell expense_idvalue=new PdfPCell(new Paragraph(expense_id1));
				 expense_idvalue.setBorderColor(BaseColor.BLACK);
				 expense_idvalue.setPaddingLeft(10);
				 expense_idvalue.setPaddingRight(10);
				 expense_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				 expense_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				 expense_idvalue.setBackgroundColor(BaseColor.WHITE);
				 expense_idvalue.setExtraParagraphSpace(5);
				 table.addCell(expense_idvalue);
		   
				 PdfPCell expense_namevalue=new PdfPCell(new Paragraph(expense.getExpense_name(), tableBody));
				 expense_namevalue.setBorderColor(BaseColor.BLACK);
				 expense_namevalue.setPaddingLeft(10);
				 expense_namevalue.setPaddingRight(10);
				 expense_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				 expense_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				 expense_namevalue.setBackgroundColor(BaseColor.WHITE);
				 expense_namevalue.setExtraParagraphSpace(5);
				 table.addCell(expense_namevalue);
				    
				    
				String cost1=String.valueOf(expense.getCost());
			    PdfPCell costvalue=new PdfPCell(new Paragraph(cost1));
			    costvalue.setBorderColor(BaseColor.BLACK);
			    costvalue.setPaddingLeft(10);
			    costvalue.setPaddingRight(10);
				costvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
			    costvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				costvalue.setBackgroundColor(BaseColor.WHITE);
				costvalue.setExtraParagraphSpace(5);
				table.addCell(costvalue);	   
				    
			    String expense_date1=String.valueOf(expense.getExpense_date());
			    PdfPCell expense_datevalue=new PdfPCell(new Paragraph(expense_date1));
			    expense_datevalue.setBorderColor(BaseColor.BLACK);
			    expense_datevalue.setPaddingLeft(10);
			    expense_datevalue.setPaddingRight(10);
				expense_datevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
			    expense_datevalue.setVerticalAlignment(Element.ALIGN_CENTER);
			    expense_datevalue.setBackgroundColor(BaseColor.WHITE);
				expense_datevalue.setExtraParagraphSpace(5);
				table.addCell(expense_datevalue);	 
		    }	
		    String value;
			int count=0;
			double sum=0;
			for(Expense expense1:expenses)
			{
				int expense_id2=(int) (expense1.getExpense_id()/10);
				count=count+1;
			}
			
			   value=String.valueOf(count);
				 PdfPCell totalexpenseid=new PdfPCell(new Paragraph(value));
				 totalexpenseid.setBorderColor(BaseColor.BLACK);
				 totalexpenseid.setPaddingLeft(10);
				 totalexpenseid.setPaddingRight(10);
				 totalexpenseid.setHorizontalAlignment(Element.ALIGN_CENTER);
				 totalexpenseid.setVerticalAlignment(Element.ALIGN_CENTER);
				 totalexpenseid.setBackgroundColor(BaseColor.WHITE);
				 totalexpenseid.setExtraParagraphSpace(5);
				 table.addCell(totalexpenseid);   
			
				 PdfPCell totalexpensename=new PdfPCell(new Paragraph());
				 totalexpensename.setBorderColor(BaseColor.BLACK);
				 totalexpensename.setPaddingLeft(10);
				 totalexpensename.setPaddingRight(10);
				 totalexpensename.setHorizontalAlignment(Element.ALIGN_CENTER);
				 totalexpensename.setVerticalAlignment(Element.ALIGN_CENTER);
				 totalexpensename.setBackgroundColor(BaseColor.WHITE);
				 totalexpensename.setExtraParagraphSpace(5);
				 table.addCell(totalexpensename); 
				 
				 for(Expense expense2:expenses)
				 {
					  sum=expense2.getCost() +sum;						 
				 }
				 value=String.valueOf(sum);
				 PdfPCell totalcost=new PdfPCell(new Paragraph(value));
				 totalcost.setBorderColor(BaseColor.BLACK);
				 totalcost.setPaddingLeft(10);
				 totalcost.setPaddingRight(10);
				 totalcost.setHorizontalAlignment(Element.ALIGN_CENTER);
				 totalcost.setVerticalAlignment(Element.ALIGN_CENTER);
				 totalcost.setBackgroundColor(BaseColor.WHITE);
				 totalcost.setExtraParagraphSpace(5);
				 table.addCell(totalcost);
				 
				 
				 PdfPCell totalexpense1=new PdfPCell(new Paragraph());
				 totalexpense1.setBorderColor(BaseColor.BLACK);
				 totalexpense1.setPaddingLeft(10);
				 totalexpense1.setPaddingRight(10);
				 totalexpense1.setHorizontalAlignment(Element.ALIGN_CENTER);
				 totalexpense1.setVerticalAlignment(Element.ALIGN_CENTER);
				 totalexpense1.setBackgroundColor(BaseColor.WHITE);
				 totalexpense1.setExtraParagraphSpace(5);
				 table.addCell(totalexpense1);
			    document.add(table);
			   
			    document.close();
			    writer.close();
			    return true;
		    
		}
		catch(Exception e)
		{
			return false;
		}
	}


	@Override
	public boolean createExcel(List<Expense> expenses, ServletContext context, HttpServletResponse response,
			HttpServletRequest request) {
		// TODO Auto-generated method stub
		String filepath=context.getRealPath("/resources/reports");
		File file=new File(filepath);
		boolean exists=new File(filepath).exists();
		if(!exists)
		{
			new File(filepath).mkdirs();
		}
		try
		{
			FileOutputStream outputstream=new FileOutputStream(file+"/"+"expenses"+".xls");
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet worksheet=workbook.createSheet("Expenses");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle=workbook.createCellStyle();
		     headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		     headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		     
		     HSSFRow headerRow=worksheet.createRow(0);
		     HSSFCell expense_id=headerRow.createCell(0);
		     expense_id.setCellValue("Expense Id");
		     expense_id.setCellStyle(headerCellStyle);
		     
		     HSSFCell expense_name=headerRow.createCell(1);
		     expense_name.setCellValue("Expense Name");
		     expense_name.setCellStyle(headerCellStyle);
		     
		     HSSFCell cost=headerRow.createCell(2);
		     cost.setCellValue("Cost");
		     cost.setCellStyle(headerCellStyle);
		     
		     HSSFCell expense_date=headerRow.createCell(3);
		     expense_date.setCellValue("Expense Date");
		     expense_date.setCellStyle(headerCellStyle);
		
		     int i=1;
		     for(Expense expense:expenses)
		     {
		    	 HSSFRow bodyRow=worksheet.createRow(i);
		    	 HSSFCellStyle bodycellstyle=workbook.createCellStyle();
		    	 bodycellstyle.setFillBackgroundColor(HSSFColor.WHITE.index);
		    	 
		    	 HSSFCell expense_idValue=bodyRow.createCell(0);
		    	 expense_idValue.setCellValue(expense.getExpense_id());
		    	 expense_idValue.setCellStyle(bodycellstyle);
		    	 
		    	 
		    	 HSSFCell expense_nameValue=bodyRow.createCell(1);
		    	 expense_nameValue.setCellValue(expense.getExpense_name());
		    	 expense_nameValue.setCellStyle(bodycellstyle);
		    	 
		    	 
		    	 HSSFCell cost_Value=bodyRow.createCell(2);
		    	 cost_Value.setCellValue(expense.getCost());
		    	 cost_Value.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell expensedate_Value=bodyRow.createCell(3);
		    	 expensedate_Value.setCellValue(expense.getExpense_date());
		    	 expensedate_Value.setCellStyle(bodycellstyle);
		    	 
		         i++;
		     }	 
		     workbook.write(outputstream);
		     outputstream.flush();
		     outputstream.close();
		     return true;
	}
		catch(Exception e)
		{
			return false;
		}

}


	@Override
	public List<Expense> getExpenseByExpenseDate(String[] expense_date) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		   List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, expense_date);
		    List<Expense> expense=new ArrayList<Expense>();
		    for(Map<String, Object> rows:row)
		    {
		    	Expense obj=new Expense();
		    	obj.setExpense_id((long)rows.get("expense_id"));
		    	obj.setExpense_name((String)rows.get("expense_name"));
		    	obj.setCost((double)rows.get("Cost"));
		    	obj.setExpense_date((Date)rows.get("expense_date"));
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
	public int countTotalId(String[] expense_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(expense_id) FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expense_date);
		return total;
	}


	@Override
	public Double totalCost(String[] expense_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(cost) FROM expense WHERE expense_date>=?::Date and expense_date<=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, expense_date);
		return total;
	}


	@Override
	public int countTotalId(String expense_name) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(expense_id) FROM expense WHERE expense_name=?";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expense_name);
		return total;
	}


	@Override
	public double totalCost(String expense_name) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(cost) FROM expense WHERE expense_name=?";
		double total=this.getJdbcTemplate().queryForObject(sql, double.class, expense_name);
		return total;
	}
}
