package com.example.demo.repository;

import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

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

import com.example.demo.model.Product;
import com.example.demo.model.Supplier;
import com.example.demo.model.Supplier_Product;
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
public class SupplierRepositoryImpl extends JdbcDaoSupport implements SupplierRepository {

    @Autowired	
	DataSource datasource;
    
    @PostConstruct
    public void initialize()
    {
    	setDataSource(datasource);
    }
	
	@Override
	public void insertintosupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO supplier "+" (supplier_id, supplier_name, supplier_type, permanent_address, temporary_address, image) SELECT ?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplier.getSupplier_id(), supplier.getSupplier_name(), supplier.getSupplier_type(), supplier.getPermanent_address(), supplier.getTemporary_address(), supplier.getImage()});
	}
	
	@Override
	public void insertintosupplierproduct(Supplier_Product supplierproduct) {
		// TODO Auto-generated method stub
	    String sql="INSERT INTO supplier_product "+" (supplier_supplier_id, product_product_id, quantity, cost) SELECT ?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {supplierproduct.getSupplier_id(), supplierproduct.getProduct_id(), supplierproduct.getQuantity(), supplierproduct.getCost()});
	}
	

	@Override
	public List<Supplier_View> getAllSupplierInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.permanent_address, s.temporary_address,h.quantity, h.cost,h.buy_date,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<Supplier_View> rowmapper=new BeanPropertyRowMapper<Supplier_View> (Supplier_View.class);
		return this.getJdbcTemplate().query(sql,rowmapper);
	}

	@Override
	public Supplier_View getsupplierbyid(long supplier_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,h.quantity,h.cost,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_id=? and product_id=?";
		RowMapper<Supplier_View> rowmapper=new BeanPropertyRowMapper<Supplier_View> (Supplier_View.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, supplier_id, product_id);
	}

	@Override
	public void updateintosupplier(Supplier supplier) {
		// TODO Auto-generated method stub
		String sql="UPDATE supplier SET supplier_name=?,supplier_type=?,permanent_address=?, temporary_address=?,image=? WHERE supplier_id=?";
	    getJdbcTemplate().update(sql, new Object[] {supplier.getSupplier_name(), supplier.getSupplier_type(),supplier.getPermanent_address(), supplier.getTemporary_address(),supplier.getImage(),supplier.getSupplier_id()});
	}

	@Override
	public void updateintosupplierview(Supplier_Product supplierview) {
		// TODO Auto-generated method stub
	   String sql="UPDATE supplier_product SET product_product_id=?, quantity=?, cost=? WHERE supplier_supplier_id=?";
	   getJdbcTemplate().update(sql, new Object[] {supplierview.getProduct_id(),supplierview.getQuantity(),supplierview.getCost(),supplierview.getSupplier_id()});
	}
	

	@Override
	public void deleteintosupplierview(long supplier_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM supplier_product WHERE supplier_supplier_id=? AND product_product_id=?";
		getJdbcTemplate().update(sql, supplier_id, product_id);
	}

	@Override
	public void deleteintosupplier(long supplier_id, long product_id) {
		// TODO Auto-generated method stub
		String sql="DELETE FROM supplier WHERE supplier_id IN(SELECT B.supplier_supplier_id FROM supplier_product B INNER JOIN product p ON B.product_product_id=p.product_id WHERE supplier_id=? AND B.product_product_id=?)";
	    getJdbcTemplate().update(sql, supplier_id, product_id);
	}

	@Override
	public Supplier getsupplierid(long supplier_id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM supplier WHERE supplier_id=?";
		RowMapper<Supplier> rowmapper=new BeanPropertyRowMapper<Supplier> (Supplier.class);
		return this.getJdbcTemplate().queryForObject(sql, rowmapper, supplier_id);
	}

	@Override
	public List<Supplier_View> getSupplierbybuydate(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type, s.image, s.permanent_address, s.temporary_address, h.quantity,h.cost,h.buy_date,i.product_id, i.product_name FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE buy_date>=?::DATE and buy_date<=?::DATE ";
	    List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, buy_date);
	    List<Supplier_View> supplier_view=new ArrayList<Supplier_View>();
	    for(Map<String, Object> rows:row)
	    {
	    	Supplier_View obj=new Supplier_View();
	    	obj.setSupplier_id((int)rows.get("supplier_id"));
	    	System.out.println(obj.getSupplier_id());
	    	obj.setSupplier_name((String)rows.get("supplier_name"));
	    	System.out.println(obj.getSupplier_name());
	    	obj.setSupplier_type((String)rows.get("supplier_type"));
	        obj.setImage((String)rows.get("image"));
	        obj.setPermanent_address((String)rows.get("permanent_address"));
	        obj.setTemporary_address((String)rows.get("temporary_address"));
	        obj.setQuantity((int)rows.get("quantity"));
	        obj.setCost((double)rows.get("cost"));
	        obj.setBuy_date((Date)rows.get("buy_date"));
	       obj.setProduct_id((long)rows.get("product_id")); 
	       obj.setProduct_name((String)rows.get("product_name"));
	       supplier_view.add(obj);
	    }
	    return supplier_view;
	}

	@Override
	public List<Supplier> getAllSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM supplier";
		RowMapper<Supplier> rowmapper=new BeanPropertyRowMapper<Supplier> (Supplier.class);
		return this.getJdbcTemplate().query(sql, rowmapper);
	}

	@Override
	public List<Supplier_View> getSupplierByName(String supplier_name) {
		// TODO Auto-generated method stub
		String sql="SELECT s.supplier_id, s.supplier_name, s.supplier_type,s.image,s.permanent_address, s.temporary_address,h.quantity,h.cost,h.buy_date,i.product_id, i.product_name  FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id WHERE supplier_name=?";
		List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, supplier_name);
		List<Supplier_View> supplier_view=new ArrayList<Supplier_View>();
		for(Map<String, Object> rows:row)
		{
			Supplier_View obj=new Supplier_View();
			obj.setSupplier_id((int)rows.get("supplier_id"));
			obj.setSupplier_name((String)rows.get("supplier_name"));
			obj.setSupplier_type((String)rows.get("supplier_type"));
			obj.setImage((String)rows.get("image"));
			obj.setPermanent_address((String)rows.get("permanent_address"));
			obj.setTemporary_address((String)rows.get("temporary_address"));
			obj.setQuantity((int)rows.get("quantity"));
            obj.setCost((double)rows.get("cost"));
            obj.setBuy_date((Date)rows.get("buy_date"));
            obj.setProduct_id((long)rows.get("product_id"));
            obj.setProduct_name((String)rows.get("product_name"));
            supplier_view.add(obj);
		}
		return supplier_view;
	}

	@Override
	public List<Supplier_View> getSupplierInformaton() {
		// TODO Auto-generated method stub
		String sql="SELECT DISTINCT(s.supplier_name) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id INNER JOIN product i on h.product_product_id=i.product_id";
		RowMapper<Supplier_View> rowmapper=new BeanPropertyRowMapper<Supplier_View> (Supplier_View.class);
		return  this.getJdbcTemplate().query(sql,rowmapper);
	}
	
	
	@Override
	public double getSumOfCost() {
		// TODO Auto-generated method stub
	  String sql="SELECT SUM(cost) FROM supplier_product";
	  double total=this.getJdbcTemplate().queryForObject(sql, double.class);
		return total;
	}
	
	@Override
	public int getNoofSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(supplier_supplier_id) FROM supplier_product";
	    int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
	
	@Override
	public int getNoofQuantity() {
		// TODO Auto-generated method stub
	   String sql="SELECT SUM(quantity) FROM supplier_product";
	   int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}


	@Override
	public int getNoofProduct() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(product_product_id) FROM supplier_product";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}	
	@Override
	public boolean createExcel(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response,
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
			FileOutputStream outputstream=new FileOutputStream(file+"/"+"suppliers"+".xls");
			HSSFWorkbook workbook=new HSSFWorkbook();
			HSSFSheet worksheet=workbook.createSheet("Suppliers");
			worksheet.setDefaultColumnWidth(30);
			
			HSSFCellStyle headerCellStyle=workbook.createCellStyle();
		     headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
		     headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		     
		     HSSFRow headerRow=worksheet.createRow(0);
		     HSSFCell supplier_id=headerRow.createCell(0);
		     supplier_id.setCellValue("Supplier Id");
		     supplier_id.setCellStyle(headerCellStyle);
		     
		     HSSFCell supplier_name=headerRow.createCell(1);
		     supplier_name.setCellValue("Supplier Name");
		     supplier_name.setCellStyle(headerCellStyle);
		     
		     HSSFCell supplier_type=headerRow.createCell(2);
		     supplier_type.setCellValue("Supplier Type");
		     supplier_type.setCellStyle(headerCellStyle);
		     
		     HSSFCell quantity=headerRow.createCell(3);
		     quantity.setCellValue("Quantity");
		     quantity.setCellStyle(headerCellStyle);
		     
		     HSSFCell cost=headerRow.createCell(4);
		     cost.setCellValue("Cost");
		     cost.setCellStyle(headerCellStyle);
		     
		     HSSFCell buy_date=headerRow.createCell(5);
		     buy_date.setCellValue("Buy Date");
		     buy_date.setCellStyle(headerCellStyle);
		     
		     HSSFCell permanent_address=headerRow.createCell(6);
		     permanent_address.setCellValue("Permanent Address");
		     permanent_address.setCellStyle(headerCellStyle);
		     
		     HSSFCell temporary_address=headerRow.createCell(7);
		     temporary_address.setCellValue("Temporary Address");
		     temporary_address.setCellStyle(headerCellStyle);
		     
		     HSSFCell product_id=headerRow.createCell(8);
		     product_id.setCellValue("Product Id");
		     product_id.setCellStyle(headerCellStyle);
		     
		     HSSFCell product_name=headerRow.createCell(9);
		     product_name.setCellValue("Product Name");
		     product_name.setCellStyle(headerCellStyle);
		     
		     int i=1;
		     for(Supplier_View supplierproduct:supplierproducts)
		     {
		    	 HSSFRow bodyRow=worksheet.createRow(i);
		    	 HSSFCellStyle bodycellstyle=workbook.createCellStyle();
		    	 bodycellstyle.setFillBackgroundColor(HSSFColor.WHITE.index);
		    	 
		    	 HSSFCell supplier_idValue=bodyRow.createCell(0);
		    	 supplier_idValue.setCellValue(supplierproduct.getSupplier_id());
		    	 supplier_idValue.setCellStyle(bodycellstyle);
		    	 
		    	 
		    	 HSSFCell supplier_nameValue=bodyRow.createCell(1);
		    	 supplier_nameValue.setCellValue(supplierproduct.getSupplier_name());
		    	 supplier_nameValue.setCellStyle(bodycellstyle);
		    	 
		    	 
		    	 HSSFCell supplier_typeValue=bodyRow.createCell(2);
		    	 supplier_typeValue.setCellValue(supplierproduct.getSupplier_type());
		    	 supplier_typeValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell quantityValue=bodyRow.createCell(3);
		    	 quantityValue.setCellValue(supplierproduct.getQuantity());
		    	 quantityValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell costValue=bodyRow.createCell(4);
		    	 costValue.setCellValue(supplierproduct.getCost());
		    	 costValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell buy_dateValue=bodyRow.createCell(5);
		    	 buy_dateValue.setCellValue(supplierproduct.getBuy_date());
		    	 buy_dateValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell permanent_addressValue=bodyRow.createCell(6);
		    	 permanent_addressValue.setCellValue(supplierproduct.getPermanent_address());
		    	 permanent_addressValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell temporary_addressValue=bodyRow.createCell(7);
		    	 temporary_addressValue.setCellValue(supplierproduct.getTemporary_address());
		    	 temporary_addressValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell product_idValue=bodyRow.createCell(8);
		    	 product_idValue.setCellValue(supplierproduct.getProduct_id());
		    	 product_idValue.setCellStyle(bodycellstyle);
		    	 
		    	 HSSFCell product_nameValue=bodyRow.createCell(9);
		    	 product_nameValue.setCellValue(supplierproduct.getProduct_name());
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
	public boolean createPdf(List<Supplier_View> supplierproducts, ServletContext context, HttpServletResponse response,
			HttpServletRequest request) {
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
			PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"suppliers"+".pdf"));
		    document.open();
		    
		    Font mainfont=FontFactory.getFont("Arial", 10, BaseColor.BLACK);
		    
		    Paragraph paragraph=new Paragraph("All Suppliers", mainfont);
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
		    
		    
		    PdfPCell supplier_id=new PdfPCell(new Paragraph("supplier_id", tableHeader));
		    supplier_id.setBorderColor(BaseColor.BLACK);
		    supplier_id.setPaddingLeft(10);
		    supplier_id.setPaddingRight(10);
		    supplier_id.setHorizontalAlignment(Element.ALIGN_CENTER);
		    supplier_id.setVerticalAlignment(Element.ALIGN_CENTER);
		    supplier_id.setBackgroundColor(BaseColor.GRAY);
		    supplier_id.setExtraParagraphSpace(5);
		    table.addCell(supplier_id);
		    
		    PdfPCell supplier_name=new PdfPCell(new Paragraph("supplier_name", tableHeader));
		    supplier_name.setBorderColor(BaseColor.BLACK);
		    supplier_name.setPaddingLeft(10);
		    supplier_name.setPaddingRight(10);
		    supplier_name.setHorizontalAlignment(Element.ALIGN_CENTER);
		    supplier_name.setVerticalAlignment(Element.ALIGN_CENTER);
		    supplier_name.setBackgroundColor(BaseColor.GRAY);
		    supplier_name.setExtraParagraphSpace(5);
		    table.addCell(supplier_name);
		    
		    PdfPCell supplier_type=new PdfPCell(new Paragraph("supplier_type", tableHeader));
		    supplier_type.setBorderColor(BaseColor.BLACK);
		    supplier_type.setPaddingLeft(10);
		    supplier_type.setPaddingRight(10);
		    supplier_type.setHorizontalAlignment(Element.ALIGN_CENTER);
		    supplier_type.setVerticalAlignment(Element.ALIGN_CENTER);
		    supplier_type.setBackgroundColor(BaseColor.GRAY);
		    supplier_type.setExtraParagraphSpace(5);
		    table.addCell(supplier_type);
		    
		    PdfPCell quantity=new PdfPCell(new Paragraph("quantity", tableHeader));
		    quantity.setBorderColor(BaseColor.BLACK);
		    quantity.setPaddingLeft(10);
		    quantity.setPaddingRight(10);
		    quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
		    quantity.setVerticalAlignment(Element.ALIGN_CENTER);
		    quantity.setBackgroundColor(BaseColor.GRAY);
		    quantity.setExtraParagraphSpace(5);
		    table.addCell(quantity);
		    
		    
		    PdfPCell cost=new PdfPCell(new Paragraph("cost", tableHeader));
		    cost.setBorderColor(BaseColor.BLACK);
		    cost.setPaddingLeft(10);
		    cost.setPaddingRight(10);
		    cost.setHorizontalAlignment(Element.ALIGN_CENTER);
		    cost.setVerticalAlignment(Element.ALIGN_CENTER);
		    cost.setBackgroundColor(BaseColor.GRAY);
		    cost.setExtraParagraphSpace(5);
		    table.addCell(cost);
		    
		    PdfPCell buy_date=new PdfPCell(new Paragraph("buy date", tableHeader));
		    buy_date.setBorderColor(BaseColor.BLACK);
		    buy_date.setPaddingLeft(10);
		    buy_date.setPaddingRight(10);
		    buy_date.setHorizontalAlignment(Element.ALIGN_CENTER);
		    buy_date.setVerticalAlignment(Element.ALIGN_CENTER);
		    buy_date.setBackgroundColor(BaseColor.GRAY);
		    buy_date.setExtraParagraphSpace(5);
		    table.addCell(buy_date);
		    
		    PdfPCell permanent_address=new PdfPCell(new Paragraph("permanent address", tableHeader));
		    permanent_address.setBorderColor(BaseColor.BLACK);
		    permanent_address.setPaddingLeft(10);
		    permanent_address.setPaddingRight(10);
		    permanent_address.setHorizontalAlignment(Element.ALIGN_CENTER);
		    permanent_address.setVerticalAlignment(Element.ALIGN_CENTER);
		    permanent_address.setBackgroundColor(BaseColor.GRAY);
		    permanent_address.setExtraParagraphSpace(5);
		    table.addCell(permanent_address);
		    
		    PdfPCell temporary_address=new PdfPCell(new Paragraph("temporary address", tableHeader));
		    temporary_address.setBorderColor(BaseColor.BLACK);
		    temporary_address.setPaddingLeft(10);
		    temporary_address.setPaddingRight(10);
		    temporary_address.setHorizontalAlignment(Element.ALIGN_CENTER);
		    temporary_address.setVerticalAlignment(Element.ALIGN_CENTER);
		    temporary_address.setBackgroundColor(BaseColor.GRAY);
		    temporary_address.setExtraParagraphSpace(5);
		    table.addCell(temporary_address);
		    
		    
		    PdfPCell product_id=new PdfPCell(new Paragraph("product id", tableHeader));
		    product_id.setBorderColor(BaseColor.BLACK);
		    product_id.setPaddingLeft(10);
		    product_id.setPaddingRight(10);
		    product_id.setHorizontalAlignment(Element.ALIGN_CENTER);
		    product_id.setVerticalAlignment(Element.ALIGN_CENTER);
		    product_id.setBackgroundColor(BaseColor.GRAY);
		    product_id.setExtraParagraphSpace(5);
		    table.addCell(product_id);
		    
		    PdfPCell product_name=new PdfPCell(new Paragraph("product name", tableHeader));
		    product_name.setBorderColor(BaseColor.BLACK);
		    product_name.setPaddingLeft(10);
		    product_name.setPaddingRight(10);
		    product_name.setHorizontalAlignment(Element.ALIGN_CENTER);
		    product_name.setVerticalAlignment(Element.ALIGN_CENTER);
		    product_name.setBackgroundColor(BaseColor.GRAY);
		    product_name.setExtraParagraphSpace(5);
		    table.addCell(product_name);
		    
		    for(Supplier_View supplier:supplierproducts)
		    {
		    	 String supplier_id1=String.valueOf(supplier.getSupplier_id());
		    	 PdfPCell supplier_idvalue=new PdfPCell(new Paragraph(supplier_id1));
				   supplier_idvalue.setBorderColor(BaseColor.BLACK);
				   supplier_idvalue.setPaddingLeft(10);
				   supplier_idvalue.setPaddingRight(10);
				   supplier_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				   supplier_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
				   supplier_idvalue.setBackgroundColor(BaseColor.WHITE);
				   supplier_idvalue.setExtraParagraphSpace(5);
				    table.addCell(supplier_idvalue);
		   
				    PdfPCell supplier_namevalue=new PdfPCell(new Paragraph(supplier.getSupplier_name(), tableBody));
				    supplier_namevalue.setBorderColor(BaseColor.BLACK);
				    supplier_namevalue.setPaddingLeft(10);
				    supplier_namevalue.setPaddingRight(10);
				    supplier_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
				    supplier_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
				    supplier_namevalue.setBackgroundColor(BaseColor.WHITE);
				    supplier_namevalue.setExtraParagraphSpace(5);
				    table.addCell(supplier_namevalue);
				    
				    
					 String supplier_type1=String.valueOf(supplier.getSupplier_type());
			    	 PdfPCell supplier_typevalue=new PdfPCell(new Paragraph(supplier_type1));
					   supplier_typevalue.setBorderColor(BaseColor.BLACK);
					   supplier_typevalue.setPaddingLeft(10);
					   supplier_typevalue.setPaddingRight(10);
					   supplier_typevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					   supplier_typevalue.setVerticalAlignment(Element.ALIGN_CENTER);
					   supplier_typevalue.setBackgroundColor(BaseColor.WHITE);
					   supplier_typevalue.setExtraParagraphSpace(5);
					   table.addCell(supplier_typevalue);
					   
					   
				    
					 String quantity1=String.valueOf(supplier.getQuantity());
			    	 PdfPCell quantityvalue=new PdfPCell(new Paragraph(quantity1));
					 quantityvalue.setBorderColor(BaseColor.BLACK);
					 quantityvalue.setPaddingLeft(10);
					 quantityvalue.setPaddingRight(10);
					 quantityvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 quantityvalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 quantityvalue.setBackgroundColor(BaseColor.WHITE);
					 quantityvalue.setExtraParagraphSpace(5);
					 table.addCell(quantityvalue);    
				    
					 String cost1=String.valueOf(supplier.getCost());
				     PdfPCell costvalue=new PdfPCell(new Paragraph(cost1));
					 costvalue.setBorderColor(BaseColor.BLACK);
					 costvalue.setPaddingLeft(10);
					 costvalue.setPaddingRight(10);
					 costvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 costvalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 costvalue.setBackgroundColor(BaseColor.WHITE);
					 costvalue.setExtraParagraphSpace(5);
					 table.addCell(costvalue);
						    
					
				     PdfPCell buy_datevalue=new PdfPCell(new Paragraph(supplier.getBuy_date().toLocaleString()));
					 buy_datevalue.setBorderColor(BaseColor.BLACK);
					 buy_datevalue.setPaddingLeft(10);
					 buy_datevalue.setPaddingRight(10);
					 buy_datevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 buy_datevalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 buy_datevalue.setBackgroundColor(BaseColor.WHITE);
					 buy_datevalue.setExtraParagraphSpace(5);
					 table.addCell(buy_datevalue);    
					 
					 PdfPCell permanent_addressvalue=new PdfPCell(new Paragraph(supplier.getPermanent_address(), tableBody));
					 permanent_addressvalue.setBorderColor(BaseColor.BLACK);
					 permanent_addressvalue.setPaddingLeft(10);
					 permanent_addressvalue.setPaddingRight(10);
					 permanent_addressvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 permanent_addressvalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 permanent_addressvalue.setBackgroundColor(BaseColor.WHITE);
					 permanent_addressvalue.setExtraParagraphSpace(5);
					 table.addCell(permanent_addressvalue);  
					 
					 PdfPCell temporary_addressvalue=new PdfPCell(new Paragraph(supplier.getTemporary_address(), tableBody));
					 temporary_addressvalue.setBorderColor(BaseColor.BLACK);
					 temporary_addressvalue.setPaddingLeft(10);
					 temporary_addressvalue.setPaddingRight(10);
					 temporary_addressvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 temporary_addressvalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 temporary_addressvalue.setBackgroundColor(BaseColor.WHITE);
					 temporary_addressvalue.setExtraParagraphSpace(5);
					 table.addCell(temporary_addressvalue); 
					 
					 
					 String product_id1=String.valueOf(supplier.getProduct_id());
			    	 PdfPCell product_idvalue=new PdfPCell(new Paragraph(product_id1));
					 product_idvalue.setBorderColor(BaseColor.BLACK);
					 product_idvalue.setPaddingLeft(10);
					 product_idvalue.setPaddingRight(10);
					 product_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 product_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 product_idvalue.setBackgroundColor(BaseColor.WHITE);
					 product_idvalue.setExtraParagraphSpace(5);
					 table.addCell(product_idvalue); 
					 
					 String product_name1=String.valueOf(supplier.getProduct_name());
			    	 PdfPCell product_namevalue=new PdfPCell(new Paragraph(product_name1));
					 product_namevalue.setBorderColor(BaseColor.BLACK);
					 product_namevalue.setPaddingLeft(10);
					 product_namevalue.setPaddingRight(10);
					 product_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
					 product_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
					 product_namevalue.setBackgroundColor(BaseColor.WHITE);
					 product_namevalue.setExtraParagraphSpace(5);
					 table.addCell(product_namevalue);
		    }
		    String value;
		    int count=0, count1=0;
		    double sum=0;
		    int sum1=0;
		    for(Supplier_View supplier1:supplierproducts)
		    {
		    	int supplier_id1=(int) (supplier1.getSupplier_supplier_id()/10);
		    	count=count+1;
		    }
		     value=String.valueOf(count);
			 PdfPCell totalsupplier=new PdfPCell(new Paragraph(value));
			 totalsupplier.setBorderColor(BaseColor.BLACK);
			 totalsupplier.setPaddingLeft(10);
			 totalsupplier.setPaddingRight(10);
			 totalsupplier.setHorizontalAlignment(Element.ALIGN_CENTER);
			 totalsupplier.setVerticalAlignment(Element.ALIGN_CENTER);
			 totalsupplier.setBackgroundColor(BaseColor.WHITE);
			 totalsupplier.setExtraParagraphSpace(5);
			 table.addCell(totalsupplier);    
			 
			 PdfPCell supplier_name1=new PdfPCell(new Paragraph());
			 supplier_name1.setBorderColor(BaseColor.BLACK);
			 supplier_name1.setPaddingLeft(10);
			 supplier_name1.setPaddingRight(10);
			 supplier_name1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 supplier_name1.setVerticalAlignment(Element.ALIGN_CENTER);
			 supplier_name1.setBackgroundColor(BaseColor.WHITE);
			 supplier_name1.setExtraParagraphSpace(5);
			 table.addCell(supplier_name1); 
			 
			 PdfPCell supplier_type1=new PdfPCell(new Paragraph());
			 supplier_type1.setBorderColor(BaseColor.BLACK);
			 supplier_type1.setPaddingLeft(10);
			 supplier_type1.setPaddingRight(10);
			 supplier_type1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 supplier_type1.setVerticalAlignment(Element.ALIGN_CENTER);
			 supplier_type1.setBackgroundColor(BaseColor.WHITE);
			 supplier_type1.setExtraParagraphSpace(5);
			 table.addCell(supplier_type1); 
			 
			 
			 
			 
			 for(Supplier_View supplier2:supplierproducts)
			 {
				 sum1=sum1+supplier2.getQuantity();
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
				
			 for(Supplier_View supplier3:supplierproducts)
			 {
				 sum=sum+supplier3.getCost();
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
			 permanent_address1.setPaddingRight(10);
			 permanent_address1.setHorizontalAlignment(Element.ALIGN_CENTER);
			 permanent_address1.setVerticalAlignment(Element.ALIGN_CENTER);
			 permanent_address1.setBackgroundColor(BaseColor.WHITE);
			 permanent_address1.setExtraParagraphSpace(5);
			 table.addCell(permanent_address1); 
			 
			 
			 for(Supplier_View supplier4:supplierproducts)
			 {
				  	System.out.println(supplier4.getProduct_id());
				  	int product_id1=(int) (supplier4.getProduct_id()/10);
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
			 
			 PdfPCell totalproduct_name=new PdfPCell(new Paragraph());
			 totalproduct_name.setBorderColor(BaseColor.BLACK);
			 totalproduct_name.setPaddingLeft(10);
			 totalproduct_name.setPaddingRight(10);
			 totalproduct_name.setHorizontalAlignment(Element.ALIGN_CENTER);
			 totalproduct_name.setVerticalAlignment(Element.ALIGN_CENTER);
			 totalproduct_name.setBackgroundColor(BaseColor.WHITE);
			 totalproduct_name.setExtraParagraphSpace(5);
			 table.addCell(totalproduct_name);
			 
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
	public double getSumofCost(String supplier_name) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.cost) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
		double total=this.getJdbcTemplate().queryForObject(sql, Double.class, supplier_name);
		return total;
	}

	@Override
	public int getNoofSupplier(String supplier_name) {
		// TODO Auto-generated method stub
		 String sql="SELECT COUNT(h.supplier_supplier_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplier_name);
		return total;
	}

	@Override
	public int getNoofQuantity(String supplier_name) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplier_name);
		return total;
	}

	@Override
	public int getNoofProduct(String supplier_name) {
		// TODO Auto-generated method stub
	  String sql="SELECT COUNT(h.product_product_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE supplier_name=?";
	  int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, supplier_name);	
	  return total;
	}

	@Override
	public Double getSumofCost(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.cost) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Double total=this.getJdbcTemplate().queryForObject(sql, Double.class, buy_date);
		return total;
	}

	@Override
	public int getNoofSupplier(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.supplier_supplier_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public int NoofSupplier() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(supplier_id) FROM supplier";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
	
	
	
	@Override
	public Integer getNoofQuantity(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(h.quantity) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}

	@Override
	public int getNoofProduct(String[] buy_date) {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(h.product_product_id) FROM supplier s INNER JOIN supplier_product h on s.supplier_id=h.supplier_supplier_id WHERE buy_date>=?::Date and buy_date<=?::Date";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, buy_date);
		return total;
	}
	
	
}