package com.example.demo.service;

import com.example.demo.model.*;
import com.example.demo.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ProductDetailServiceImpl {


    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ExcelService excelService;

    @Autowired
    private PdfService pdfService;

    public void insertIntoProduct(Product product) {
        productRepository.insertIntoProduct(product);
    }

    public List<PurchaseProduct> getProductByNameTypeAndPurchaseDate(Date purchaseDate)
    {
        return  productRepository.getProductByNameTypeAndPurchaseDate(purchaseDate);
    }

//    public PurchaseProduct getProductByNameTypeAndPurchaseDate(String productName, String productType, Date purchaseDate) {
//        return  productRepository.getProductByNameTypeAndPurchaseDate(productName, productType, purchaseDate);
//    }

    public List<PurchaseProduct> getProductByNameTypeLessThanPurchaseDate(String productName, String productType, Date purchaseDate)
    {
        return  productRepository.getProductByNameTypeLessThanPurchaseDate(productName, productType, purchaseDate);
    }
//
//    public List<Product> getProductByNameTypeAndPurchaseDate(String productName, String productType, Date purchaseDate) {
//
//        return productRepository.getProductByNameTypeAndPurchaseDate(productName, productType, purchaseDate)
//    }

    public List<ProductAnalysis> getAllProductAnalysis() {
        return productRepository.getAllProductAnalysis();
    }

    public void insertIntoProductAnalysis(Map map) {
        productRepository.insertIntoProductAnalysis(map);
    }


    public Integer countProductByNameAndType(String productName, String productType) {
        return  productRepository.countProductByNameAndType(productName, productType);
    }


    public List<ProductAnalysis> getDataByName(String productName) {
        return productRepository.getDataByName(productName);
    }



//    public void calculateDifferenceInProductPrice(String productName, String productType) {
//        Date purchaseDate = new Date((System.currentTimeMillis()));
//        Integer count = countProductByNameAndType(productName, productType);
//        for (int i = 0; i <= count; i++) {
//            List<PurchaseProduct> purchaseProducts = getProductByNameTypeAndPurchaseDate(purchaseDate);
//            System.out.println(purchaseProducts);
//            List<PurchaseProduct> productList = getProductByNameTypeLessThanPurchaseDate(productName, productType, purchaseDate);
//            for (PurchaseProduct purchaseProduct : purchaseProducts) {
//                for (PurchaseProduct productList1 : productList) {
//                    if (purchaseProduct.getProductId() != productList1.getProductId()) {
//                        if (purchaseProduct.getPrice() >= productList1.getPrice()) {
//                            Double currentPrice = purchaseProduct.getPrice() - productList1.getPrice();
//                            Map<String, Object> map1 = new HashMap<>();
//                            map1.put("productId", purchaseProduct.getProductId());
//                            map1.put("pastPrice", productList1.getPrice());
//                            map1.put("presentPrice", purchaseProduct.getPrice());
//                            map1.put("priceIncreament", currentPrice);
//                            map1.put("priceDecreament", 0);
//                            map1.put("referenceProductId", productList1.getProductId());
//                            insertIntoProductAnalysis(map1);
//                        } else if (productList1.getPrice() == purchaseProduct.getPrice()) {
//                            System.out.println("The prices of a given product are equal");
//                        } else if (purchaseProduct.getPrice() <= productList1.getPrice()) {
//                            Double currentPrice = productList1.getPrice() - purchaseProduct.getPrice();
//                            Map<String, Object> map1 = new HashMap<>();
//                            map1.put("productId", purchaseProduct.getProductId());
//                            map1.put("pastPrice", productList1.getPrice());
//                            map1.put("presentPrice", purchaseProduct.getPrice());
//                            map1.put("priceIncreament", 0);
//                            map1.put("priceDecreament", currentPrice);
//                            map1.put("referenceProductId", productList1.getProductId());
//                            insertIntoProductAnalysis(map1);
//                        } else {
//                            System.out.println("The given data is empty");
//                        }
//                        purchaseDate = productList1.getPurchaseDate();
//                        System.out.println(purchaseDate);
//                    }
//                }
//            }
//        }
//    }




//    public void calculateDiffferenceInProductPrice(long productId,String productName, String productType, double price) {
//        Date date=new Date((System.currentTimeMillis()));
//        List<PurchaseProduct> product=getProductByNameTypeAndPurchaseDate(productName, productType,date);
//        for(PurchaseProduct productValue:product)
//        {
//                            if (price >= productValue.getPrice()) {
//                                Double currentPrice = price - productValue.getPrice();
//                                Map<String, Object> map = new HashMap<>();
//                                map.put("productId", productId);
//                                map.put("pastPrice", productValue.getPrice());
//                                map.put("presentPrice", price);
//                                map.put("priceIncreament", currentPrice);
//                                map.put("priceDecreament", 0);
//                                map.put("referenceProductId", productValue.getProductId());
//                                insertIntoProductAnalysis(map);
//                            } else if (price == productValue.getPrice()) {
//                                System.out.println("The prices of a given product are equal");
//                            } else if (productValue.getPrice() >= price) {
//                                Double currentPrice = productValue.getPrice() - price;
//                                Map<String, Object> map = new HashMap<>();
//                                map.put("productId", productId);
//                                map.put("pastPrice", productValue.getPrice());
//                                map.put("presentPrice", price);
//                                map.put("priceIncreament", 0);
//                                map.put("priceDecreament", currentPrice);
//                                map.put("referenceProductId", productValue.getProductId());
//                                insertIntoProductAnalysis(map);
//                            } else {
//                                System.out.println("The given data is empty");
//                            }
//                        }
//                }




    public List<PurchaseProduct> findByProductName(String productName) {
        return productRepository.findByProductName(productName);
    }

    public void updateExpiredProduct(Product product) {
         productRepository.updateExpiredProduct(product);
    }

    public Product getProductById(long productId) {
      return  productRepository.getProductById(productId);
    }

    public List<PurchaseProduct> getAllProductInfo() {
        return productRepository.getAllProductInfo();
    }


    public void updateIntoProduct(Product product) {
        productRepository.updateIntoProduct(product);
    }

    public void deleteproductinfo(long productId) {
        productRepository.deleteProductInfo(productId);
    }





    public CustomerProduct getQuantityByCustomerid(long customerId, long productId) {
        return productRepository.getQuantityByCustomerId(customerId, productId);
    }

    public int getTotalNoOfProduct() {
        return productRepository.getTotalNoOfProduct();
    }

    public double getSumOfPrice() {
        return productRepository.getSumOfPrice();
    }


    public int getTotalNoOfQuantity() {
        return productRepository.getTotalNoOfQuantity();
    }


    public int getTotalNoOfProduct(String productName) {
        return productRepository.getTotalNoOfProduct(productName);
    }


    public double getSumOfPrice(String productName) {
        return productRepository.getSumOfPrice(productName);
    }

    public Integer getTotalNoOfQuantity(String productName) {
        return productRepository.getTotalNoOfQuantity(productName);
    }

    public List<Product> getExpiredProduct(String expiryDate) {
        return productRepository.getExpiredProduct(expiryDate);
    }


    public int getNoOfExpiredProduct(String expiryDate) {
        return productRepository.getNoOfExpiredProduct(expiryDate);
    }


    public boolean createPdfForProducts(List<PurchaseProduct> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {
        return pdfService.createPdfForProducts(products, context, request, response);
    }

    public boolean createExcelForProducts(List<PurchaseProduct> products, ServletContext context, HttpServletRequest request, HttpServletResponse response) {

        return excelService.createExcelForProducts(products, context, request, response);
    }




}
