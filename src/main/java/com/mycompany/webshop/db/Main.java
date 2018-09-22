/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import java.util.List;
import org.springframework.context.support.GenericXmlApplicationContext;

/**
 *
 * @author grperets
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        GenericXmlApplicationContext ctx = new GenericXmlApplicationContext();
        ctx.load("classpath:WEB-INF/applicationContext.xml");
        ctx.refresh();
        ProductDao productDao = ctx.getBean("productDao", ProductDao.class);
        
        listProducts(productDao.findAll());
        
        
        listProducts(productDao.findProductById(2L));
        
        
        listProducts(productDao.findProductByModel("abc321"));
        
        
        listProducts(productDao.findProductByCategory("hdd"));
        
        
        listProducts(productDao.findProductByManufacturer("xiaomi"));
        
        List<Product> products = productDao.findProductById(3L);
        Product product;
        product = products.get(0);
        product.setPrice(99.9);
        productDao.updateProduct(product);
        
        /*
        product.setModel("Deskjet 3070A");
        product.setCategory("printers");
        product.setManufacturer("HP");
        product.setPrice(130.99);
        productDao.insertProduct(product);
        
        listProducts(productDao.findAll());

        */
        productDao.deleteProduct(6L);
        
        listProducts(productDao.findAll());
    }
    private static void listProducts(List<Product> products) {
            for(Product product: products) {
                System.out.println(product);
            }
        }
}
