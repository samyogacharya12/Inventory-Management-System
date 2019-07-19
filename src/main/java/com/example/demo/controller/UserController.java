package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.imageio.IIOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Expense;
import com.example.demo.model.Projectuser;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.ExpenseDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;

@Controller
@RequestMapping("/")
public class UserController {
	String uploaded_file="C:/Users/DELL/Desktop/New Files/";
    @Autowired
    private UserDetailServiceImpl userService;
    @Autowired
    private SupplierDetailService supplierDetailService;
    @Autowired
    private ExpenseDetailServiceImpl expenseDetailService;
    
    @Autowired
    private CustomerDetailServiceImpl customerDetailService;
	@GetMapping(value="/user_Form")
	@PreAuthorize("hasRole('super_admin')")
	public String getUserForm(Projectuser projectuser)
	{
		return "userForm.jsp";
	}
	
//	public ProductResponse getExpiredProduct()
//	{
//		DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
//		Date date = new Date();
//		String product_name=userService.getExpiredProduct(dateFormat.format(date));
//		if(product_name!=null)
//		{
//			return new ProductResponse(product_name+"The Given Product has been Expired");
//		}
//		else
//		{
//			return null;
//		}
//	}
	
	@GetMapping(value="/get_report")
	public String getReport()
	{
		return "summaryReport.jsp";
	}
	
	@GetMapping(value="/calculateCash")
	public String getTable(@RequestParam(value="buy_date[]")String buy_date[],Model model)
	{
		Double cost;
		ModelAndView value=new ModelAndView();
		System.out.println(buy_date[0]);
		System.out.println(buy_date[1]);
		Double cost1=expenseDetailService.totalCost(buy_date);
		Double cost2=supplierDetailService.getSumofCost(buy_date);
		if(cost1!=null && cost2!=null)
		{
			cost=cost1+cost2;
			Double revenue=customerDetailService.sumOfAmount(buy_date);
			
			if(revenue>=cost)
			{
				String data="profit";
				model.addAttribute("data", data);
				System.out.println(revenue);
				System.out.println(cost);
				Double profit=revenue-cost;
				System.out.println(profit);
				model.addAttribute("profit", profit);
				value.setViewName("summaryReport.jsp");
			}
			else if(revenue==cost)
			{
				String data="breakevenpoint";
				model.addAttribute("data", data);
				System.out.println(revenue);
				System.out.println(cost);
				Double breakevenpoint=revenue-cost;
				System.out.println(breakevenpoint);
				model.addAttribute("breakevenpoint", breakevenpoint);
				value.setViewName("summaryReport.jsp");
			}
			
			else
			{
				String data="loss";
				model.addAttribute("data", data);
				System.out.println(revenue);
				System.out.println(cost);
				Double loss=cost-revenue;
				System.out.println(loss);
				model.addAttribute("loss", loss);
				System.out.println("the loss is of" + loss);
			}
			
			return "summaryReport.jsp";
		}
		else if(cost1==null)
		{
			cost1=(double) 0;
			cost2=(double) 0;
			System.out.println(cost1);
			System.out.println(cost2);
			cost=cost1+cost2;
			Double revenue=customerDetailService.sumOfAmount(buy_date);
			revenue=(double) 0;
			System.out.println(revenue);
			if(revenue>cost)
			{
				String data="profit";
				model.addAttribute("data", data);
				Double profit=revenue-cost;
				model.addAttribute("profit", profit);
				value.setViewName("summaryReport.jsp");
			}
			else if(revenue==cost)
			{
				String data="breakevenpoint";
				model.addAttribute("data", data);
				Double breakevenpoint=revenue-cost;
				model.addAttribute("breakevenpoint", breakevenpoint);
				value.setViewName("summaryReport.jsp");
			}
			
			else
			{
				String data="loss";
				model.addAttribute("data", data);
				
				Double loss=cost-revenue;
				model.addAttribute("loss", loss);
				System.out.println("the loss is of" + loss);
			}
			return "summaryReport.jsp";
		}
		else if(cost2==null)
		{
			cost2=(double) 0;
			cost=cost1+cost2;
			System.out.println(cost);
			Double revenue=customerDetailService.sumOfAmount(buy_date);
			if(revenue==null)
			{
			  revenue=(double) 0;
			  if(revenue>=cost)
			{
				String data="profit";
				model.addAttribute("data", data);
				Double profit=revenue-cost;
				System.out.println(profit);
				model.addAttribute("profit", profit);
				value.setViewName("summaryReport.jsp");
			}
			else if(revenue==cost)
			{
				String data="breakevenpoint";
				model.addAttribute("data", data);
				Double breakevenpoint=revenue-cost;
				model.addAttribute("breakevenpoint", breakevenpoint);
				value.setViewName("summaryReport.jsp");
			}
			
			else
			{
				String data="loss";
				model.addAttribute("data", data);
				Double loss=cost-revenue;
				model.addAttribute("loss", loss);
				System.out.println("the loss is of" + loss);
			}
			
			return "summaryReport.jsp";
			}
			else
			{
			    if(revenue>=cost)
			{
				String data="profit";
				model.addAttribute("data", data);
				Double profit=revenue-cost;
				System.out.println(profit);
				model.addAttribute("profit", profit);
				value.setViewName("summaryReport.jsp");
			}
			else if(revenue==cost)
			{
				String data="breakevenpoint";
				model.addAttribute("data", data);
				Double breakevenpoint=revenue-cost;
				model.addAttribute("breakevenpoint", breakevenpoint);
				value.setViewName("summaryReport.jsp");
			}
			
			else
			{
				String data="loss";
				model.addAttribute("data", data);
				Double loss=cost-revenue;
				model.addAttribute("loss", loss);
				System.out.println("the loss is of" + loss);
			}
			
			return "summaryReport.jsp";
		    }
		}
		else
		{
		  cost1=(double) 0;
		  cost2=(double) 0;
		  cost=cost1+cost2;
			Double revenue=customerDetailService.sumOfAmount(buy_date);
			if(revenue>=cost)
			{
				String data="profit";
				model.addAttribute("data", data);
				Double profit=revenue-cost;
				model.addAttribute("profit", profit);
				value.setViewName("summaryReport.jsp");
			}
			else if(revenue==cost)
			{
				String data="breakevenpoint";
				model.addAttribute("data", data);
				Double breakevenpoint=revenue-cost;
				model.addAttribute("breakevenpoint", breakevenpoint);
				value.setViewName("summaryReport.jsp");
			}
			
			else
			{
				String data="loss";
				model.addAttribute("data", data);
				Double loss=cost-revenue;
				model.addAttribute("loss", loss);
				System.out.println("the loss is of" + loss);
			}
			return "summaryReport.jsp";
		}
		
		
	
	}

	
	@PostMapping(value="/save_form")
	public String saveuser(@ModelAttribute Projectuser projectuser,@RequestParam MultipartFile file) throws IOException
	{
		String imageuploadpath=writeImagetoFile(file);
		if(projectuser!=null)
		{
			projectuser.setImage(imageuploadpath);
			userService.insert(projectuser);
		}
		return "redirect:/userForm.jsp";
	}
	
