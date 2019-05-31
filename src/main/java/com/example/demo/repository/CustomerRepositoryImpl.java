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

import com.example.demo.model.Customer;
import com.example.demo.model.Customer_Product;
import com.example.demo.model.Customer_View;
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
public class CustomerRepositoryImpl extends JdbcDaoSupport implements CustomerRepository {

	 @Autowired	
		DataSource datasource;
	    
	    @PostConstruct
	    public void initialize()
	    {
	    	setDataSource(datasource);
	    }
	
	@Override
	public List<Customer> getAllCustomer() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM customer";
		RowMapper<Customer> rowmapper=new BeanPropertyRowMapper<Customer> (Customer.class);
		return this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public List<Customer_View> getAllCustomerInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, c.country,h.quantity,h.buy_date,h.amount,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<Customer_View> rowmapper=new BeanPropertyRowMapper<Customer_View> (Customer_View.class);
		return  this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public void insertintocustomer(Customer customer) {
		// TODO Auto-generated method stub
	String sql="INSERT INTO customer "+"(customer_id, customer_name, permanent_address, temporary_address, phone_number, country) SELECT ?,?,?,?,?,?";
	getJdbcTemplate().update(sql, new Object[] {customer.getCustomer_id(), customer.getCustomer_name(), customer.getTemporary_address(), customer.getPermanent_address(), customer.getPhone_number(), customer.getCountry()});	
	}
	@Override
	public void insertintocustomerproduct(Customer_Product customerproduct) {
		// TODO Auto-generated method stub
	String sql="INSERT INTO customer_product "+"(customer_customer_id, product_product_id, quantity, buy_date ,amount) SELECT ?,?,?,?,?";
	getJdbcTemplate().update(sql, new Object[] {customerproduct.getCustomer_customer_id(), customerproduct.getProduct_product_id(), customerproduct.getQuantity(),customerproduct.getBuy_date(),customerproduct.getAmount()});
	}

	@Override
	public Customer_Product getCustomerById(long customer_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM customer_product WHERE customer_customer_id=?";
		RowMapper<Customer_Product> rowmapper=new BeanPropertyRowMapper<Customer_Product>(Customer_Product.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, customer_id);
	}

