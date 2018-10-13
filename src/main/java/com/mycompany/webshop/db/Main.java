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
        ctx.load("classpath:META-INF/applicationContextDataJpa.xml");
        ctx.refresh();
        
        
        //Spring+JDBC
        //************************
        /*
        JdbcProductDao jdbcProductDao = ctx.getBean("jdbcProductDao", JdbcProductDao.class);
        
        listProducts(jdbcProductDao.findAll());
        /*
        Product product = new Product();
        product.setModel("Redmi 4 Prime");
        product.setCategoryId("smartphone");
        product.setManufacturerId("xiaomi");
        product.setPrice(131.00);
        
        jdbcProductDao.insertProduct(product);
        
        listProducts(jdbcProductDao.findProductById(20l));
        
        listProducts(jdbcProductDao.findProductByModel("abc321"));
        
                
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
        
        listProducts(productDao.findAll());
                
        jdbcProductDao.deleteProduct(6l);
        
        listProducts(jdbcProductDao.findAll());
        */
        
        //Spring+Hibernate
        //*******************************
        /*
        HibernateProductDao hibernateProductDao = ctx.getBean("hibernateProductDao", HibernateProductDao.class);
        listProducts(hibernateProductDao.findAll());
        listProducts(hibernateProductDao.findProductById(21l));
        listProducts(hibernateProductDao.findProductByModel("SyncMaster p2350"));
        Product product = hibernateProductDao.findProductById(22l).get(0);
        
        /*
        product.setModel("SyncMaster 965");
        product.setCategoryId("monitor");
        product.setManufacturerId("samsung");
        
        product.setPrice(0.99);
        
        hibernateProductDao.save(product);
        product = hibernateProductDao.findProductById(19l).get(0);
        hibernateProductDao.delete(product);
        listProducts(hibernateProductDao.findAll());
        
        */
        
        //Spring+JPA
        //*********************
        /*
        JpaProductDao jpaProductDao = ctx.getBean("jpaProductDao", JpaProductDao.class);
        listProducts(jpaProductDao.findAll());
        jpaProductDao.findProductById(22L);
        
        
        Product product = new Product();
        product.setModel("x610");
        product.setCategoryId("smartphone");
        product.setManufacturerId("samsung");
        product.setPrice(19.99);
        jpaProductDao.save(product);
        listProducts(jpaProductDao.findAll());
        /*
        product = jpaProductDao.findProductById(27l);
        product.setPrice(999.99);
        jpaProductDao.save(product);
        ///
        listProducts(jpaProductDao.findAll());
        jpaProductDao.delete(jpaProductDao.findProductByModel("x610").get(0));
        listProducts(jpaProductDao.findAll());
        
        listProducts(jpaProductDao.findByCriteriaQuery(null, null));
        */
        
        //Spring Data JPA
        
        ProductService productService = ctx.getBean("jpaProductService", ProductService.class);
        listProducts(productService.findAll());
        listProducts(productService.findByCategoryIdAndManufacturerId("monitor","samsung"));
        listProducts(productService.findByModel("hg530"));
    
    }
    
    private static void listProducts(List<Product> products) {
            for(Product product: products) {
                System.out.println(product);
            }
        }
}
