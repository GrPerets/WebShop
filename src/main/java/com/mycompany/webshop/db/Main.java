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
        List<Product> products = productDao.findAll();
        for(Product product:products) {
            System.out.println(product);
        }
        
        products = productDao.findProductById(2L);
        for(Product product:products) {
            System.out.println(product);
        }
        
        products = productDao.findProductByModel("abc321");
        for(Product product:products) {
            System.out.println(product);
        }
        
        products = productDao.findProductByCategory("hdd");
        for(Product product:products) {
            System.out.println(product);
        }
    }
    
}
