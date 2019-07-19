package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SpringBootApplication
public class SpringSecurityJdbcApplication{
	
	
	public static void main(String[] args) {
		SpringApplication.run(SpringSecurityJdbcApplication.class, args);
	}
}

