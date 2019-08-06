package com.example.demo.service;

import com.example.demo.model.Purchase;
import com.example.demo.repository.PurchaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class PurchaseDetailServiceImpl {

    @Autowired
   private PurchaseRepository purchaseRepository;

    public void insertIntoPurchase(Map map) {
        Date currentDate1 = new Date(System.currentTimeMillis());
        System.out.println(currentDate1);
        map.put("purchaseDate", currentDate1);
        purchaseRepository.insertIntoPurchase(map);
    }
}
