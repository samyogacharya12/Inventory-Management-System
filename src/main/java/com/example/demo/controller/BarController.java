package com.example.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Customer_View;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.google.gson.Gson;

@Controller
public class BarController {

	@Autowired
	private CustomerDetailServiceImpl customerServiceImpl;
	
	public ModelAndView passingBarObjects(@RequestParam(value="sell_date[]") String sell_date[])
	{
		Gson gsonObj=new Gson();
		Map<Object, Object> map=null;
		List<Map<Object, Object>> list=new ArrayList<Map<Object, Object>>();
		String dataPoints=null;
		String xVal, yVal;
		List<Customer_View> customerView=customerServiceImpl.getCustomerBuyDate(sell_date);
		xVal="x";
		yVal="y";
		map=new HashMap<Object, Object>();
		map.put("x", Double.parseDouble(xVal));
		map.put("y", Double.parseDouble(yVal));
		list.add(map);
		dataPoints=gsonObj.toJson(list);
		return null;
	}
	
	@GetMapping("@{/Graph}")
	public String barDiagram()
	{
		return "barGraph";
	}
	
	@GetMapping("@{/displayBarGraph")
	public String barDiagram(@RequestParam(value="sell_date[]") String sell_date[],Model model)
	{
		Map<String, Object> surveyMap = new LinkedHashMap<>();		
		List<Customer_View> customer_view=customerServiceImpl.getCustomerBuyDate(sell_date);
		for(Customer_View customer_view1:customer_view)
		{
			surveyMap.put("customer_id", customer_view1.getCustomer_id());
			surveyMap.put("buy_date", customer_view1.getBuy_date());	
		}
		model.addAttribute("surveyMap", surveyMap);
		return "barGraph";
	}
	
	
	
	
   @GetMapping(value="/canvasjschart")
	public String springMvc()
	{
		return "chart.jsp";
	}
	
	@GetMapping(value="/getData")
	public String springMVC(@RequestParam(value="sell_date[]") String[] sell_date,ModelMap modelMap) {
//		List<List<Map<Object, Object>>> canvasjsDataList = canvasjsChartService.getCanvasjsChartData();
		Map<Object, Object> canvasjsDataList=new LinkedHashMap<Object, Object>();
		List<Customer_View> customerView=customerServiceImpl.getCustomerBuyDate(sell_date);
		for(Customer_View customerView1:customerView)
		{
			canvasjsDataList.put("buy_date", customerView1.getBuy_date());
			canvasjsDataList.put("customer_id", customerView1.getCustomer_id());
		}
		modelMap.addAttribute("dataPointsList", canvasjsDataList);	
		return "chart.jsp";
	}
	
	
}
