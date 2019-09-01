package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.example.demo.model.ServiceResponse;
import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import com.example.demo.model.Projectuser;

import javax.validation.Valid;

@RestController
public class UserRestController {
	String uploaded_file="C:/Users/DELL/Desktop/New Files/";
    @Autowired
    private UserDetailServiceImpl userService;
    @Autowired
    private SupplierDetailService supplierDetailService;
    @Autowired
    private ExpenseDetailServiceImpl expenseDetailService;


    @Autowired
    private CustomerDetailServiceImpl customerDetailService;




	@GetMapping(value="/getListUser")
	public List<Projectuser> listUser()
	{
		List<Projectuser> userList=userService.getAllUserInfo();
		return userList;
	}



	@GetMapping(value="/calculateCash/{startDate}/{endDate}")
	public Map calculateCash(@PathVariable String startDate, @PathVariable String endDate)
	{
		Double totalCost;
		Map<String, Object> map=new HashMap<>();
		Double expenseCost=expenseDetailService.totalCost(startDate, endDate);
		System.out.println(expenseCost);
		Double supplierCost=supplierDetailService.getSumofCost(startDate, endDate);
		System.out.println(supplierCost);
		Double expiredProductCost=supplierDetailService.getCostFromExpiredProduct(startDate, endDate);
		System.out.println(expiredProductCost);
		Double totalRevenue=customerDetailService.sumOfAmount(startDate, endDate);
		System.out.println(totalRevenue);
		if(expenseCost!=null && supplierCost!=null && expiredProductCost!=null && totalRevenue!=null)
		{
			totalCost=expenseCost+supplierCost+expiredProductCost;
			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}

		else if(expenseCost==null  && supplierCost==null && expiredProductCost==null && totalRevenue==null) {
			expenseCost = 0.0;
			expiredProductCost = 0.0;
			supplierCost = 0.0;
			totalRevenue=0.0;
			String data = "breakevenpoint";
			map.put("data", data);
			return map;
		}

		else if(expenseCost==null  && supplierCost==null && expiredProductCost==null) {
			expenseCost = 0.0;
			expiredProductCost = 0.0;
			supplierCost = 0.0;
			totalCost = expenseCost + supplierCost + expiredProductCost;
			String data = "profit";
			Double profit = totalRevenue - totalCost;
			map.put("data", data);
			map.put("profit", profit);
			return map;
		}




	 else if(expenseCost==null  && supplierCost==null && totalRevenue==null)
		{
			expenseCost=0.0;
			supplierCost=0.0;
		    totalRevenue=0.0;
		    totalCost=expenseCost + supplierCost + expiredProductCost;
		    Double loss=totalCost-totalRevenue;
			String data = "loss";
			map.put("data", data);
			map.put("loss", loss);
		}

	 else if(supplierCost==null && expiredProductCost==null && totalRevenue==null)
		{
			expiredProductCost=0.0;
			supplierCost=0.0;
			totalRevenue=0.0;
			totalCost=expenseCost + supplierCost + expiredProductCost;
			Double loss=totalCost-totalRevenue;
			String data = "loss";
			map.put("data", data);
			map.put("loss", loss);
		}

	 else if(expenseCost==null && expiredProductCost==null && totalRevenue==null)
		{
			expenseCost=0.0;
			expiredProductCost=0.0;
			totalRevenue=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			Double loss=totalCost-totalRevenue;
			System.out.println(loss);
			String data = "loss";
			map.put("data", data);
			map.put("loss", loss);
		}

	 else if(expenseCost==null  && supplierCost==null)
		{
			expenseCost=0.0;
			supplierCost=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}

		else if(expenseCost==null  && expiredProductCost==null)
		{
			expenseCost=0.0;
			expiredProductCost=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}

		else if(supplierCost==null  && expiredProductCost==null)
		{
			supplierCost=0.0;
			expiredProductCost=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}

		else if(expenseCost==null && totalRevenue==null)
		{
			expenseCost=0.0;
			totalRevenue=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			String data="loss";
			Double loss=totalCost-totalRevenue;
			map.put("data", data);
			map.put("loss", loss);
			return map;
		}

		else if(expiredProductCost==null && totalRevenue==null)
		{
			expiredProductCost=0.0;
			totalRevenue=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			String data="loss";
			Double loss=totalCost-totalRevenue;
			map.put("data", data);
			map.put("loss", loss);
			return map;
		}

		else if(supplierCost==null && totalRevenue==null)
		{
			supplierCost=0.0;
			totalRevenue=0.0;
			totalCost=supplierCost+expenseCost+expiredProductCost;
			String data="loss";
			Double loss=totalCost-totalRevenue;
			map.put("data", data);
			map.put("loss", loss);
			return map;
		}

		else if(supplierCost==null)
		{
			supplierCost=(double) 0;
			totalCost=expenseCost+supplierCost+expiredProductCost;

			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}
		else if(expiredProductCost==null)
		{
			expiredProductCost=(double) 0;
			totalCost=expenseCost+supplierCost+expiredProductCost;
			if(totalRevenue>=totalCost)
			{
				String data="profit";
				Double profit=totalRevenue-totalCost;
				map.put("data", data);
				map.put("profit", profit);
				return map;
			}
			else if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}
		else if(totalRevenue==null)
		{
			totalCost=expenseCost+supplierCost+expiredProductCost;
			if(totalRevenue==totalCost)
			{
				String data="breakevenpoint";
				Double breakEvenPoint=totalRevenue-totalCost;
				map.put("data", data);
				map.put("breakEvenPoint", breakEvenPoint);
				return map;
			}
			else {
				String data="loss";
				Double loss=totalCost-totalRevenue;
				map.put("data", data);
				map.put("loss", loss);
				return map;
			}
		}
     return map;
	}



//	@GetMapping(value="/calculateCash")
//	public String calculateCash(@RequestParam(value="buyDate[]")String buyDate[],Model model)
//	{
//		Double cost;
//		ModelAndView value=new ModelAndView();
//		System.out.println(buyDate[0]);
//		System.out.println(buyDate[1]);
//		Double cost1=expenseDetailService.totalCost(buyDate);
//		System.out.println(cost1);
//		Double cost2=supplierDetailService.getSumofCost(buyDate);
//		Double cost3=supplierDetailService.getCostFromExpiredProduct(buyDate);
//		System.out.println(cost3);
//		if(cost1!=null && cost2!=null && cost3!=null)
//		{
//			cost=cost1+cost2 +cost3;
//			Double revenue=customerDetailService.sumOfAmount(buyDate);
//
//			if(revenue>=cost)
//			{
//				String data="profit";
//				model.addAttribute("data", data);
//				Double profit=revenue-cost;
//				model.addAttribute("profit", profit);
//				value.setViewName("summaryReport.jsp");
//			}
//			else if(revenue==cost)
//			{
//				String data="breakevenpoint";
//				model.addAttribute("data", data);
//				Double breakevenpoint=revenue-cost;
//				model.addAttribute("breakevenpoint", breakevenpoint);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			else
//			{
//				String data="loss";
//				model.addAttribute("data", data);
//				Double loss=cost-revenue;
//				model.addAttribute("loss", loss);
//				System.out.println("the loss is of" + loss);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			return "summaryReport.jsp";
//		}
//		else if(cost1==null)
//		{
//			cost1=(double) 0;
//			cost=cost1+cost2+cost3;
//			Double revenue= customerDetailService.sumOfAmount(buyDate);
//			if(revenue>cost)
//			{
//				String data="profit";
//				model.addAttribute("data", data);
//				Double profit=revenue-cost;
//				model.addAttribute("profit", profit);
//				value.setViewName("summaryReport.jsp");
//			}
//			else if(revenue==cost)
//			{
//				String data="breakevenpoint";
//				model.addAttribute("data", data);
//				Double breakevenpoint=revenue-cost;
//				model.addAttribute("breakevenpoint", breakevenpoint);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			else
//			{
//				String data="loss";
//				model.addAttribute("data", data);
//
//				Double loss=cost-revenue;
//				model.addAttribute("loss", loss);
//				System.out.println("the loss is of" + loss);
//				value.setViewName("summaryReport.jsp");
//			}
//			return "summaryReport.jsp";
//		}
//		else if(cost2==null)
//		{
//			cost2=(double) 0;
//			cost=cost1+cost2+cost3;
//			Double revenue=customerDetailService.sumOfAmount(buyDate);
//			if(revenue==null)
//			{
//			  revenue=(double) 0;
//			  if(revenue>=cost)
//			{
//				String data="profit";
//				model.addAttribute("data", data);
//				Double profit=revenue-cost;
//				model.addAttribute("profit", profit);
//				value.setViewName("summaryReport.jsp");
//			}
//			else if(revenue==cost)
//			{
//				String data="breakevenpoint";
//				model.addAttribute("data", data);
//				Double breakevenpoint=revenue-cost;
//				model.addAttribute("breakevenpoint", breakevenpoint);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			else
//			{
//				String data="loss";
//				model.addAttribute("data", data);
//				Double loss=cost-revenue;
//				model.addAttribute("loss", loss);
//				System.out.println("the loss is of" + loss);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			return "summaryReport.jsp";
//			}
//
//
//			else
//			{
//			    if(revenue>=cost)
//			{
//				String data="profit";
//				model.addAttribute("data", data);
//				Double profit=revenue-cost;
//				model.addAttribute("profit", profit);
//				value.setViewName("summaryReport.jsp");
//			}
//			else if(revenue==cost)
//			{
//				String data="breakevenpoint";
//				model.addAttribute("data", data);
//				Double breakevenpoint=revenue-cost;
//				model.addAttribute("breakevenpoint", breakevenpoint);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			else
//			{
//				String data="loss";
//				model.addAttribute("data", data);
//				Double loss=cost-revenue;
//				model.addAttribute("loss", loss);
//				System.out.println("the loss is of" + loss);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			return "summaryReport.jsp";
//		    }
//		}
//		else if(cost3==null)
//		{
//			cost3=(double) 0;
//			cost=cost1+cost2+cost3;
//			Double revenue=customerDetailService.sumOfAmount(buyDate);
//			if(revenue==null)
//			{
//				revenue=(double) 0;
//				if(revenue>=cost)
//				{
//					String data="profit";
//					model.addAttribute("data", data);
//					Double profit=revenue-cost;
//					model.addAttribute("profit", profit);
//					value.setViewName("summaryReport.jsp");
//				}
//				else if(revenue==cost)
//				{
//					String data="breakevenpoint";
//					model.addAttribute("data", data);
//					Double breakevenpoint=revenue-cost;
//					model.addAttribute("breakevenpoint", breakevenpoint);
//					value.setViewName("summaryReport.jsp");
//				}
//
//				else
//				{
//					String data="loss";
//					model.addAttribute("data", data);
//					Double loss=cost-revenue;
//					model.addAttribute("loss", loss);
//					System.out.println("the loss is of" + loss);
//					value.setViewName("summaryReport.jsp");
//				}
//
//				return "summaryReport.jsp";
//			}
//
//			else
//			{
//				if(revenue>=cost)
//				{
//					String data="profit";
//					model.addAttribute("data", data);
//					Double profit=revenue-cost;
//					model.addAttribute("profit", profit);
//					value.setViewName("summaryReport.jsp");
//				}
//				else if(revenue==cost)
//				{
//					String data="breakevenpoint";
//					model.addAttribute("data", data);
//					Double breakevenpoint=revenue-cost;
//					model.addAttribute("breakevenpoint", breakevenpoint);
//					value.setViewName("summaryReport.jsp");
//				}
//
//				else
//				{
//					String data="loss";
//					model.addAttribute("data", data);
//					Double loss=cost-revenue;
//					model.addAttribute("loss", loss);
//					System.out.println("the loss is of" + loss);
//					value.setViewName("summaryReport.jsp");
//				}
//
//				return "summaryReport.jsp";
//			}
//		}
//
//
//		else
//		{
//		  cost1=(double) 0;
//		  cost2=(double) 0;
//		  cost3=(double)0;
//		  cost=cost1+cost2 +cost3;
//			Double revenue=customerDetailService.sumOfAmount(buyDate);
//			if(revenue>=cost)
//			{
//				String data="profit";
//				model.addAttribute("data", data);
//				Double profit=revenue-cost;
//				model.addAttribute("profit", profit);
//				value.setViewName("summaryReport.jsp");
//			}
//			else if(revenue==cost)
//			{
//				String data="breakevenpoint";
//				model.addAttribute("data", data);
//				Double breakevenpoint=revenue-cost;
//				model.addAttribute("breakevenpoint", breakevenpoint);
//				value.setViewName("summaryReport.jsp");
//			}
//
//			else
//			{
//				String data="loss";
//				model.addAttribute("data", data);
//				Double loss=cost-revenue;
//				model.addAttribute("loss", loss);
//				System.out.println("the loss is of" + loss);
//				value.setViewName("summaryReport.jsp");
//			}
//			return "summaryReport.jsp";
//		}
//	}

