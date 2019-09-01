package com.example.demo.controller;

import com.example.demo.model.CustomerView;
import com.example.demo.model.PurchaseProduct;
import com.example.demo.service.CustomerDetailServiceImpl;
import com.example.demo.service.ProductDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
public class CustomerFrontController {



    @Autowired
    private CustomerDetailServiceImpl customerDetailService;

    @GetMapping(value = "/list-Customer")
    public String getCustomerList()
    {
        return "customerList.jsp";
    }


//    @GetMapping("/getCustomerEditForm")
//    public ModelAndView getCustomerEditForm(@RequestParam long customerId, @RequestParam long productId)
//    {
//        CustomerView customerView=customerDetailService.getCustomerById(customerId, productId);
//        return new ModelAndView("customerPersonalEdit.jsp","customerView",customerView);
//    }
//
//    @GetMapping("/getCustomerProductEditForm")
//    public ModelAndView getCustomerProductEditForm(@RequestParam long customerId, @RequestParam long productId)
//    {
//        CustomerView customerProduct=customerDetailService.getCustomerById(customerId, productId);
//        return new ModelAndView("customerProductEdit.jsp","customerProduct",customerProduct);
//    }



    @GetMapping("/getCustomerForm")
    public String getCustomerForm()
    {
        return "AddCustomer.jsp";
    }







}
