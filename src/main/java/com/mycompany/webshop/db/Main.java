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
        
        Product product = new Product();
        product.setModel("X-750F");
        product.setCategoryId("mouse");
        product.setManufacturerId("A4TECH");
        product.setPrice(15.00);
        
        productDao.insertProduct(product);
        
        
        /*
        
        listProducts(productDao.findProductById(2l));
        
        
        listProducts(productDao.findProductByModel("abc321"));
        
        
        listProducts(productDao.findProductByCategoryId(1l));
        
        
        listProducts(productDao.findProductByManufacturerId(3l));
        
        List<Product> products = productDao.findProductById(11l);
        //Product product;
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

        
        
        productDao.deleteProduct(6l);
        */
        listProducts(productDao.findAll());
    }
    private static void listProducts(List<Product> products) {
            for(Product product: products) {
                System.out.println(product);
            }
        }
}
