package com.example.demo.repository;

import java.util.List;

import com.example.demo.model.Product;
import com.example.demo.model.Trash;

public interface TrashRepository {

	  public void insertintotrash(Product product);
	  public List<Trash> getAllTrashInfo();
}
