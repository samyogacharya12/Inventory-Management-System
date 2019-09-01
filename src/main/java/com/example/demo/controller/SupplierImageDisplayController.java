package com.example.demo.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.example.demo.model.Supplier;
import com.example.demo.repository.SupplierRepository;
import com.example.demo.service.SupplierDetailService;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class SupplierImageDisplayController {

	@Autowired
	private SupplierDetailService supplierDetailService;
	private int DEFAULT_BUFFER_SIZE=1024;
	@GetMapping("/supplierimagedisplay")
	public void showImage(@RequestParam long supplierId, HttpServletResponse response) throws IOException
	{
		System.out.println(supplierId);
		Supplier supplier=supplierDetailService.getSupplierId(supplierId);
		File file=new File(supplier.getImage());
		BufferedInputStream input=null;
		BufferedOutputStream output=null;
		try {
			input=new BufferedInputStream(new FileInputStream(file),DEFAULT_BUFFER_SIZE);
			output=new BufferedOutputStream(response.getOutputStream(), DEFAULT_BUFFER_SIZE);
			byte[] buffer=new byte[DEFAULT_BUFFER_SIZE];
			int length;
			while((length=input.read(buffer))>0)
			{
				output.write(buffer,0, length);
				
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {
			input.close();
			output.close();
		}
		
	}
}
