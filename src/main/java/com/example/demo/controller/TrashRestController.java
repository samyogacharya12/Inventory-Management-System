package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.demo.model.Trash;
import com.example.demo.service.TrashDetailServiceImpl;

@RestController
public class TrashRestController {

	@Autowired
	private TrashDetailServiceImpl trashDetailService;


	 @GetMapping(value = "/getListTrash")
	  public List<Trash> getListTrash()
	  {
	  	List<Trash> trashList=trashDetailService.getAllTrashInfo();
	  	return trashList;
	  }

	  @GetMapping(value = "/getTrashByProductName/{productName}")
      public List<Trash> getListTrashByProductName(@PathVariable String productName)
	  {
	  	List<Trash> trashListByProductName=trashDetailService.getTrashByProductName(productName);
	  	return trashListByProductName;
	  }
	
}
