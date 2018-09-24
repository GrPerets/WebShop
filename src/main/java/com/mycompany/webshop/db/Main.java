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
        
        //Spring+JDBC
        /*
        JdbcProductDao jdbcProductDao = ctx.getBean("jdbcProductDao", JdbcProductDao.class);
        
        listProducts(jdbcProductDao.findAll());
        
        Product product = new Product();
        product.setModel("Redmi 4 Prime");
        product.setCategoryId("smartphone");
        product.setManufacturerId("xiaomi");
        product.setPrice(131.00);
        
        jdbcProductDao.insertProduct(product);
        
        listProducts(jdbcProductDao.findProductById(2l));
        
        listProducts(jdbcProductDao.findProductByModel("abc321"));
        
        listProducts(jdbcProductDao.findProductByCategoryId("modem"));
        
        listProducts(jdbcProductDao.findProductByManufacturerId("samsung"));
        
        List<Product> products = jdbcProductDao.findProductById(11l);
        //Product product;
        product = products.get(0);
        product.setPrice(99.9);
        jdbcProductDao.updateProduct(product);
        
        product.setModel("Deskjet 3070A");
        product.setCategory("printers");
        product.setManufacturer("HP");
        product.setPrice(130.99);
        jdbcProductDao.insertProduct(product);
        
        listProducts(jdbcProductDao.findAll());
                
        jdbcProductDao.deleteProduct(6l);
        
        listProducts(jdbcProductDao.findAll());
        */
        
        //Spring+Hibernate
        
        HibernateProductDao hibernateProductDao = ctx.getBean("hibernateProductDao", HibernateProductDao.class);
        listProducts(hibernateProductDao.findAll());
        listProducts(hibernateProductDao.findProductById(19l));
        listProducts(hibernateProductDao.findProductByModel("SyncMaster p2350"));
        listProducts(hibernateProductDao.findProductByCategoryId("mouse"));
        listProducts(hibernateProductDao.findProductByManufacturerId("huawei"));
    
    }
    
    private static void listProducts(List<Product> products) {
            for(Product product: products) {
                System.out.println(product);
            }
        }
}