	@Override
	public Customer_View getCustomerId(long customer_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, c.country,h.customer_customer_id,h.product_product_id,h.quantity,h.amount,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id WHERE customer_id=? and product_id=?";
		RowMapper<Customer_View> rowmapper=new BeanPropertyRowMapper<Customer_View>(Customer_View.class); 
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, customer_id, product_id);
	}
	
	@Override
	public List<Customer_View> getCustomerByBuyDate(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number,c.country, h.customer_customer_id, h.product_product_id, h.quantity, h.amount,h.buy_date ,i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id=i.product_id WHERE buy_date>=?::Date and buy_date<=?::Date";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, buy_date);
        List<Customer_View> customer_view=new ArrayList<Customer_View>();
        for(Map<String, Object> rows:row)
        {
        	Customer_View obj=new Customer_View();
        	obj.setCustomer_id((long)rows.get("customer_id"));
        	obj.setCustomer_name((String)rows.get("customer_name"));
        	obj.setPermanent_address((String)rows.get("permanent_address"));
        	obj.setTemporary_address((String)rows.get("temporary_address"));
        	obj.setPhone_number((int)rows.get("phone_number"));
        	obj.setCountry((String)rows.get("country"));
        	obj.setBuy_date((Date)rows.get("buy_date"));
        	obj.setCustomer_customer_id((long)rows.get("customer_customer_id"));
        	obj.setProduct_product_id((long)rows.get("product_product_id"));
        	obj.setQuantity((int)rows.get("quantity"));
        	obj.setAmount((double)rows.get("amount"));
        	obj.setProduct_id((long)rows.get("product_id"));
        	obj.setProduct_name((String)rows.get("product_name"));
        	customer_view.add(obj);
        }
        return customer_view;
	}
	
	@Override
	public List<Customer_View> getCustomerByName(String customer_name) {
		// TODO Auto-generated method stub
	  String sql="SELECT c.customer_id, c.customer_name, c.permanent_address, c.temporary_address, c.phone_number, h.customer_customer_id, h.product_product_id, h.quantity, h.amount, i.product_id, i.product_name FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id INNER JOIN product i on h.product_product_id =i.product_id WHERE customer_name=?";
	  List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, customer_name);
	  List<Customer_View> customer_view=new ArrayList<Customer_View>();
	  for(Map<String, Object> rows:row)
	  {
		  Customer_View obj=new Customer_View();
      	obj.setCustomer_id((long)rows.get("customer_id"));
      	obj.setCustomer_name((String)rows.get("customer_name"));
      	obj.setPermanent_address((String)rows.get("permanent_address"));
      	obj.setTemporary_address((String)rows.get("temporary_address"));
      	obj.setPhone_number((int)rows.get("phone_number"));
      	obj.setCountry((String)rows.get("country"));
      	obj.setBuy_date((Date)rows.get("buy_date"));
      	obj.setCustomer_customer_id((long)rows.get("customer_customer_id"));
      	obj.setProduct_product_id((long)rows.get("product_product_id"));
      	obj.setQuantity((int)rows.get("quantity"));
      	obj.setAmount((double)rows.get("amount"));
      	obj.setProduct_id((long)rows.get("product_id"));
      	obj.setProduct_name((String)rows.get("product_name"));
      	customer_view.add(obj);
	  }
		return customer_view;
	}

	@Override
	public void updateintopersonalcustomer(Customer customer) {
		// TODO Auto-generated method stub
		String sql="UPDATE customer SET customer_name=?, permanent_address=?, temporary_address=?, phone_number=?, country=? WHERE customer_id=?";
		getJdbcTemplate().update(sql, new Object[] {customer.getCustomer_name(), customer.getPermanent_address(), customer.getTemporary_address(), customer.getPhone_number(), customer.getCountry(), customer.getCustomer_id()});
	}


	@Override
	public void updateintocustomerproduct(Customer_Product customerproduct) {
		// TODO Auto-generated method stub
        String sql="UPDATE customer_product SET product_product_id=?, quantity=?, buy_date=?, amount=? WHERE customer_customer_id=?";
        getJdbcTemplate().update(sql, new Object[] {customerproduct.getProduct_product_id(), customerproduct.getQuantity(), customerproduct.getBuy_date(), customerproduct.getAmount(), customerproduct.getCustomer_customer_id()});
	}

	@Override
	public void deleteintocustomerview(long customer_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM customer_product WHERE customer_customer_id=? and product_product_id=?";
		getJdbcTemplate().update(sql, customer_id, product_id);
	}

	@Override
	public void deleteintocustomer(long customer_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM customer WHERE customer_id IN(SELECT B.customer_customer_id FROM customer_product B INNER JOIN product p ON B.product_product_id=p.product_id WHERE customer_id=? and b.product_product_id=?)";
		getJdbcTemplate().update(sql, customer_id, product_id);
	}


	@Override
	public boolean createExcel(List<Customer_View> customers, ServletContext context, HttpServletRequest request,
			HttpServletResponse response) {
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
			FileOutputStream outputstream=new FileOutputStream(file+"/"+"customers"+".xls");
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet worksheet=workbook.createSheet("Customers");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle=workbook.createCellStyle();
		     headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		     headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		     
		     HSSFRow headerRow=worksheet.createRow(0);
		     HSSFCell customer_id=headerRow.createCell(0);
		     customer_id.setCellValue("Customer Id");
		     customer_id.setCellStyle(headerCellStyle);
		     
		     HSSFCell customer_name=headerRow.createCell(1);
		     customer_name.setCellValue("Customer Name");
		     customer_name.setCellStyle(headerCellStyle);
		     
		     HSSFCell quantity=headerRow.createCell(2);
		     quantity.setCellValue("Quantity");
		     quantity.setCellStyle(headerCellStyle);
		     
		     HSSFCell amount=headerRow.createCell(3);
		     amount.setCellValue("Amount");
		     amount.setCellStyle(headerCellStyle);
		     
		     HSSFCell buy_date=headerRow.createCell(4);
		     buy_date.setCellValue("Buy Date");
		     buy_date.setCellStyle(headerCellStyle);
		     
		     HSSFCell permanent_address=headerRow.createCell(5);
		     permanent_address.setCellValue("Permanent Address");
		     permanent_address.setCellStyle(headerCellStyle);
		     
		     HSSFCell temporary_address=headerRow.createCell(6);
		     temporary_address.setCellValue("Temporary Address");
		     temporary_address.setCellStyle(headerCellStyle);
		     
		     HSSFCell phone_number=headerRow.createCell(7);
		     phone_number.setCellValue("Phone Number");
		     phone_number.setCellStyle(headerCellStyle);
		     
		     
		     HSSFCell product_id=headerRow.createCell(8);
		     product_id.setCellValue("Product Id");
		     product_id.setCellStyle(headerCellStyle);
		     
		     HSSFCell product_name=headerRow.createCell(9);
		     product_name.setCellValue("Product Name");
		     product_name.setCellStyle(headerCellStyle);
		     
		     int i=1;
		     for(Customer_View customer:customers)
		     {
		    	 HSSFRow bodyRow=worksheet.createRow(i);
		    	 HSSFCellStyle bodycellstyle=workbook.createCellStyle();
		    	 bodycellstyle.setFillBackgroundColor(HSSFColor.WHITE.index);
		    	 
		    	 HSSFCell customer_idValue=bodyRow.createCell(0);
		    	 customer_idValue.setCellValue(customer.getCustomer_id());
		    	 customer_idValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell customer_nameValue=bodyRow.createCell(1);
		    	 customer_nameValue.setCellValue(customer.getCustomer_name());
		    	 customer_nameValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell quantityValue=bodyRow.createCell(2);
		    	 quantityValue.setCellValue(customer.getQuantity());
		    	 quantityValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell amountValue=bodyRow.createCell(3);
		    	 amountValue.setCellValue(customer.getAmount());
		    	 amountValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell dateValue=bodyRow.createCell(4);
		    	 dateValue.setCellValue(customer.getBuy_date());
		    	 dateValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell permanentaddressValue=bodyRow.createCell(5);
		    	 permanentaddressValue.setCellValue(customer.getPermanent_address());
		    	 amountValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell temporaryaddressValue=bodyRow.createCell(6);
		    	 temporaryaddressValue.setCellValue(customer.getTemporary_address());
		    	 System.out.println(customer.getTemporary_address());
		    	 temporaryaddressValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell phonenumberValue=bodyRow.createCell(7);
		    	 phonenumberValue.setCellValue(customer.getPhone_number());
		    	 phonenumberValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell product_idValue=bodyRow.createCell(8);
		    	 product_idValue.setCellValue(customer.getProduct_id());
		    	 product_idValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell product_nameValue=bodyRow.createCell(9);
		    	 product_nameValue.setCellValue(customer.getProduct_name());
		    	 product_nameValue.setCellStyle(bodycellstyle);
		    	 
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
	public boolean createPdf(List<Customer_View> customers, ServletContext context, HttpServletRequest request,
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
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"customers"+".pdf"));
		    document.open();
            Font mainfont=FontFactory.getFont("Arial", 10, BaseColor.BLACK);
		    Paragraph paragraph=new Paragraph("All Customers", mainfont);
		    paragraph.setAlignment(Element.ALIGN_CENTER);
		    paragraph.setIndentationLeft(50);
		    paragraph.setIndentationRight(50);
		    paragraph.setSpacingAfter(10);
		    document.add(paragraph);
		    
		    PdfPTable table=new PdfPTable(10);
		    table.setWidthPercentage(100);
		    table.setSpacingBefore(10f);
		    table.setSpacingAfter(10);
		    
		    Font tableHeader=FontFactory.getFont("Arial",10,BaseColor.BLACK);
		    Font tableBody=FontFactory.getFont("Arial",9,BaseColor.BLACK);
		    
		    float[] columnWidths= {2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f, 2f};
		    table.setWidths(columnWidths);
		    
		    PdfPCell customer_id=new PdfPCell(new Paragraph("customer_id", tableHeader));
		    customer_id.setBorderColor(BaseColor.BLACK);
		    customer_id.setPaddingLeft(10);
		    customer_id.setPaddingRight(10);
		    customer_id.setHorizontalAlignment(Element.ALIGN_CENTER);
		    customer_id.setVerticalAlignment(Element.ALIGN_CENTER);
		    customer_id.setBackgroundColor(BaseColor.GRAY);
		    customer_id.setExtraParagraphSpace(5);
		    table.addCell(customer_id);
		    
		    PdfPCell customer_name=new PdfPCell(new Paragraph("customer_name", tableHeader));
		    customer_name.setBorderColor(BaseColor.BLACK);
		    customer_name.setPaddingLeft(10);
		    customer_name.setPaddingRight(10);
		    customer_name.setHorizontalAlignment(Element.ALIGN_CENTER);
		    customer_name.setVerticalAlignment(Element.ALIGN_CENTER);
		    customer_name.setBackgroundColor(BaseColor.GRAY);
		    customer_name.setExtraParagraphSpace(5);
		    table.addCell(customer_name);
		    
		    PdfPCell quantity=new PdfPCell(new Paragraph("quantity", tableHeader));
		    quantity.setBorderColor(BaseColor.BLACK);
		    quantity.setPaddingLeft(10);
		    quantity.setPaddingRight(10);
		    quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
		    quantity.setVerticalAlignment(Element.ALIGN_CENTER);
		    quantity.setBackgroundColor(BaseColor.GRAY);
		    quantity.setExtraParagraphSpace(5);
		    table.addCell(quantity);
		    
		    PdfPCell amount=new PdfPCell(new Paragraph("amount", tableHeader));
		    amount.setBorderColor(BaseColor.BLACK);
		    amount.setPaddingLeft(10);
		    amount.setPaddingRight(10);
		    amount.setHorizontalAlignment(Element.ALIGN_CENTER);
		    amount.setVerticalAlignment(Element.ALIGN_CENTER);
		    amount.setBackgroundColor(BaseColor.GRAY);
		    amount.setExtraParagraphSpace(5);
		    table.addCell(amount);
		    
		    PdfPCell buy_date=new PdfPCell(new Paragraph("buy_date", tableHeader));
		    buy_date.setBorderColor(BaseColor.BLACK);
		    buy_date.setPaddingLeft(10);
		    buy_date.setPaddingRight(10);
		    buy_date.setHorizontalAlignment(Element.ALIGN_CENTER);
		    buy_date.setVerticalAlignment(Element.ALIGN_CENTER);
		    buy_date.setBackgroundColor(BaseColor.GRAY);
		    buy_date.setExtraParagraphSpace(5);
		    table.addCell(buy_date);
		    
		    
		    PdfPCell permanent_address=new PdfPCell(new Paragraph("permanent_address", tableHeader));
		    permanent_address.setBorderColor(BaseColor.BLACK);
		    permanent_address.setPaddingLeft(10);
		    permanent_address.setPaddingRight(10);
		    permanent_address.setHorizontalAlignment(Element.ALIGN_CENTER);
		    permanent_address.setVerticalAlignment(Element.ALIGN_CENTER);
		    permanent_address.setBackgroundColor(BaseColor.GRAY);
		    permanent_address.setExtraParagraphSpace(5);
		    table.addCell(permanent_address);
		    
		    PdfPCell temporary_address=new PdfPCell(new Paragraph("temporary_address", tableHeader));
		    temporary_address.setBorderColor(BaseColor.BLACK);
		    temporary_address.setPaddingLeft(10);
		    temporary_address.setPaddingRight(10);
		    temporary_address.setHorizontalAlignment(Element.ALIGN_CENTER);
		    temporary_address.setVerticalAlignment(Element.ALIGN_CENTER);
		    temporary_address.setBackgroundColor(BaseColor.GRAY);
		    temporary_address.setExtraParagraphSpace(5);
		    table.addCell(temporary_address);
		    
		    
		    PdfPCell phone_number=new PdfPCell(new Paragraph("phone_number", tableHeader));
		    phone_number.setBorderColor(BaseColor.BLACK);
		    phone_number.setPaddingLeft(10);
		    phone_number.setPaddingRight(10);
		    phone_number.setHorizontalAlignment(Element.ALIGN_CENTER);
		    phone_number.setVerticalAlignment(Element.ALIGN_CENTER);
		    phone_number.setBackgroundColor(BaseColor.GRAY);
		    phone_number.setExtraParagraphSpace(5);
		    table.addCell(phone_number);
		    
		    PdfPCell product_id=new PdfPCell(new Paragraph("product_id", tableHeader));
		    product_id.setBorderColor(BaseColor.BLACK);
		    product_id.setPaddingLeft(10);
		    product_id.setPaddingRight(10);
		    product_id.setHorizontalAlignment(Element.ALIGN_CENTER);
		    product_id.setVerticalAlignment(Element.ALIGN_CENTER);
		    product_id.setBackgroundColor(BaseColor.GRAY);
		    product_id.setExtraParagraphSpace(5);
		    table.addCell(product_id);
		    
		    
		    PdfPCell product_name=new PdfPCell(new Paragraph("product_name", tableHeader));
		    product_name.setBorderColor(BaseColor.BLACK);
		    product_name.setPaddingLeft(10);
		    product_name.setPaddingRight(10);
		    product_name.setHorizontalAlignment(Element.ALIGN_CENTER);
		    product_name.setVerticalAlignment(Element.ALIGN_CENTER);
		    product_name.setBackgroundColor(BaseColor.GRAY);
		    product_name.setExtraParagraphSpace(5);
		    table.addCell(product_name);
		    
		    for(Customer_View customer:customers)
		    {
		    	String customer_id1=String.valueOf(customer.getCustomer_id());
		    	 PdfPCell customer_idvalue=new PdfPCell(new Paragraph(customer_id1));
				    customer_idvalue.setBorderColor(BaseColor.BLACK);
				    customer_idvalue.setPaddingLeft(10);
				    customer_idvalue.setPaddingRight(10);
				    customer_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    customer_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    customer_idvalue.setBackgroundColor(BaseColor.WHITE);
				    customer_idvalue.setExtraParagraphSpace(5f);
				    table.addCell(customer_idvalue);
		   
				    PdfPCell customer_namevalue=new PdfPCell(new Paragraph(customer.getCustomer_name(), tableBody));
				    customer_namevalue.setBorderColor(BaseColor.BLACK);
				    customer_namevalue.setPaddingLeft(10);
				    customer_namevalue.setPaddingRight(10);
				    customer_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    customer_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    customer_namevalue.setBackgroundColor(BaseColor.WHITE);
				    customer_namevalue.setExtraParagraphSpace(5f);
				    table.addCell(customer_namevalue);
				    
				    String quantity1=String.valueOf(customer.getQuantity());
				    PdfPCell quantityvalue=new PdfPCell(new Paragraph(quantity1));
				    quantityvalue.setBorderColor(BaseColor.BLACK);
				    quantityvalue.setPaddingLeft(10);
				    quantityvalue.setPaddingRight(10);
				    quantityvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    quantityvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    quantityvalue.setBackgroundColor(BaseColor.WHITE);
				    quantityvalue.setExtraParagraphSpace(5f);
				    table.addCell(quantityvalue);
				    
				    
				    String amount1=String.valueOf(customer.getAmount());
				    PdfPCell amountvalue=new PdfPCell(new Paragraph(amount1));
				    quantityvalue.setBorderColor(BaseColor.BLACK);
				    quantityvalue.setPaddingLeft(10);
				    quantityvalue.setPaddingRight(10);
				    quantityvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    quantityvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    quantityvalue.setBackgroundColor(BaseColor.WHITE);
				    quantityvalue.setExtraParagraphSpace(5f);
				    table.addCell(amountvalue);
				    
				    
				    PdfPCell datevalue=new PdfPCell(new Paragraph(customer.getBuy_date().toLocaleString()));
				    datevalue.setBorderColor(BaseColor.BLACK);
				    datevalue.setPaddingLeft(10);
				    datevalue.setPaddingRight(10);
				    datevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    datevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    datevalue.setBackgroundColor(BaseColor.WHITE);
				    datevalue.setExtraParagraphSpace(5f);
				    table.addCell(datevalue);
				    
				    PdfPCell permanentaddressvalue=new PdfPCell(new Paragraph(customer.getPermanent_address(),tableBody));
				    permanentaddressvalue.setBorderColor(BaseColor.BLACK);
				    permanentaddressvalue.setPaddingLeft(10);
				    permanentaddressvalue.setPaddingRight(10);
				    permanentaddressvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    permanentaddressvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    permanentaddressvalue.setBackgroundColor(BaseColor.WHITE);
				    permanentaddressvalue.setExtraParagraphSpace(5f);
				    table.addCell(permanentaddressvalue);
				    
				    PdfPCell temporaryaddressvalue=new PdfPCell(new Paragraph(customer.getTemporary_address(),tableBody));
				    temporaryaddressvalue.setBorderColor(BaseColor.BLACK);
				    temporaryaddressvalue.setPaddingLeft(10);
				    temporaryaddressvalue.setPaddingRight(10);
				    temporaryaddressvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    temporaryaddressvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    temporaryaddressvalue.setBackgroundColor(BaseColor.WHITE);
				    temporaryaddressvalue.setExtraParagraphSpace(5f);
				    table.addCell(temporaryaddressvalue);
				    
				    String phonenumber1=String.valueOf(customer.getPhone_number());
				    PdfPCell phonenumbervalue=new PdfPCell(new Paragraph(phonenumber1));
				    phonenumbervalue.setBorderColor(BaseColor.BLACK);
				    phonenumbervalue.setPaddingLeft(10);
				    phonenumbervalue.setPaddingRight(10);
				    phonenumbervalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    phonenumbervalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    phonenumbervalue.setBackgroundColor(BaseColor.WHITE);
				    phonenumbervalue.setExtraParagraphSpace(5f);
				    table.addCell(phonenumbervalue);
				    
				    
				    String product_id1=String.valueOf(customer.getProduct_id());
				    PdfPCell product_idvalue=new PdfPCell(new Paragraph(product_id1));
				    product_idvalue.setBorderColor(BaseColor.BLACK);
				    product_idvalue.setPaddingLeft(10);
				    product_idvalue.setPaddingRight(10);
				    product_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    product_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    product_idvalue.setBackgroundColor(BaseColor.WHITE);
				    product_idvalue.setExtraParagraphSpace(5f);
				    table.addCell(product_idvalue);
				    
				    PdfPCell product_namevalue=new PdfPCell(new Paragraph(customer.getProduct_name(), tableBody));
				    System.out.println(customer.getProduct_name());
				    product_namevalue.setBorderColor(BaseColor.BLACK);
				    product_namevalue.setPaddingLeft(10);
				    product_namevalue.setPaddingRight(10);
				    product_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    product_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    product_namevalue.setBackgroundColor(BaseColor.WHITE);
				    product_namevalue.setExtraParagraphSpace(5f);
				    table.addCell(product_namevalue);
				    
		    }
		     
		    String value;
		    int count=0, count1=0;
		    double sum=0;
		    int sum1=0;
		    for(Customer_View customer1:customers)
		    {
		    	int customer_id1=(int) (customer1.getCustomer_id()/10);
		    	count=count+1;
		    }
		     value=String.valueOf(count);
			 PdfPCell totalcustomer=new PdfPCell(new Paragraph(value));
			 totalcustomer.setBorderColor(BaseColor.BLACK);
			 totalcustomer.setPaddingLeft(10);
			 totalcustomer.setPaddingRight(10);
			 totalcustomer.setHorizontalAlignment(Element.ALIGN_CENTER);
			 totalcustomer.setVerticalAlignment(Element.ALIGN_CENTER);
			 totalcustomer.setBackgroundColor(BaseColor.WHITE);
			 totalcustomer.setExtraParagraphSpace(5);
			 table.addCell(totalcustomer);    
			 
			 PdfPCell customer_name1=new PdfPCell(new Paragraph());
			 customer_name1.setBorderColor(BaseColor.BLACK);
			 customer_name1.setPaddingLeft(10);
			 customer_name1.setPaddingRight(10);
			 customer_name1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 customer_name1.setVerticalAlignment(Element.ALIGN_CENTER);
			 customer_name1.setBackgroundColor(BaseColor.WHITE);
			 customer_name1.setExtraParagraphSpace(5);
			 table.addCell(customer_name1); 
			 
			 
			 for(Customer_View customer1:customers)
			 {
				 sum1=sum1+customer1.getQuantity();
			 }
			 value=String.valueOf(sum1);
			 PdfPCell totalquantity=new PdfPCell(new Paragraph(value));
			 totalquantity.setBorderColor(BaseColor.BLACK);
			 totalquantity.setPaddingLeft(10);
			 totalquantity.setPaddingRight(10);
			 totalquantity.setHorizontalAlignment(Element.ALIGN_CENTER);
			 totalquantity.setVerticalAlignment(Element.ALIGN_CENTER);
			 totalquantity.setBackgroundColor(BaseColor.WHITE);
			 totalquantity.setExtraParagraphSpace(5);
			 table.addCell(totalquantity);    
				
			 for(Customer_View customer1:customers)
			 {
				 sum=sum+customer1.getAmount();
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
			 
			 

			 PdfPCell buy_date1=new PdfPCell(new Paragraph());
			 buy_date1.setBorderColor(BaseColor.BLACK);
			 buy_date1.setPaddingLeft(10);
			 buy_date1.setPaddingRight(10);
			 buy_date1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 buy_date1.setVerticalAlignment(Element.ALIGN_CENTER);
			 buy_date1.setBackgroundColor(BaseColor.WHITE);
			 buy_date1.setExtraParagraphSpace(5);
			 table.addCell(buy_date1); 
			 
			 PdfPCell permanent_address1=new PdfPCell(new Paragraph());
			 permanent_address1.setBorderColor(BaseColor.BLACK);
			 permanent_address1.setPaddingLeft(10);
			 permanent_address1.setPaddingRight(10);
			 permanent_address1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 permanent_address1.setVerticalAlignment(Element.ALIGN_CENTER);
			 permanent_address1.setBackgroundColor(BaseColor.WHITE);
			 permanent_address1.setExtraParagraphSpace(5);
			 table.addCell(permanent_address1); 
			 
			 PdfPCell temporary_address1=new PdfPCell(new Paragraph());
			 temporary_address1.setBorderColor(BaseColor.BLACK);
			 temporary_address1.setPaddingLeft(10);
			 temporary_address1.setPaddingRight(10);
			 temporary_address1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 temporary_address1.setVerticalAlignment(Element.ALIGN_CENTER);
			 temporary_address1.setBackgroundColor(BaseColor.WHITE);
			 temporary_address1.setExtraParagraphSpace(5);
			 table.addCell(temporary_address1); 
			 
			 PdfPCell phone_number1=new PdfPCell(new Paragraph());
			 phone_number1.setBorderColor(BaseColor.BLACK);
			 phone_number1.setPaddingLeft(10);
			 phone_number1.setPaddingRight(10);
			 phone_number1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 phone_number1.setVerticalAlignment(Element.ALIGN_CENTER);
			 phone_number1.setBackgroundColor(BaseColor.WHITE);
			 phone_number1.setExtraParagraphSpace(5);
			 table.addCell(phone_number1); 
			 
			 for(Customer_View customer3:customers)
			 {
				  	System.out.println(customer3.getProduct_id());
				  	int product_id1=(int) (customer3.getProduct_id()/10);
			    	count1=count1+1;
			 }
			 value=String.valueOf(count1);
			 PdfPCell totalproduct=new PdfPCell(new Paragraph(value));
			 totalproduct.setBorderColor(BaseColor.BLACK);
			 totalproduct.setPaddingLeft(10);
			 totalproduct.setPaddingRight(10);
			 totalproduct.setHorizontalAlignment(Element.ALIGN_CENTER);
			 totalproduct.setVerticalAlignment(Element.ALIGN_CENTER);
			 totalproduct.setBackgroundColor(BaseColor.WHITE);
			 totalproduct.setExtraParagraphSpace(5);
			 table.addCell(totalproduct);
			 
			 PdfPCell product_name1=new PdfPCell(new Paragraph());
			 product_name1.setBorderColor(BaseColor.BLACK);
			 product_name1.setPaddingLeft(10);
			 product_name1.setPaddingRight(10);
			 product_name1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 product_name1.setVerticalAlignment(Element.ALIGN_CENTER);
			 product_name1.setBackgroundColor(BaseColor.WHITE);
			 product_name1.setExtraParagraphSpace(5);
			 table.addCell(product_name1); 
			 
		    document.add(table);
		    document.close();
		    writer.close();
		    return true;
		    
		}
		catch(Exception e)
		{
			
		}
		return false;
	}

	@Override
	public int getTotalCustomer() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(customer_customer_id) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public int sumOfQuantity() {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(quantity) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public double sumOfAmount() {
		// TODO Auto-generated method stub
	  String sql="SELECT SUM(amount) FROM customer_product";
	  double total=this.getJdbcTemplate().queryForObject(sql, Double.class);
		return total;
	}

	@Override
	public int getTotalProduct() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_product_id) FROM customer_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}

	@Override
	public int getTotalCustomer(String customer_name) {
		// TODO Auto-generated method stub
		  String sql="SELECT  COUNT(h.customer_customer_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customer_name);
		  return total;
	}

	@Override
	public int sumOfQuantity(String customer_name) {
		// TODO Auto-generated method stub
		  String sql="SELECT SUM(h.quantity) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customer_name);
		  return total;
	}

	@Override
	public double sumOfAmount(String customer_name) {
		// TODO Auto-generated method stub
		 String sql="SELECT SUM(h.amount) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		 double total=this.getJdbcTemplate().queryForObject(sql, Double.class, customer_name);
		 return total;
	}

	@Override
	public int getTotalProduct(String customer_name) {
		// TODO Auto-generated method stub
		 String sql="SELECT COUNT(h.product_product_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE customer_name=?";
		 int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, customer_name);
		 return total;
	}

	@Override
	public int getTotalCustomer(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.customer_customer_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public Integer sumOfQuantity(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public Double sumOfAmount(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(amount) FROM customer_product  WHERE buy_date>=?::Date and buy_date<=?::Date";
        Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buy_date);
		return total;
	}

	@Override
	public int getTotalProduct(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.product_product_id) FROM Customer c INNER JOIN customer_product h on c.customer_id=h.customer_customer_id  WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public int getPresentDate(String buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(quantity) FROM Customer_Product WHERE buy_date=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	  public int getNumberofCustomersToday(String buy_date){
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(customer_customer_id) FROM customer_product WHERE buy_date=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public Double getPresentRevenue(String buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(amount) FROM customer_product WHERE buy_date=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buy_date);
		return total;
	}
}
