/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductService;
import com.mycompany.webshop.service_and_special_classes.ProductGrid;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

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
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ProductGrid listGrid(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sortBy, @RequestParam(value = "sord", required = false) String order) {
        LOGGER.info("Licting products for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing products for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        
        if (orderBy != null && orderBy.equals("dateLastModifiedString"))
            orderBy = "dateLastModified";
                 
        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort (Sort.Direction.DESC, orderBy);
            } else sort = new Sort(Sort.Direction.ASC, orderBy);
                }
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest (page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest (page - 1, rows);
        }
        
        Page<Product> productPage = productService.findAllByPage(pageRequest);
        //JSON grid
        ProductGrid productGrid = new ProductGrid();
        productGrid.setCurrentPage(productPage.getNumber() + 1);
        productGrid.setTotalPages(productPage.getTotalPages());
        productGrid.setTotalRecords(productPage.getTotalElements());
        productGrid.setProductData(Lists.newArrayList(productPage.iterator()));
        return productGrid;
    }


    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    
    
}
