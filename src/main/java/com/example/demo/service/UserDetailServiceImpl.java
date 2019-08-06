package com.example.demo.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
	
	
//	public void updateExpiredProduct(Product product)
//	{
//		userRepository.updateExpiredProduct(product);
//	}

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
				Map<String, Object> map = new HashMap<>();
					map.put("username", user.getUsername());
					userRepository.insetIntoUserTemp(map);

			List<String> roleuser = userRolesRepository.findRoleByUsername(username);
			System.out.println(username);
			System.out.println(roleuser);
			return new CustomUserDetails(user, roleuser);
	}

	public  Map getUserTempData()
	{
		return userRepository.getUserTempData();
	}


	public Projectuser getUserIdByUsername(String username) {
		return  userRepository.getUserIdByUsername(username);
	}

	
//	public List<Product> findByProductName(String product_name)
//	{
//		return userRepository.findByProductName(product_name);
//	}
	
	
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
//	public void insertintorpoduct(Product product)
//	{
//		userRepository.insertintoproduct(product);
//	}
//
//
//	public void updateintoproduct(Product product)
//	{
//		userRepository.updateintoproduct(product);
//	}
//
//	public Product getquantitybyid(long id)
//	{
//		return userRepository.getquantitybyid(id);
//	}
//
//	public List<Product> getAllProductInfo()
//	{
//		return userRepository.getAllProductInfo();
//	}
//
	public void updateUser(Projectuser projectuser)
	{
		userRepository.updateIntoUser(projectuser);
	}

	public void deleteUser(long user_id)
	{
		userRepository.deleteUserInfo(user_id);
	}
//
//	public Product getproductbyId(long id)
//	{
//		return userRepository.getproductbyid(id);
//	}
//
//	public void deleteproduct(long product_id)
//	{
//		userRepository.deleteproductinfo(product_id);
//	}
//
//	public int getTotalNoOfProduct() {
//
//		return userRepository.getTotalNoOfProduct();
//	}
//
//	public double getSumOfPrice() {
//
//		return userRepository.getSumOfPrice();
//	}
//
//	public int getTotalNoOfQuantity() {
//		return userRepository.getTotalNoOfQuantity();
//	}
//
//	public int getTotalNoOfProduct(String product_name) {
//		return userRepository.getTotalNoOfProduct(product_name);
//	}
//
//	public double getSumOfPrice(String product_name) {
//
//		return userRepository.getSumOfPrice(product_name);
//	}
	

	public int getTotalNoOfQuantity(String product_name) {
		return userRepository.getTotalNoOfQuantity(product_name);
	}
	
	
//	public CustomerProduct getquantitybycustomerid(long customer_id, long product_id)
//	{
//		return userRepository.getquantitybycustomerid(customer_id, product_id);
//	}
//
//	public boolean createPdf(List<Product> products, ServletContext context, HttpServletRequest request,
//			HttpServletResponse response) {
//		return userRepository.createPdf(products, context, request, response);
//	}
//
//	public boolean createExcel(List<Product> products, ServletContext context, HttpServletRequest request,
//			HttpServletResponse response) {
//
//		return userRepository.createExcel(products, context, request, response);
//	}
//
		public int getNoofUsers()
		{
			return userRepository.getNoofUsers();
		}
//
//		public int getNoOfExpiredProduct(String expiry_date) {
//
//			return userRepository.getNoOfExpiredProduct(expiry_date);
//		}
//
//		  public List<String> getExpiredProduct(String expiry_date)
//		  {
//			  return userRepository.getExpiredProduct(expiry_date);
//		  }
		
}
