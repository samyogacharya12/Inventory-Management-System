package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class SupplierFrontController {

    @GetMapping("/getSupplierForm")
    public String getForm()
    {
        return "supplierForm.jsp";
    }


    @GetMapping("/list-supplier")
    public String getSupplier()
    {
        return "supplierList.jsp";
    }


    @GetMapping(value="/getSupplierPersonalInfo")
    public String getSupplierPersonalInfo()
    {
        return "supplierpersonaList.jsp";
    }


    @GetMapping(value="/getSupplierProductAnalysis")
    public String getProductAnalysisList()
    {
        return "supplierAnalysis.jsp";
    }


}
