package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/")
public class HomeFrontController {

    @RequestMapping(value= {"/", "/home"})
    public String home()
    {
        return "home.jsp";
    }


}
