package com.example.demo.repository;

import com.example.demo.model.Customer_Product;
import com.example.demo.model.Product;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import org.apache.poi.hssf.usermodel.*;
import org.apache.poi.hssf.util.HSSFColor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Repository
public class ProductRepositoryImpl extends JdbcDaoSupport implements ProductRepository {


    @Autowired
    DataSource datasource;

    @PostConstruct
    public void initialize()
    {
        setDataSource(datasource);
    }

    @Override
    public void insertIntoProduct(Product product) {
        String sql="INSERT INTO product "+"(product_id, product_name,product_type,price,quantity,magnifacture_date,expiry_date, image) SELECT ?,?,?,?,?,?::Date,?::Date,?";
        getJdbcTemplate().update(sql, new Object[] {product.getProduct_id(), product.getProduct_name(), product.getProduct_type(), product.getPrice(), product.getQuantity(), product.getMagnifacture_date(), product.getExpiry_date(), product.getImage()});
    }

    @Override
    public List<Product> findByProductName(String product_name) {
        String sql="SELECT * FROM product WHERE product_name=?";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql,product_name);
        List<Product> product=new ArrayList<Product>();
        for(Map<String, Object> rows:row)
        {
            Product obj=new Product();
            obj.setProduct_id((Long)rows.get("product_id"));
            obj.setProduct_name((String) rows.get("product_name"));
            obj.setProduct_type((String) rows.get("product_type"));
            obj.setQuantity((Integer) rows.get("quantity"));
            obj.setPrice((Double) rows.get("price"));
            obj.setMagnifacture_date((Date) rows.get("magnifacture_date"));
            obj.setExpiry_date((Date)rows.get("expiry_date"));
            obj.setImage((String)rows.get("image"));
            product.add(obj);
        }
        return product;
    }

    @Override
    public void updateExpiredProduct(Product product) {
        String sql="UPDATE product SET is_expired=? WHERE product_id=?";
        this.getJdbcTemplate().update(sql, new Object[] {product.getIs_expired(), product.getProduct_id()});
    }

    @Override
    public Product getProductById(long product_id) {
        String sql="SELECT * FROM product WHERE product_id=?";
        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product>(Product.class);
        Product product=getJdbcTemplate().queryForObject(sql, rowmapper, product_id);
        return product;

    }

