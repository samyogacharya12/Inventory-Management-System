package com.example.demo.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;

import com.example.demo.model.LogRecord;
import com.example.demo.model.Usertemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.support.JdbcDaoSupport;
import org.springframework.stereotype.Repository;

import com.example.demo.model.Projectuser;

@Repository
public class UserRepositoryImpl extends JdbcDaoSupport implements UserRepository{

    @Autowired	
	DataSource datasource;
    
    @PostConstruct
    public void initialize()
    {
    	setDataSource(datasource);
    }
	@Override
	public Projectuser findByUsername(String username) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM inventoryuser WHERE username=?";
		return getJdbcTemplate().queryForObject(sql,new Object[] {username}, new RowMapper<Projectuser>()
				{
					@Override
					public Projectuser mapRow(ResultSet rs, int arg1) throws SQLException {
						// TODO Auto-generated method stub
					    Projectuser user=new Projectuser();
					    user.setUserId(rs.getLong("user_id"));
					    user.setUsername(rs.getString("username"));
					    user.setPassword(rs.getString("password"));
					    user.setFirstName(rs.getString("first_name"));
					    user.setMiddleName(rs.getString("middle_name"));
					    user.setLastName(rs.getString("last_name"));
					    user.setTemporaryAdddress(rs.getString("permanent_address"));
					    user.setPermanentAddress(rs.getString("temporary_adddress"));
					    user.setEmail(rs.getString("email"));
					    user.setPhoneNumber(rs.getInt("phone_number"));
					    user.setUserRoleId(rs.getInt("user_role_id"));
					    user.setCountry(rs.getString("country"));
					    user.setGender(rs.getString("gender"));
					    user.setImage(rs.getString("image"));
					    user.setLandlineNumber(rs.getInt("landline_number"));
					    user.setDateOfBirth(rs.getDate("date_of_birth"));
					    user.setJoinedDate(rs.getDate("joined_date"));
						return user;
					}
				});

	}
	@Override
	public List<LogRecord> getListLogRecord(Date logoutTime) {
        String sql="SELECT * FROM log_record WHERE login_time<=? ORDER BY login_time DESC LIMIT 1";
        List<Map<String, Object>> rows=getJdbcTemplate().queryForList(sql, logoutTime);
        List<LogRecord> logRecords=new ArrayList<>();
		for(Map<String, Object> row:rows)
		{
			LogRecord logRecord=new LogRecord();
			logRecord.setLoginId((int)row.get("login_id"));
			logRecord.setUserId((long) row.get("user_id"));
			logRecord.setUserName((String) row.get("username"));
			logRecord.setLoginTime((Date) row.get("login_time"));
			logRecord.setLogoutTime((Date) row.get("logout_time"));
			logRecords.add(logRecord);
		}
		return logRecords;
	}

	@Override
	public Projectuser getUserIdByUsername(String username) {

    	String sql="SELECT user_id FROM  inventoryuser WHERE username=?";
    	RowMapper<Projectuser> rowMapper=new BeanPropertyRowMapper<>(Projectuser.class);
    	Projectuser projectuser=getJdbcTemplate().queryForObject(sql, rowMapper, username);
    	return  projectuser;
	}
	
	@Override
	public void Insert(Projectuser projectuser) {
		// TODO Auto-generated method stub
		String sql="INSERT INTO inventoryuser "+"(user_id, username, password, first_name, middle_name, last_name, permanent_address, temporary_adddress, email, phone_number,country,user_role_id, gender,image, landline_number, date_of_birth, joined_date) SELECT ?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?";
		getJdbcTemplate().update(sql, new Object[] {projectuser.getUserId(), projectuser.getUsername(), projectuser.getPassword(), projectuser.getFirstName(), projectuser.getMiddleName(), projectuser.getLastName(), projectuser.getPermanentAddress(), projectuser.getTemporaryAdddress(),projectuser.getEmail(), projectuser.getPhoneNumber(), projectuser.getCountry(), projectuser.getUserRoleId(), projectuser.getGender(),projectuser.getImage(), projectuser.getLandlineNumber(), projectuser.getDateOfBirth(), projectuser.getJoinedDate()});
	}
	@Override
	public Projectuser getUserById(long id) {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM inventoryuser WHERE user_id=?";
		return getJdbcTemplate().queryForObject(sql,new Object[] {id}, new RowMapper<Projectuser>()
		{
			
	       
			@Override
			public Projectuser mapRow(ResultSet rs, int arg1) throws SQLException {
				// TODO Auto-generated method stub
			    Projectuser user=new Projectuser();
			    user.setUserId(rs.getLong("user_id"));
			    user.setUsername(rs.getString("username"));
			    user.setPassword(rs.getString("password"));
			    user.setFirstName(rs.getString("first_name"));
			    user.setMiddleName(rs.getString("middle_name"));
			    user.setLastName(rs.getString("last_name"));
			    user.setTemporaryAdddress(rs.getString("temporary_adddress"));
			    user.setPermanentAddress(rs.getString("permanent_address"));
			    user.setEmail(rs.getString("email"));
			    user.setPhoneNumber(rs.getInt("phone_number"));
			    user.setUserRoleId(rs.getInt("user_role_id"));
			    user.setCountry(rs.getString("country"));
			    user.setGender(rs.getString("gender"));
			    user.setImage(rs.getString("image"));
			    user.setLandlineNumber(rs.getInt("landline_number"));
			    user.setDateOfBirth(rs.getDate("date_of_birth"));
			    user.setJoinedDate(rs.getDate("joined_date"));
				return user;
			}
		});
	}
	@Override
	public List<Projectuser> getAllUserInfo() {
		// TODO Auto-generated method stub
		String sql="SELECT * FROM inventoryuser";
		RowMapper<Projectuser> rowmapper=new BeanPropertyRowMapper<Projectuser>(Projectuser.class);
		return this.getJdbcTemplate().query(sql, rowmapper);
	}
	
	@Override
	public void updateIntoUser(Projectuser projectuser) {
		// TODO Auto-generated method stub
	  String sql="UPDATE inventoryuser SET username=?, password=?,first_name=?,middle_name=?,last_name=?,temporary_adddress=?, permanent_address=?,email=?, gender=?, phone_number=?, landline_number=?,user_role_id=?, date_of_birth=?, joined_date=?, image=? WHERE user_id=?";	
      getJdbcTemplate().update(sql, new Object[] {projectuser.getUsername(), projectuser.getPassword(), projectuser.getFirstName(), projectuser.getMiddleName(), projectuser.getLastName(), projectuser.getTemporaryAdddress(), projectuser.getPermanentAddress(),projectuser.getEmail(), projectuser.getGender(), projectuser.getPhoneNumber(), projectuser.getLandlineNumber(), projectuser.getUserRoleId(), projectuser.getDateOfBirth(), projectuser.getJoinedDate(), projectuser.getImage(), projectuser.getUserId()});
	}

	@Override
	public void insetIntoUserTemp(Map map) {
		String sql="INSERT INTO user_temp(username) SELECT ?";
		getJdbcTemplate().update(sql, new Object[] {map.get("username")});
	}
	@Override
	public void insertIntoLogRecord(Map map) {
		String sql="INSERT INTO log_record(user_id, username, login_time, logout_time) SELECT ?,?,?,?";
		getJdbcTemplate().update(sql, new Object[]{map.get("userId"), map.get("username"), map.get("loginTime"), map.get("logoutTime")});
	}

	@Override
	public void updateIntoLogRecord(Map map) {
      String sql="UPDATE log_record SET user_id=?, username=?, login_time=?, logout_time=? WHERE login_id=?";
      getJdbcTemplate().update(sql, new Object[]{map.get("userId"), map.get("username"), map.get("loginTime"), map.get("logoutTime"), map.get("loginId")});
	}



//	@Override
//	public Map getUserTempData() {
//		String sql="SELECT * FROM user_temp";
//	    Map map=this.getJdbcTemplate().queryForMap(sql);
//	    return map;
//	}

	@Override
	public void deleteIntoUserTemp(String username) {
		String sql="DELETE FROM user_temp WHERE username=?";
		getJdbcTemplate().update(sql,username);
	}

	@Override
	public void deleteUserInfo(long userId) {
		// TODO Auto-generated method stub
	  String sql="DELETE FROM inventoryuser WHERE user_id=?";
	  getJdbcTemplate().update(sql, userId);
	}


	@Override
	public int getTotalNoOfQuantity(String product_name) {
		// TODO Auto-generated method stub
		String sql="SELECT SUM(quantity) FROM product WHERE product_name=?";
		int total=this.getJdbcTemplate().queryForObject(sql, Integer.class, product_name);
		return total;
	}
	
	@Override
	public int getNoofUsers() {
		// TODO Auto-generated method stub
		String sql="SELECT COUNT(user_id) FROM inventoryuser";
		int total=getJdbcTemplate().queryForObject(sql, Integer.class);
		return total;
	}
}