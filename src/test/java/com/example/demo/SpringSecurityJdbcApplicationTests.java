package com.example.demo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.ExpenseDetailServiceImpl;
import com.example.demo.service.SupplierDetailService;
import com.example.demo.service.UserDetailServiceImpl;

import junit.framework.TestCase;

@RunWith(SpringRunner.class)
@SpringBootTest
public class SpringSecurityJdbcApplicationTests extends TestCase {

	@Autowired
	private ExpenseDetailServiceImpl expenseDetailService;
	
	@Autowired
	private CustomerDetailServiceImpl customerDetailService;
	
	@Autowired
	private SupplierDetailService supplierDetailService;
	
	@Test
	public void contextLoads() 
	{
		String[] buy_date= {"2019-05-1","2019-05-30"};
		System.out.println(buy_date[0]);
		System.out.println(buy_date[1]);
		Double cost1=expenseDetailService.totalCost(buy_date);
		Double cost2=supplierDetailService.getSumofCost(buy_date);
		Double revenue=customerDetailService.sumOfAmount(buy_date);
		System.out.println(revenue);
		 if(cost2==null)
		{
			cost2=(double) 0;
			Double cost=cost1+cost2;
			System.out.println(cost);
			if(revenue==null)
			{
			  revenue=(double) 0;
			  if(revenue>=cost)
			{
				Double profit=revenue-cost;
				assertNotSame(14, profit);
			}
			else if(revenue==cost)
			{
				Double breakevenpoint=revenue-cost;
			}
			
			else
			{
				Double loss=cost-revenue;
				System.out.println("the loss is of" + loss);
			}
			}
			else
			{
			    if(revenue>=cost)
			{
				Double profit=revenue-cost;
			}
			else if(revenue==cost)
			{
				Double breakevenpoint=revenue-cost;
			}
			
			else
			{
				String data="loss";
				Double loss=cost-revenue;
				System.out.println("the loss is of" + loss);
			}
		    }
		}
	}
}


