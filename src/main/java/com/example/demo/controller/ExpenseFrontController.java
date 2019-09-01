package com.example.demo.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ExpenseFrontController {



    @GetMapping(value="/list-expense")
    public String getExpenseReport()
    {
        return "ExpenseReport.jsp";
    }


    @GetMapping(value="/expense-Form")
    public String expenseForm()
    {
        return "expenseForm.jsp";
    }

//

}
