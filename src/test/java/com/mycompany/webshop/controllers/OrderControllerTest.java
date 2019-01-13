/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.customer.CustomerService;
import com.mycompany.webshop.db.order.Order;
import com.mycompany.webshop.db.order.OrderService;
import com.mycompany.webshop.db.product.ProductService;
import com.mycompany.webshop.service_and_special_classes.OrderGrid;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author grperets
 */
public class OrderControllerTest {
    
    public OrderControllerTest() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    @Test
    public void testList() {
        System.out.println("list");
        Model uiModel = null;
        OrderController instance = new OrderController();
        String expResult = "";
        String result = instance.list(uiModel);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testListGrid() {
        System.out.println("listGrid");
        Integer page = null;
        Integer rows = null;
        String sortBy = "";
        String order = "";
        OrderController instance = new OrderController();
        OrderGrid<Order> expResult = null;
        OrderGrid<Order> result = instance.listGrid(page, rows, sortBy, order);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @Test (expected = java.lang.NullPointerException.class )
    public void testShow() {
        System.out.println("show");
        Long id = 1L;
        Model uiModel = null;
        OrderController instance = new OrderController();
        String expResult = "orders/show";
        String result = instance.show(id, uiModel);
        assertEquals(expResult, result);
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testUpdate() {
        System.out.println("update");
        Long id = null;
        Order order = null;
        BindingResult bindingResult = null;
        Model uiModel = null;
        HttpServletRequest httpServletRequest = null;
        RedirectAttributes redirectAttributes = null;
        Locale locale = null;
        OrderController instance = new OrderController();
        String expResult = "";
        String result = instance.update(id, order, bindingResult, uiModel, httpServletRequest, redirectAttributes, locale);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testDelete() {
        System.out.println("delete");
        Long id = null;
        OrderController instance = new OrderController();
        String expResult = "";
        String result = instance.delete(id);
        assertEquals(expResult, result);
        fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetMessageSource() {
        System.out.println("setMessageSource");
        MessageSource messageSource = null;
        OrderController instance = new OrderController();
        instance.setMessageSource(messageSource);
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetOrderService() {
        System.out.println("setOrderService");
        OrderService orderService = null;
        OrderController instance = new OrderController();
        instance.setOrderService(orderService);
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetCustomerService() {
        System.out.println("setCustomerService");
        CustomerService customerService = null;
        OrderController instance = new OrderController();
        instance.setCustomerService(customerService);
        //fail("The test case is a prototype.");
    }

    @org.junit.Test
    public void testSetProductService() {
        System.out.println("setProductService");
        ProductService productService = null;
        OrderController instance = new OrderController();
        instance.setProductService(productService);
        //fail("The test case is a prototype.");
    }
    
}