	@GetMapping(value="/getUserByName")
	public ModelAndView getUser(@RequestParam String username)
	{
		Projectuser usern=userService.findByUsername(username);
		return new ModelAndView("userList.jsp", "username", usern);
	}
	
	@RequestMapping(value="/get_list", method=RequestMethod.GET)
	public ModelAndView listuser()
	{
		List<Projectuser> userList = userService.getAllUserInfo();
		return new ModelAndView("userList.jsp", "user", userList);
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
	
	@GetMapping(value="/viewprofile")
	public String getProfile(Model model, @RequestParam long user_id)
	{
		model.addAttribute("user",userService.getUserById(user_id));
		return "userprofile.jsp";
	}
	
	@GetMapping(value="/username")
	public String getUserProfile(Model model, @RequestParam long user_id)
	{
		model.addAttribute("user",userService.getUserById(user_id));
		return "userprofile.jsp";
	}
	
	@GetMapping(value="/user")
	@PreAuthorize("hasRole('super_admin')")
	public ModelAndView getUserEditForm(@RequestParam long user_id)
	{
		System.out.println(user_id);
		Projectuser user=userService.getUserById(user_id);
		return new ModelAndView("userEdit.jsp", "userEdit", user);
	}
	
	@PostMapping(value="/update_user")
	@PreAuthorize("hasRole('super_admin')")
	public String updateUserForm(@ModelAttribute Projectuser projectuser,@RequestParam MultipartFile file) throws IOException
	{                                                                                                     
		String imageuploadpath="";
		if(file.getOriginalFilename().isEmpty())
		{
			Projectuser user=userService.getUserById(projectuser.getUser_id());
	        imageuploadpath=user.getImage();		
		}
		else
		{
			imageuploadpath=writeImagetoFile(file);
		}
		if(projectuser!=null)
		{
			projectuser.setImage(imageuploadpath);
			userService.UpdateUser(projectuser);
		}
		return "redirect:/userEdit.jsp";
	}
	
	@GetMapping(value="/deleteuser")
	@PreAuthorize("hasRole('super_admin')")
	public String deleteUser(@RequestParam long user_id)
	{
		userService.deleteUser(user_id);
		return "redirect:/userList.jsp";
	}
	
}
