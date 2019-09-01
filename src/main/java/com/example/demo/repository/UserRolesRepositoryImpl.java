package com.example.demo.repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

@Repository
public class UserRolesRepositoryImpl extends JdbcDaoSupport implements UserRolesRepository {

	@Autowired
	DataSource datasource;
	
	@PostConstruct
	public void initialize()
	{
		setDataSource(datasource);
	}

	@Override
	public void insertIntoUserRole(Map map) {
		String sql="INSERT INTO user_role(user_id,  role_id) SELECT ?,?";
		getJdbcTemplate().update(sql, new Object[]{map.get("user_id"), map.get("role_id")});
	}

	@Override
	public void updateIntoUserRole(Map map) {
       String sql="UPDATE  user_role SET role_id=? WHERE user_id=?";
       getJdbcTemplate().update(sql, new Object[]{map.get("role_id"), map.get("user_id")});
	}

	@Override
	public void deleteIntoUserRole(Map map) {
		String sql="DELETE FROM user_role WHERE role_id=?";
		getJdbcTemplate().update(sql, map.get("user_id"));
	}

	@Override
	public List<String> findRoleByUsername(String username) {
		// TODO Auto-generated method stub
		String sql="SELECT a.role FROM role a, inventoryuser b WHERE b.username=? and a.role_id=b.user_role_id";
		System.out.println(username);
	    return getJdbcTemplate().queryForList(sql, new Object[] {username}, String.class);
}	
	
}
