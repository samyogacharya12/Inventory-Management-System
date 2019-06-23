package com.example.demo.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Demo {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		List<Integer> a=new ArrayList<>();
		a.add(1);
		a.add(5);
		a.add(7);
		a.add(8);
		a.add(10);
		System.out.println(a);
		Collections.reverse(a);
		System.out.println(a);

	}

}
