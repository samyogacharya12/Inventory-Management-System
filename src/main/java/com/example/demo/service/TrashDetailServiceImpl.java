package com.example.demo.service;

import com.example.demo.model.Product;
import com.example.demo.model.Trash;
import com.example.demo.repository.TrashRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
@Service
public class TrashDetailServiceImpl {

	@Autowired
	private TrashRepository trashRepository;
	
	public void insertIntoTrash(Product product)
	{
		trashRepository.insertIntoTrash(product);
	}
	
	public List<Trash> getAllTrashInfo() {
		return trashRepository.getAllTrashInfo();
	}
	public List<Trash> getTrashByProductName(String productName){
		return trashRepository.getTrashByProductName(productName);
	}
}
