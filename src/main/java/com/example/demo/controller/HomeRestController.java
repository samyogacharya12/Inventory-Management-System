package com.example.demo.controller;

import java.text.SimpleDateFormat;
import java.util.*;

import com.example.demo.model.Login;
import com.example.demo.model.Product;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;

@RestController
public class HomeRestController {

	@Autowired
	private UserDetailServiceImpl userDetailService;
	@Autowired
	private CustomerDetailServiceImpl customerDetailService;

	@Autowired
	private ProductDetailServiceImpl productDetailService;

	@Autowired
	private SupplierDetailService supplierDetailService;

	@GetMapping(value = "/getFrontDatas")
	public List<Map> home()
	{
     List<Map> map=new ArrayList<>();
     Map<String, Object> map1=new HashMap<>();
		SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd");
		Date buyDate1 = new Date(System.currentTimeMillis());
		String buyDate=formatter.format(buyDate1);
		int user=userDetailService.getNoofUsers();
		int numberofsales=customerDetailService.getPresentDate(buyDate);
		int numberofcustomers=customerDetailService.getNumberofCustomersToday(buyDate);
		int numberofsuppliers=supplierDetailService.NoofSupplier();
		int expiredproduct=productDetailService.getNoOfExpiredProduct(buyDate);
		System.out.println(expiredproduct);
		int totalNoOfQuantity = productDetailService.getTotalNoOfQuantity();
		int numberofproduct= totalNoOfQuantity;
		Date date = new Date(System.currentTimeMillis());
		String sellDate=formatter.format(date);
		List<Product> product=productDetailService.getExpiredProduct(sellDate);
		Double revenue=customerDetailService.getPresentRevenue(buyDate);
		if(revenue==null)
		{
			revenue=(double) 0;
            map1.put("revenue", revenue);
		}
		else
		{
			map1.put("revenue", revenue);
		}
		if(product!=null)
		{
			map1.put("product", product);
		}
		else
		{
			product=null;
			map1.put("product", product);
		}
		map1.put("user", user);
		map1.put("numberofsales", numberofsales);
		map1.put("numberofcustomers", numberofcustomers);
		map1.put("numberofsuppliers", numberofsuppliers);
		map1.put("expiredproduct",expiredproduct );
        map1.put("numberofproduct", numberofproduct);
        map.add(map1);
        return map;
	}


}
