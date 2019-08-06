package com.example.demo.repository;

import com.example.demo.model.Purchase;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.util.Map;

@Repository
public class PurchaseRepositoryImpl extends JdbcDaoSupport implements PurchaseRepository {

    @Autowired
    DataSource datasource;

    @PostConstruct
    public void initialize()
    {
        setDataSource(datasource);
    }

    @Override
    public void insertIntoPurchase(Map map) {
        String sql="INSERT INTO purchase "+"(product_id, purchase_date, user_id, username) SELECT ?,?,?,?";
        this.getJdbcTemplate().update(sql, new Object[] {map.get("productId"), map.get("purchaseDate"), map.get("userId"), map.get("username")});
    }
}
