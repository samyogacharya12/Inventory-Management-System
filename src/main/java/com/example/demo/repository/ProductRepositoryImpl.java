package com.example.demo.repository;

import com.example.demo.model.*;
import com.example.demo.model.PurchaseProduct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
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
        getJdbcTemplate().update(sql, new Object[] {product.getProductId(), product.getProductName(), product.getProductType(), product.getPrice(), product.getQuantity(), product.getMagnifactureDate(), product.getExpiryDate(), product.getImage()});
    }

    @Override
    public void insertIntoProductAnalysis(Map map) {
        String sql="INSERT INTO product_analysis "+"(product_id, past_price, present_price, price_increament, price_decreament, reference_product_id) SELECT ?,?,?,?,?,?";
        getJdbcTemplate().update(sql, new Object[] {map.get("productId"), map.get("pastPrice"), map.get("presentPrice"), map.get("priceIncreament"), map.get("priceDecreament"), map.get("referenceProductId")});
    }


    @Override
    public List<PurchaseProduct> getProductByNameTypeAndPurchaseDate( Date purchaseDate) {
        String sql="SELECT p.product_id, p.product_name, p.product_type, p.price, p.quantity, p.magnifacture_date, p.expiry_date, ph.purchase_date FROM product p INNER JOIN purchase ph on p.product_id=ph.product_id WHERE  ph.purchase_date>=? ORDER BY purchase_date DESC LIMIT 1";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql,  purchaseDate);
        List<PurchaseProduct> products=new ArrayList<>();
        for(Map<String, Object> rows:row)
        {
            PurchaseProduct product=new PurchaseProduct();
            product.setProductId((Long) rows.get("product_id"));
            product.setProductName((String) rows.get("product_name"));
            product.setProductType((String) rows.get("product_type"));
            product.setQuantity((Integer) rows.get("quantity"));
            product.setPrice((Double) rows.get("price"));
            product.setMagnifactureDate((Date) rows.get("magnifacture_date"));
            product.setExpiryDate((Date) rows.get("expiry_date"));
            product.setPurchaseDate((Date) rows.get("purchase_date"));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<PurchaseProduct> getProductByNameTypeLessThanPurchaseDate(String productName, String productType, Date purchaseDate) {
        String sql="SELECT p.product_id, p.product_name, p.product_type, p.price, p.quantity, p.magnifacture_date, p.expiry_date, ph.purchase_date FROM product p INNER JOIN purchase ph on p.product_id=ph.product_id WHERE p.is_expired='false' and p.product_name=? and p.product_type=? and ph.purchase_date<? ORDER BY purchase_date DESC LIMIT 1";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql, productName, productType, purchaseDate);
        List<PurchaseProduct> products=new ArrayList<>();
        for(Map<String, Object> rows:row)
        {
            PurchaseProduct product=new PurchaseProduct();
            product.setProductId((Long) rows.get("product_id"));
            product.setProductName((String) rows.get("product_name"));
            product.setProductType((String) rows.get("product_type"));
            product.setQuantity((Integer) rows.get("quantity"));
            product.setPrice((Double) rows.get("price"));
            product.setMagnifactureDate((Date) rows.get("magnifacture_date"));
            product.setExpiryDate((Date) rows.get("expiry_date"));
            product.setPurchaseDate((Date) rows.get("purchase_date"));
            products.add(product);
        }
        return products;
    }

    @Override
    public List<PurchaseProduct> findByProductName(String productName) {
        String sql="SELECT p.product_id, p.product_name, p.product_type, p.price, p.quantity, p.magnifacture_date, p.expiry_date, p.image, ph.purchase_date, ph.username FROM product p INNER JOIN purchase ph on p.product_id=ph.product_id WHERE p.is_expired='false' and p.product_name=? ";
//        String sql="SELECT * FROM product WHERE product_name=?";
        List<Map<String, Object>> row=getJdbcTemplate().queryForList(sql,productName);
        List<PurchaseProduct> product=new ArrayList<PurchaseProduct>();
        for(Map<String, Object> rows:row)
        {
             PurchaseProduct obj=new PurchaseProduct();
            obj.setProductId((Long)rows.get("product_id"));
            obj.setProductName((String) rows.get("product_name"));
            obj.setProductType((String) rows.get("product_type"));
            obj.setQuantity((Integer) rows.get("quantity"));
            obj.setPrice((Double) rows.get("price"));
            obj.setMagnifactureDate((Date) rows.get("magnifacture_date"));
            obj.setExpiryDate((Date)rows.get("expiry_date"));
            obj.setImage((String)rows.get("image"));
            obj.setPurchaseDate((Date) rows.get("purchase_date"));
            obj.setUsername((String) rows.get("username"));
            product.add(obj);
        }
        return product;
    }

    @Override
    public void updateExpiredProduct(Product product) {
        String sql="UPDATE product SET is_expired=? WHERE product_id=?";
        this.getJdbcTemplate().update(sql, new Object[] {product.getIsExpired(), product.getProductId()});
    }

    @Override
    public Product getProductById(long productId) {
        String sql="SELECT * FROM product WHERE product_id=?";
        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product>(Product.class);
        Product product=getJdbcTemplate().queryForObject(sql, rowmapper, productId);
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
    public List<PurchaseProduct> getAllProductInfo() {
         String sql="SELECT p.product_id, p.product_name, p.product_type, p.price, p.quantity, p.magnifacture_date, p.expiry_date, p.image, ph.purchase_date, ph.username FROM product p INNER JOIN purchase ph on p.product_id=ph.product_id WHERE p.is_expired='false'";
//        String sql="SELECT * FROM product WHERE is_expired='false' ";
        RowMapper<PurchaseProduct> rowmapper=new BeanPropertyRowMapper<PurchaseProduct> (PurchaseProduct.class);
        return this.getJdbcTemplate().query(sql, rowmapper);
    }
    @Override
    public List<ProductAnalysis> getAllProductAnalysis() {
        String sql="SELECT * FROM product_analysis";
        RowMapper<ProductAnalysis> rowMapper=new BeanPropertyRowMapper<>(ProductAnalysis.class);
        return this.getJdbcTemplate().query(sql, rowMapper);
    }


    @Override
    public void updateIntoProduct(Product product) {
        String sql="UPDATE product SET product_name=?,product_type=?, price=?,quantity=?,magnifacture_date=?, expiry_date=?, image=? WHERE product_id=?";
        getJdbcTemplate().update(sql, new Object[] {product.getProductName(),product.getProductType(),product.getPrice(),product.getQuantity(),product.getMagnifactureDate(),product.getExpiryDate(),product.getImage(),product.getProductId()});
    }

    @Override
    public void deleteProductInfo(long productId) {
        String sql="DELETE FROM product WHERE product_id=?";
        getJdbcTemplate().update(sql, productId);
    }

//    @Override
//    public Product getQuantityById(long productId) {
//        String sql="SELECT * FROM product WHERE product_id=?";
//        RowMapper<Product> rowmapper=new BeanPropertyRowMapper<Product>(Product.class);
//        Product product= this.getJdbcTemplate().queryForObject(sql, rowmapper, productId);
//         return  product;
//    }

    @Override
    public CustomerProduct getQuantityByCustomerId(long customerId, long productId) {
        String sql="SELECT * FROM customer_product WHERE customer_customer_id=? and product_product_id=?";
        RowMapper<CustomerProduct> rowmapper=new BeanPropertyRowMapper<CustomerProduct>(CustomerProduct.class);
        CustomerProduct customerproduct=getJdbcTemplate().queryForObject(sql,rowmapper, customerId, productId);
        return customerproduct;
    }
//    @Override
//    public PurchaseProduct getProductByNameTypeAndPurchaseDate(String productName, String productType, Date purchaseDate) {
//        String sql="SELECT p.product_id, p.product_name, p.product_type, p.price, p.quantity, p.magnifacture_date, p.expiry_date, ph.purchase_date FROM product p INNER JOIN purchase ph on p.product_id=ph.product_id WHERE p.is_expired='false' and p.product_name=? and p.product_type=? and ph.purchase_date>=?";
//        RowMapper<PurchaseProduct> rowMapper=new BeanPropertyRowMapper<>(PurchaseProduct.class);
//        PurchaseProduct purchaseProduct=getJdbcTemplate().queryForObject(sql, rowMapper, productName, productType, purchaseDate);
//        return purchaseProduct;
//    }


    @Override
    public int getTotalNoOfProduct() {
        String sql="SELECT COUNT(product_id) FROM product WHERE is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public double getSumOfPrice() {
        String sql="SELECT SUM(price) FROM product WHERE is_expired='false'";
        double total=this.getJdbcTemplate().queryForObject(sql, Double.class);
        return total;
    }

    @Override
    public int getTotalNoOfQuantity() {
        String sql="SELECT SUM(quantity) FROM product WHERE is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class);
        return total;
    }

    @Override
    public int getTotalNoOfProduct(String productName) {
        String sql="SELECT COUNT(product_id) FROM product WHERE product_name=? and is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, productName);
        return total;
    }

    @Override
    public Integer countProductByNameAndType(String productName, String productType) {
        String sql="SELECT COUNT(ph.product_id) FROM purchase ph  INNER JOIN product p on p.product_id=ph.product_id WHERE p.is_expired='false'  and p.product_name=? and p.product_type=?";
        Integer total=this.getJdbcTemplate().queryForObject(sql, Integer.class, productName, productType);
        return total;
    }


    @Override
    public double getSumOfPrice(String productName) {
        String sql="SELECT SUM(price) FROM product WHERE product_name=? and is_expired='false'";
        double total=this.getJdbcTemplate().queryForObject(sql, Double.class, productName);
        return total;
    }

//    @Override
//    public double getSumOfPrice(String product_name) {
//        String sql="SELECT SUM(price) FROM product WHERE product_name=?";
//        double total=this.getJdbcTemplate().queryForObject(sql, Double.class, product_name);
//        return total;
//    }

    @Override
    public int getTotalNoOfQuantity(String productName) {
        String sql="SELECT SUM(quantity) FROM product WHERE product_name=? and is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, productName);
        return total;
    }

    @Override
    public List<String> getExpiredProduct(String expiryDate) {
        String sql="SELECT product_name FROM product WHERE expiry_date<=?::Date and is_expired='false'";
        return this.getJdbcTemplate().queryForList(sql, new Object[] {expiryDate}, String.class);
    }

    @Override
    public int getNoOfExpiredProduct(String expiryDate) {
        String sql="SELECT COUNT(product_id) FROM product WHERE expiry_date<=?::Date and is_expired='false'";
        int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, expiryDate);
        return total;
    }
}
