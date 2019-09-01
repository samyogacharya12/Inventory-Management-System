package com.example.demo.controller;

import com.example.demo.service.UserDetailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserFrontController {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @GetMapping(value = "/list-user")
    public String getUserList() {
        return "userList.jsp";
    }
    @GetMapping(value="/user-Form")
    @PreAuthorize("hasRole('super_admin')")
    public String getUserForm()
    {
        return "userForm.jsp";
    }

    @GetMapping(value="/get-report")
    public String getReport()
    {
        return "summaryReport.jsp";
    }
}