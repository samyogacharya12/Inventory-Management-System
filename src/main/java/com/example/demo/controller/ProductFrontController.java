package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ProductFrontController {


    @GetMapping(value ="/list-product")
   public String getProductListPage()
   {
       return "productList.jsp";
   }


    @GetMapping(value="/getSaveProductForm")
    public String getProductForm()
    {
        return "AddProduct.jsp";
    }

    @GetMapping(value="/getProductAnalysis")
    public String getProductAnalysisList()
    {
        return "productAnalysis.jsp";
    }


}
