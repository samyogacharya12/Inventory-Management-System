package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Trash;
import com.example.demo.service.TrashDetailServiceImpl;

@Controller
public class TrashController {

	@Autowired
	private TrashDetailServiceImpl trashDetailService;
	
	  @GetMapping("/list-trash")
	  public ModelAndView getTrashReport()
	  {
		  List<Trash> trash=trashDetailService.getAllTrashInfo();
			return new ModelAndView("trashList.jsp", "trash", trash);
	  }
	
}
