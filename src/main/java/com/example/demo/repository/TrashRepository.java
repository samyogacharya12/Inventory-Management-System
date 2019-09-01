package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.model.Trash;

public interface TrashRepository {

	   void insertIntoTrash(Product product);
	   List<Trash> getAllTrashInfo();
	   List<Trash> getTrashByProductName(String productName);
}
