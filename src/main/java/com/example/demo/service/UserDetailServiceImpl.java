package com.example.demo.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.LogRecord;
import com.example.demo.model.Usertemp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.demo.model.CustomUserDetails;
import com.example.demo.model.Projectuser;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.UserRolesRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private UserRolesRepository userRolesRepository;
	
	public Projectuser findByUsername(String username)
	{
		return userRepository.findByUsername(username);
	}


	public void updateIntoUserRole(Map map) {
		 userRolesRepository.updateIntoUserRole(map);
	}


	public void insertIntoUserRole(Map map) {
		userRolesRepository.insertIntoUserRole(map);
	}

	public void deleteIntoUserRole(Map map) { userRolesRepository.deleteIntoUserRole(map);
	}

	public void insertIntoLogRecord(Map map)
	{
		userRepository.insertIntoLogRecord(map);
	}

	public void updateIntoLogRecord(Map map) {
		userRepository.updateIntoLogRecord(map);
	}

	public List<LogRecord> getListLogRecord(Date logoutTime) {
		return userRepository.getListLogRecord(logoutTime);
	}

	public void deleteIntoUserTemp(String username) {
		userRepository.deleteIntoUserTemp(username);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		Projectuser user = userRepository.findByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException("Username not found");
		}
		Date loginTime = new Date(System.currentTimeMillis());
				Map<String, Object> map = new HashMap<>();
		            map.put("userId", user.getUserId());
					map.put("username", user.getUsername());
                    map.put("loginTime", loginTime);
                    userRepository.insertIntoLogRecord(map);
					userRepository.insetIntoUserTemp(map);

			List<String> roleuser = userRolesRepository.findRoleByUsername(username);
			System.out.println(username);
			System.out.println(roleuser);
			return new CustomUserDetails(user, roleuser);
	}

//	public  Map getUserTempData()
//	{
//		return userRepository.getUserTempData();
//	}


	public Projectuser getUserIdByUsername(String username) {
		return  userRepository.getUserIdByUsername(username);
	}


	
	
	public void insert(Projectuser projectuser)
	{
		userRepository.Insert(projectuser);
	}
	public Projectuser getUserById(long id)
	{
		return userRepository.getUserById(id);
		
	}
	public List<Projectuser> getAllUserInfo()
	{
		return userRepository.getAllUserInfo();
		
	}

//
	public void updateUser(Projectuser projectuser)
	{
		userRepository.updateIntoUser(projectuser);
	}

	public void deleteUser(long user_id)
	{
		userRepository.deleteUserInfo(user_id);
	}

	

	public int getTotalNoOfQuantity(String product_name) {
		return userRepository.getTotalNoOfQuantity(product_name);
	}

		public int getNoofUsers()
		{
			return userRepository.getNoofUsers();
		}

		
}