	@PostMapping(value = "/save-user")
	@PreAuthorize("hasRole('super_admin')")
   public  ResponseEntity saveUser(@Valid Projectuser projectuser , @RequestParam MultipartFile file) throws IOException {
		    String imageuploadpath=writeImagetoFile(file);
		    projectuser.setImage(imageuploadpath);
			userService.insert(projectuser);
			Map<String, Object> map=new HashMap();
			map.put("user_id", projectuser.getUserId());
			map.put("role_id", projectuser.getUserRoleId());
			userService.insertIntoUserRole(map);
			ServiceResponse<Projectuser> response=new ServiceResponse<>("success", projectuser);
		    return new ResponseEntity(response, HttpStatus.OK);
   }

	
	public String writeImagetoFile(MultipartFile file) throws IOException
	{
		String imageuploadPath=uploaded_file +file.getOriginalFilename();
		byte[] bytes=file.getBytes();
		try {
			BufferedOutputStream bout=new BufferedOutputStream(new FileOutputStream(imageuploadPath));
			bout.write(bytes);
			bout.flush();
			bout.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return imageuploadPath;
	}


	@PostMapping(value="/update-user")
	@PreAuthorize("hasRole('super_admin')")
	public ResponseEntity updateUserForm(@Valid Projectuser projectuser, @RequestParam MultipartFile file) throws IOException {
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
		{
			Projectuser user=userService.getUserById(projectuser.getUserId());
	        imageuploadpath=user.getImage();
		}
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		if(projectuser!=null)
		{
			projectuser.setImage(imageuploadpath);
			Map<String, Object> map=new HashMap<>();
			map.put("role_id", projectuser.getUserRoleId());
			map.put("user_id", projectuser.getUserId());
			userService.updateIntoUserRole(map);
			userService.updateUser(projectuser);
		}
		ServiceResponse<Projectuser> response=new ServiceResponse<>("success", projectuser);
		return new ResponseEntity(response, HttpStatus.OK);
	}




	@GetMapping(value="/delete-user/{userId}")
	@PreAuthorize("hasRole('super_admin')")
	public ResponseEntity deleteUser(@PathVariable long userId)
	{
        Map<String, Object> map=new HashMap<>();
        map.put("user_id", userId);
        userService.deleteIntoUserRole(map);
		userService.deleteUser(userId);
        ServiceResponse<Projectuser> serviceResponse=new ServiceResponse("success", userId);
        return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@GetMapping(value="/retrieveUserById/{userId}")
	@PreAuthorize("hasRole('super_admin')")
	public ResponseEntity<Projectuser> retrieveUserById(@PathVariable long userId)
	{
		Projectuser projectuser=userService.getUserById(userId);
		System.out.println(projectuser.getTemporaryAdddress());
		ServiceResponse serviceResponse=new ServiceResponse("success", projectuser);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}

	@GetMapping(value="/retrieveUserProfileById/{userId}")
	public ResponseEntity<Projectuser> retrieveProfileByUserId(@PathVariable long userId)
	{
		Projectuser projectuser=userService.getUserById(userId);
		ServiceResponse serviceResponse=new ServiceResponse("success", projectuser);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}



	@GetMapping(value = "/getUserByUsername/{username}")
    public ResponseEntity<Projectuser> getUserByUsername(@PathVariable String username)
	{
		Projectuser projectuser=userService.findByUsername(username);
		ServiceResponse<Projectuser> serviceResponse=new ServiceResponse("sucess", projectuser);
		return new ResponseEntity(serviceResponse, HttpStatus.OK);
	}
}
