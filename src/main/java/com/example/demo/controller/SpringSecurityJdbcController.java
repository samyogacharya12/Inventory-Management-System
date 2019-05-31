package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.model.Product;
import com.example.demo.model.Projectuser;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;



@Controller
@RequestMapping("/")
public class SpringSecurityJdbcController {

	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private CustomerDetailServiceImpl customerDetailService;
	
	@Autowired
	private SupplierDetailService supplierDetailService;
	@RequestMapping(value= {"/", "/home"})
	public String home(Model model)
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date buy_date1 = new Date(System.currentTimeMillis());
		String buy_date=formatter.format(buy_date1);
		int user=userDetailService.getNoofUsers();
		int numberofsales=customerDetailService.getPresentDate(buy_date);
		model.addAttribute("numberofsales", numberofsales);
		model.addAttribute("user", user);
		int numberofcustomers=customerDetailService.getNumberofCustomersToday(buy_date);
		model.addAttribute("numberofcustomers", numberofcustomers);
		int numberofsuppliers=supplierDetailService.NoofSupplier();
		model.addAttribute("numberofsuppliers", numberofsuppliers);
		int expiredproduct=userDetailService.getNoOfExpiredProduct(buy_date);
		model.addAttribute("expiredproduct", expiredproduct);
		int numberofproduct=userDetailService.getTotalNoOfQuantity();
		model.addAttribute("numberofproducts", numberofproduct);
		Double revenue=customerDetailService.getPresentRevenue(buy_date);
		if(revenue==null)
		{
			revenue=(double) 0;
			model.addAttribute("revenue", revenue);
		}
		else
		{
		model.addAttribute("revenue", revenue);
		}
		return "home.jsp";
	}
	
	 @GetMapping(value="/login")
	public String loginpage(HttpServletRequest request, HttpServletResponse response, @ModelAttribute("login") Projectuser projectuser)
	{
		return "login.jsp";
	}
	
	@GetMapping("/logout-sucess")
	public String logoutpage()
	{
		return "logout.jsp";
	}
}
