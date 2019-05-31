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

import com.example.demo.model.Projectuser;
import com.example.demo.repository.UserRepository;

@Controller
public class ImageDisplayController {
    
	@Autowired
	private UserRepository userRepository;
	
	
	private int DEFAULT_BUFFER_SIZE=1024;
	
	@GetMapping(value="/imagedisplay")
	public void showImage(HttpServletRequest request, HttpServletResponse response) throws IOException
	{
		int id=Integer.parseInt(request.getParameter("user_id"));
		Projectuser projectuser=userRepository.getUserById(id);
		File file=new File(projectuser.getImage());
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
