package com.example.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TrashFrontController {

    @GetMapping(value = "/list-trash")
    public String getTrashReport()
    {
        return "trashList.jsp";
    }
}
