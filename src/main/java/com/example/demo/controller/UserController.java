package com.example.demo.controller;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

import com.example.demo.service.*;
import org.springframework.beans.factory.annotation.Autowired;
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

import com.example.demo.model.Projectuser;

import javax.servlet.http.HttpServletRequest;

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
    private ProductDetailServiceImpl productDetailService;

    @Autowired
    private CustomerDetailServiceImpl customerDetailService;
	@GetMapping(value="/user-Form")
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
	
	@GetMapping(value="/get-report")
	public String getReport()
	{
		return "summaryReport.jsp";
	}
	
	@GetMapping(value="/calculateCash")
	public String calculateCash(@RequestParam(value="buyDate[]")String buyDate[],Model model)
	{
		Double cost;
		ModelAndView value=new ModelAndView();
		System.out.println(buyDate[0]);
		System.out.println(buyDate[1]);
		Double cost1=expenseDetailService.totalCost(buyDate);
		System.out.println(cost1);
		Double cost2=supplierDetailService.getSumofCost(buyDate);
		Double cost3=supplierDetailService.getCostFromExpiredProduct(buyDate);
		System.out.println(cost3);
		if(cost1!=null && cost2!=null && cost3!=null)
		{
			cost=cost1+cost2 +cost3;
			Double revenue=customerDetailService.sumOfAmount(buyDate);
			
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
				value.setViewName("summaryReport.jsp");
			}
			
			return "summaryReport.jsp";
		}
		else if(cost1==null)
		{
			cost1=(double) 0;
			cost=cost1+cost2+cost3;
			Double revenue= customerDetailService.sumOfAmount(buyDate);
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
				value.setViewName("summaryReport.jsp");
			}
			return "summaryReport.jsp";
		}
		else if(cost2==null)
		{
			cost2=(double) 0;
			cost=cost1+cost2+cost3;
			Double revenue=customerDetailService.sumOfAmount(buyDate);
			if(revenue==null)
			{
			  revenue=(double) 0;
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
				value.setViewName("summaryReport.jsp");
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
				value.setViewName("summaryReport.jsp");
			}
			
			return "summaryReport.jsp";
		    }
		}
		else if(cost3==null)
		{
			cost3=(double) 0;
			cost=cost1+cost2+cost3;
			Double revenue=customerDetailService.sumOfAmount(buyDate);
			if(revenue==null)
			{
				revenue=(double) 0;
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
					value.setViewName("summaryReport.jsp");
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
					value.setViewName("summaryReport.jsp");
				}

				return "summaryReport.jsp";
			}
		}


		else
		{
		  cost1=(double) 0;
		  cost2=(double) 0;
		  cost3=(double)0;
		  cost=cost1+cost2 +cost3;
			Double revenue=customerDetailService.sumOfAmount(buyDate);
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
				value.setViewName("summaryReport.jsp");
			}
			return "summaryReport.jsp";
		}
	}

	
	@PostMapping(value="/save-user")
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
	
	@RequestMapping(value="/list-user", method=RequestMethod.GET)
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
	
	@GetMapping(value="/viewProfile")
	public String getProfile(Model model, @RequestParam long userId)
	{
		model.addAttribute("user",userService.getUserById(userId));
		return "userprofile.jsp";
	}
	
	@GetMapping(value="/username")
	public String getUserProfile(Model model, @RequestParam long userId)
	{
		model.addAttribute("user",userService.getUserById(userId));
		return "userprofile.jsp";
	}
	
	@GetMapping(value="/getEditForm")
	@PreAuthorize("hasRole('super_admin')")
	public ModelAndView getUserEditForm(@RequestParam long userId)
	{
		System.out.println(userId);
		Projectuser user=userService.getUserById(userId);
		return new ModelAndView("userEdit.jsp", "userEdit", user);
	}
	
	@PostMapping(value="/update-user")
	@PreAuthorize("hasRole('super_admin')")
	public String updateUserForm(@ModelAttribute Projectuser projectuser,@RequestParam MultipartFile file) throws IOException
	{                                                                                                     
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
			userService.updateUser(projectuser);
		}
		return "redirect:/userEdit.jsp";
	}
	
	@GetMapping(value="/delete-user")
	@PreAuthorize("hasRole('super_admin')")
	public String deleteUser(@RequestParam long userId)
	{
		userService.deleteUser(userId);
		return "redirect:/userList.jsp";
	}
	
}