//    @Override
//    public Product getproductbyid(long product_id) {
//        String sql="SELECT * FROM product WHERE product_id=?";
//        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product>(Product.class);
//        Product product=getJdbcTemplate().queryForObject(sql, rowmapper, product_id);
//        return product;
//    }

    @Override
    public List<Product> getAllProductInfo() {
        String sql="SELECT * FROM product WHERE is_expired='false' ";
        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product> (Product.class);
        return this.getJdbcTemplate().query(sql, rowmapper);
    }

    @Override
    public void updateIntoProduct(Product product) {
        String sql="UPDATE product SET product_name=?,product_type=?, price=?,quantity=?,magnifacture_date=?, expiry_date=?, image=? WHERE product_id=?";
        getJdbcTemplate().update(sql, new Object[] {product.getProduct_name(),product.getProduct_type(),product.getPrice(),product.getQuantity(),product.getMagnifacture_date(),product.getExpiry_date(),product.getImage(),product.getProduct_id()});
    }

    @Override
    public void deleteProductInfo(long product_id) {
        String sql="DELETE FROM product WHERE product_id=?";
        getJdbcTemplate().update(sql, product_id);
    }

    @Override
    public Product getQuantityById(long id) {
        String sql="SELECT * FROM product WHERE product_id=?";
        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product>(Product.class);
        Product product=getJdbcTemplate().queryForObject(sql, rowmapper, id);
        return product;
    }

    @Override
    public Customer_Product getQuantityByCustomerId(long customer_id, long product_id) {
        String sql="SELECT * FROM customer_product WHERE customer_customer_id=? and product_product_id=?";
        RowMapper<Customer_Product> rowmapper=new BeanPropertyRowMapper<Customer_Product>(Customer_Product.class);
        Customer_Product customerproduct=getJdbcTemplate().queryForObject(sql,rowmapper, customer_id, product_id);
        return customerproduct;
    }

    @Override
    public int getTotalNoOfProduct() {
        String sql="SELECT COUNT(product_id) FROM product";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public double getSumOfPrice() {
        String sql="SELECT SUM(price) FROM product";
        double total=this.getJdbcTemplate().queryForObject(sql, Double.class);
        return total;
    }

    @Override
    public int getTotalNoOfQuantity() {
        String sql="SELECT SUM(quantity) FROM product";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public int getTotalNoOfProduct(String product_name) {
        String sql="SELECT COUNT(product_id) FROM product WHERE product_name=?";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, product_name);
        return total;
    }

    @Override
    public double getSumOfPrice(String product_name) {
        String sql="SELECT SUM(price) FROM product WHERE product_name=?";
        double total=this.getJdbcTemplate().queryForObject(sql, Double.class, product_name);
        return total;
    }

//    @Override
//    public double getSumOfprice(String product_name) {
//        String sql="SELECT SUM(price) FROM product WHERE product_name=?";
//        double total=this.getJdbcTemplate().queryForObject(sql, Double.class, product_name);
//        return total;
//    }

    @Override
    public int getTotalNoOfQuantity(String product_name) {
        String sql="SELECT SUM(quantity) FROM product WHERE product_name=?";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, product_name);
        return total;
    }

    @Override
    public List<String> getExpiredProduct(String expiry_date) {
        String sql="SELECT product_name FROM product WHERE expiry_date<=?::Date and is_expired='false'";
        return this.getJdbcTemplate().queryForList(sql, new Object[] {expiry_date}, String.class);
    }

    @Override
    public int getNoOfExpiredProduct(String expiry_date) {
        String sql="SELECT COUNT(product_id) FROM product WHERE expiry_date<=?::Date and is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expiry_date);
        return total;
    }

    @Override
    public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
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
            PdfWriter writer=PdfWriter.getInstance(document, new FileOutputStream(file+"/"+"products"+".pdf"));
            document.open();

            Font mainfont= FontFactory.getFont("Arial", 10, BaseColor.BLACK);

            Paragraph paragraph=new Paragraph("All Products", mainfont);
            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(50);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            PdfPTable table=new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(10f);
            table.setSpacingAfter(10);

            Font tableHeader=FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody=FontFactory.getFont("Arial", 9, BaseColor.BLACK);

            float[] columnwidths= {2f, 2f, 2f, 2f, 2f, 2f, 2f};
            table.setWidths(columnwidths);

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

            PdfPCell product_type=new PdfPCell(new Paragraph("product_type", tableHeader));
            product_type.setBorderColor(BaseColor.BLACK);
            product_type.setPaddingLeft(10);
            product_type.setPaddingRight(10);
            product_type.setHorizontalAlignment(Element.ALIGN_CENTER);
            product_type.setVerticalAlignment(Element.ALIGN_CENTER);
            product_type.setBackgroundColor(BaseColor.GRAY);
            product_type.setExtraParagraphSpace(5);
            table.addCell(product_type);

            PdfPCell price=new PdfPCell(new Paragraph("price", tableHeader));
            price.setBorderColor(BaseColor.BLACK);
            price.setPaddingLeft(10);
            price.setPaddingRight(10);
            price.setHorizontalAlignment(Element.ALIGN_CENTER);
            price.setVerticalAlignment(Element.ALIGN_CENTER);
            price.setBackgroundColor(BaseColor.GRAY);
            price.setExtraParagraphSpace(5);
            table.addCell(price);


            PdfPCell quantity=new PdfPCell(new Paragraph("quantity", tableHeader));
            quantity.setBorderColor(BaseColor.BLACK);
            quantity.setPaddingLeft(10);
            quantity.setPaddingRight(10);
            quantity.setHorizontalAlignment(Element.ALIGN_CENTER);
            quantity.setVerticalAlignment(Element.ALIGN_CENTER);
            quantity.setBackgroundColor(BaseColor.GRAY);
            quantity.setExtraParagraphSpace(5);
            table.addCell(quantity);

            PdfPCell magnifacture_date=new PdfPCell(new Paragraph("magnifacture_date", tableHeader));
            magnifacture_date.setBorderColor(BaseColor.BLACK);
            magnifacture_date.setPaddingLeft(10);
            magnifacture_date.setPaddingRight(10);
            magnifacture_date.setHorizontalAlignment(Element.ALIGN_CENTER);
            magnifacture_date.setVerticalAlignment(Element.ALIGN_CENTER);
            magnifacture_date.setBackgroundColor(BaseColor.GRAY);
            magnifacture_date.setExtraParagraphSpace(5);
            table.addCell(magnifacture_date);

            PdfPCell expiry_date=new PdfPCell(new Paragraph("expiry_date", tableHeader));
            expiry_date.setBorderColor(BaseColor.BLACK);
            expiry_date.setPaddingLeft(10);
            expiry_date.setPaddingRight(10);
            expiry_date.setHorizontalAlignment(Element.ALIGN_CENTER);
            expiry_date.setVerticalAlignment(Element.ALIGN_CENTER);
            expiry_date.setBackgroundColor(BaseColor.GRAY);
            expiry_date.setExtraParagraphSpace(5);
            table.addCell(expiry_date);

            for(Product product:products)
            {
                System.out.println(product.getProduct_id());
                String product_id1=String.valueOf(product.getProduct_id());
                PdfPCell product_idvalue=new PdfPCell(new Paragraph(product_id1, tableBody));
                product_idvalue.setBorderColor(BaseColor.BLACK);
                product_idvalue.setPaddingLeft(10);
                product_idvalue.setPaddingRight(10);
                product_idvalue.setHorizontalAlignment(Element.ALIGN_CENTER);
                product_idvalue.setVerticalAlignment(Element.ALIGN_CENTER);
                product_idvalue.setBackgroundColor(BaseColor.WHITE);
                product_idvalue.setExtraParagraphSpace(5);
                table.addCell(product_idvalue);

                System.out.println(product.getProduct_name());
                String product_name1=String.valueOf(product.getProduct_name());
                PdfPCell product_namevalue=new PdfPCell(new Paragraph(product_name1, tableBody));
                product_namevalue.setBorderColor(BaseColor.BLACK);
                product_namevalue.setPaddingLeft(10);
                product_namevalue.setPaddingRight(10);
                product_namevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
                product_namevalue.setVerticalAlignment(Element.ALIGN_CENTER);
                product_namevalue.setBackgroundColor(BaseColor.WHITE);
                product_namevalue.setExtraParagraphSpace(5);
                table.addCell(product_namevalue);

                System.out.println(product.getProduct_type());
                PdfPCell product_typevalue=new PdfPCell(new Paragraph(product.getProduct_type(), tableBody));
                product_typevalue.setBorderColor(BaseColor.BLACK);
                product_typevalue.setPaddingLeft(10);
                product_typevalue.setPaddingRight(10);
                product_typevalue.setHorizontalAlignment(Element.ALIGN_CENTER);
                product_typevalue.setVerticalAlignment(Element.ALIGN_CENTER);
                product_typevalue.setBackgroundColor(BaseColor.WHITE);
                product_typevalue.setExtraParagraphSpace(5);
                table.addCell(product_typevalue);

                String price1=String.valueOf(product.getPrice());
                PdfPCell price_value=new PdfPCell(new Paragraph(price1, tableBody));
                price_value.setBorderColor(BaseColor.BLACK);
                price_value.setPaddingLeft(10);
                price_value.setPaddingRight(10);
                price_value.setHorizontalAlignment(Element.ALIGN_CENTER);
                price_value.setVerticalAlignment(Element.ALIGN_CENTER);
                price_value.setBackgroundColor(BaseColor.WHITE);
                price_value.setExtraParagraphSpace(5);
                table.addCell(price_value);

                String quantity1=String.valueOf(product.getQuantity());
                PdfPCell quantity_value=new PdfPCell(new Paragraph(quantity1, tableBody));
                quantity_value.setBorderColor(BaseColor.BLACK);
                quantity_value.setPaddingLeft(10);
                quantity_value.setPaddingRight(10);
                quantity_value.setHorizontalAlignment(Element.ALIGN_CENTER);
                quantity_value.setVerticalAlignment(Element.ALIGN_CENTER);
                quantity_value.setBackgroundColor(BaseColor.WHITE);
                quantity_value.setExtraParagraphSpace(5);
                table.addCell(quantity_value);


                PdfPCell magnifacture_date1=new PdfPCell(new Paragraph(product.getMagnifacture_date().toLocaleString()));
                System.out.println(product.getMagnifacture_date());
                magnifacture_date1.setBorderColor(BaseColor.BLACK);
                magnifacture_date1.setPaddingLeft(10);
                magnifacture_date1.setPaddingRight(10);
                magnifacture_date1.setHorizontalAlignment(Element.ALIGN_CENTER);
                magnifacture_date1.setVerticalAlignment(Element.ALIGN_CENTER);
                magnifacture_date1.setBackgroundColor(BaseColor.WHITE);
                magnifacture_date1.setExtraParagraphSpace(5);
                table.addCell(magnifacture_date1);

                PdfPCell expiry_date1=new PdfPCell(new Paragraph(product.getExpiry_date().toLocaleString()));
                expiry_date1.setBorderColor(BaseColor.BLACK);
                expiry_date1.setPaddingLeft(10);
                expiry_date1.setPaddingRight(10);
                expiry_date1.setHorizontalAlignment(Element.ALIGN_CENTER);
                expiry_date1.setVerticalAlignment(Element.ALIGN_CENTER);
                expiry_date1.setBackgroundColor(BaseColor.WHITE);
                expiry_date1.setExtraParagraphSpace(5);
                table.addCell(expiry_date1);
            }
            String value;
            int count=0;
            double sum=0;
            int sum1=0;

            for(Product product:products)
            {
                int product_id1=(int) product.getProduct_id();
                count=count+1;
            }
            value=String.valueOf(count);
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

            PdfPCell product_type1=new PdfPCell(new Paragraph());
            product_type1.setBorderColor(BaseColor.BLACK);
            product_type1.setPaddingLeft(10);
            product_type1.setPaddingRight(10);
            product_type1.setHorizontalAlignment(Element.ALIGN_CENTER);
            product_type1.setVerticalAlignment(Element.ALIGN_CENTER);
            product_type1.setBackgroundColor(BaseColor.WHITE);
            product_type1.setExtraParagraphSpace(5);
            table.addCell(product_type1);


            for(Product product1:products)
            {
                sum=sum+product1.getPrice();
            }
            value=String.valueOf(sum);
            PdfPCell totalprice=new PdfPCell(new Paragraph(value));
            totalprice.setBorderColor(BaseColor.BLACK);
            totalprice.setPaddingLeft(10);
            totalprice.setPaddingRight(10);
            totalprice.setHorizontalAlignment(Element.ALIGN_CENTER);
            totalprice.setVerticalAlignment(Element.ALIGN_CENTER);
            totalprice.setBackgroundColor(BaseColor.WHITE);
            totalprice.setExtraParagraphSpace(5);
            table.addCell(totalprice);

            for(Product product2:products)
            {
                sum1=sum1+product2.getQuantity();
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


            PdfPCell magnifacture_date1=new PdfPCell(new Paragraph());
            magnifacture_date1.setBorderColor(BaseColor.BLACK);
            magnifacture_date1.setPaddingLeft(10);
            magnifacture_date1.setPaddingRight(10);
            magnifacture_date1.setHorizontalAlignment(Element.ALIGN_CENTER);
            magnifacture_date1.setVerticalAlignment(Element.ALIGN_CENTER);
            magnifacture_date1.setBackgroundColor(BaseColor.WHITE);
            magnifacture_date1.setExtraParagraphSpace(5);
            table.addCell(magnifacture_date1);

            PdfPCell expiry_date1=new PdfPCell(new Paragraph());
            expiry_date1.setBorderColor(BaseColor.BLACK);
            expiry_date1.setPaddingLeft(10);
            expiry_date1.setPaddingRight(10);
            expiry_date1.setHorizontalAlignment(Element.ALIGN_CENTER);
            expiry_date1.setVerticalAlignment(Element.ALIGN_CENTER);
            expiry_date1.setBackgroundColor(BaseColor.WHITE);
            expiry_date1.setExtraParagraphSpace(5);
            table.addCell(expiry_date1);


            document.add(table);
            document.close();
            writer.close();
            return true;

        } catch (Exception e) {
            // TODO Auto-generated catch block
            return false;
        }
    }

    @Override
    public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        String filepath=context.getRealPath("/resources/reports");
        File file=new File(filepath);
        boolean exists=new File(filepath).exists();
        if(!exists)
        {
            new File(filepath).mkdirs();
        }
        try
        {
            FileOutputStream outputstream=new FileOutputStream(file+"/"+"products"+".xls");
            HSSFWorkbook workbook=new HSSFWorkbook();
            HSSFSheet worksheet=workbook.createSheet("Products");
            worksheet.setDefaultColumnWidth(30);

            HSSFCellStyle headerCellStyle=workbook.createCellStyle();
            headerCellStyle.setFillForegroundColor(HSSFColor.BLUE.index);
            headerCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

            HSSFRow headerRow=worksheet.createRow(0);
            HSSFCell product_id=headerRow.createCell(0);
            product_id.setCellValue("Product Id");
            product_id.setCellStyle(headerCellStyle);

            HSSFCell product_name=headerRow.createCell(1);
            product_name.setCellValue("Product Name");
            product_name.setCellStyle(headerCellStyle);

            HSSFCell product_type=headerRow.createCell(2);
            product_type.setCellValue("Product Type");
            product_type.setCellStyle(headerCellStyle);

            HSSFCell quantity=headerRow.createCell(3);
            quantity.setCellValue("Quantity");
            quantity.setCellStyle(headerCellStyle);

            HSSFCell price=headerRow.createCell(4);
            price.setCellValue("Price");
            price.setCellStyle(headerCellStyle);


            HSSFCell magnifacture_date=headerRow.createCell(5);
            magnifacture_date.setCellValue("Magnifacture Date");
            magnifacture_date.setCellStyle(headerCellStyle);

            HSSFCell expiry_date=headerRow.createCell(6);
            expiry_date.setCellValue("Expiry Date");
            expiry_date.setCellStyle(headerCellStyle);


            int i=1;
            for(Product product:products)
            {
                HSSFRow bodyRow=worksheet.createRow(i);
                HSSFCellStyle bodycellstyle=workbook.createCellStyle();
                bodycellstyle.setFillBackgroundColor(HSSFColor.WHITE.index);

                HSSFCell product_idValue=bodyRow.createCell(0);
                product_idValue.setCellValue(product.getProduct_id());
                System.out.println(product.getProduct_id());
                product_idValue.setCellStyle(bodycellstyle);

                HSSFCell product_nameValue=bodyRow.createCell(1);
                product_nameValue.setCellValue(product.getProduct_name());
                System.out.println(product.getProduct_name());
                product_nameValue.setCellStyle(bodycellstyle);

                HSSFCell product_typeValue=bodyRow.createCell(2);
                product_typeValue.setCellValue(product.getProduct_type());
                product_typeValue.setCellStyle(bodycellstyle);

                HSSFCell priceValue=bodyRow.createCell(3);
                priceValue.setCellValue(product.getPrice());
                System.out.println(product.getProduct_type());
                priceValue.setCellStyle(bodycellstyle);


                HSSFCell quantityValue=bodyRow.createCell(4);
                System.out.println(quantityValue);
                quantityValue.setCellValue(product.getQuantity());
                quantityValue.setCellStyle(bodycellstyle);


                HSSFCell magnifacture_dateValue=bodyRow.createCell(5);
                magnifacture_dateValue.setCellValue(product.getMagnifacture_date());
                magnifacture_dateValue.setCellStyle(bodycellstyle);


                HSSFCell expiry_dateValue=bodyRow.createCell(6);
                expiry_dateValue.setCellValue(product.getExpiry_date());
                expiry_dateValue.setCellStyle(bodycellstyle);

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
}
