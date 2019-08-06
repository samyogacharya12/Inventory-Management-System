package com.example.demo.service;

import com.example.demo.model.CustomerProduct;
import com.example.demo.model.CustomerView;
import com.example.demo.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class NotificationDetailServiceImpl {

    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private CustomerDetailServiceImpl customerDetailService;

    @Autowired
    private ProductDetailServiceImpl productDetailService;

    public  void sendProductNotification(CustomerProduct customerProduct) throws MailException
    {
        Product product=productDetailService.getProductById(customerProduct.getProductId());
        CustomerView customerView=customerDetailService.getCustomerById(customerProduct.getCustomerId(), customerProduct.getProductId());
        SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
        simpleMailMessage.setTo(customerView.getEmail());
        System.out.println(customerView.getEmail());
        simpleMailMessage.setFrom("samyog.acharya@gmail.com");
        simpleMailMessage.setSubject("Sucessfully Buyed Product");
        simpleMailMessage.setText("Customer You have buyed" + "\t"+product.getProductName()+ "\t" +customerProduct.getQuantity()+"quantity"+ "\t" +"at" +customerProduct.getAmount()+"from our shop");
        System.out.println(simpleMailMessage.getText());
        javaMailSender.send(simpleMailMessage);
    }

    public  void updateProductNotification(Map map) throws MailException
    {
          SimpleMailMessage simpleMailMessage=new SimpleMailMessage();
          simpleMailMessage.setFrom("samyog.acharya@gmail.com");
          simpleMailMessage.setTo((String) map.get("email"));
          simpleMailMessage.setSubject("SucessFully Updated Product Quantity");
          simpleMailMessage.setText("Dear Customer you have updated your"+ "\t"+map.get("productName")+ "\t"+"quantity from"+map.get("currentQuantity") +"to" +map.get("quantity") + "\t" +"from" +map.get("currentAmount") +"to" + map.get("newAmount") );
          javaMailSender.send(simpleMailMessage);
      }
}
