/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/")
public class HomeController {
    private final Logger LOGGER = LoggerFactory.getLogger(HomeController.class);
    private ProductService productService;
    
    @RequestMapping(method  = RequestMethod.GET)
    public String news(Model uiModel) {
        LOGGER.info("Listing products");
        List<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        LOGGER.info("No. of new products: " + products.size());
        return "home/news";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    
    
}
