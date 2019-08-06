package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.example.demo.model.Login;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
@RequestMapping("/")
public class HomeController {

	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private CustomerDetailServiceImpl customerDetailService;

	@Autowired
	private ProductDetailServiceImpl productDetailService;

	@Autowired
	private SupplierDetailService supplierDetailService;
	@RequestMapping(value= {"/", "/home"})
	public String home(Model model)
	{
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date buyDate1 = new Date(System.currentTimeMillis());
		String buyDate=formatter.format(buyDate1);
		int user=userDetailService.getNoofUsers();
		int numberofsales=customerDetailService.getPresentDate(buyDate);
		model.addAttribute("numberofsales", numberofsales);
		model.addAttribute("user", user);
		int numberofcustomers=customerDetailService.getNumberofCustomersToday(buyDate);
		model.addAttribute("numberofcustomers", numberofcustomers);
		int numberofsuppliers=supplierDetailService.NoofSupplier();
		model.addAttribute("numberofsuppliers", numberofsuppliers);
		int expiredproduct=productDetailService.getNoOfExpiredProduct(buyDate);
		model.addAttribute("expiredproduct", expiredproduct);
		int totalNoOfQuantity = productDetailService.getTotalNoOfQuantity();
		int numberofproduct= totalNoOfQuantity;
		model.addAttribute("numberofproducts", numberofproduct);
		Double revenue=customerDetailService.getPresentRevenue(buyDate);
		if(revenue==null)
		{
			revenue=(double) 0;
			model.addAttribute("revenue", revenue);
		}
		else
		{
		model.addAttribute("revenue", revenue);
		}
		Date date = new Date(System.currentTimeMillis());
		String sellDate=formatter.format(date);
		List<String> productName=productDetailService.getExpiredProduct(sellDate);
		System.out.println(productName);
		if(productName!=null)
		{
			model.addAttribute("productName", productName);
		}
		else
		{
			productName=null;
			model.addAttribute("productName", productName);
		}
		return "home.jsp";
	}
	
	 @GetMapping(value="/login")
	public String loginpage(@ModelAttribute("login") Login login)
	{
		return "login.jsp";
	}
	
	@GetMapping("/logout-sucess")
	public String logoutpage()
	{
		Map map=userDetailService.getUserTempData();
		userDetailService.deleteIntoUserTemp((String) map.get("username"));
		return "logout.jsp";
	}
}
