/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.customer.CustomerService;
import com.mycompany.webshop.db.order.Order;
import com.mycompany.webshop.db.order.OrderService;
import com.mycompany.webshop.db.product.ProductService;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.OrderGrid;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/orders")
public class OrderController {
    private final Logger LOGGER = LoggerFactory.getLogger(OrderController.class);
    private MessageSource messageSource;
    private OrderService orderService;
    private CustomerService customerService;
    private ProductService productService;
    
    @RequestMapping (method = RequestMethod.GET)
    public String list (Model uiModel) {
        LOGGER.info("Listing orders");
        Set<Order> orders = orderService.findAll();
        uiModel.addAttribute("orders", orders);
        LOGGER.info("No. of orders: " + orders.size());
        return "orders/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public OrderGrid<Order> listGrid (@RequestParam (value = "page", required = false) Integer page,
        @RequestParam (value = "rows", required = false) Integer rows,
        @RequestParam (value = "sidx", required = false) String sortBy,
        @RequestParam (value = "sord", required = false) String order) {
        LOGGER.info("Listing orders for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing orders for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("orderDateString"))
            orderBy = "orderDate";
        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort(Sort.Direction.DESC, orderBy);
            } else 
                sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest (page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest (page - 1, rows);
        }
        Page<Order> orderPage = orderService.findAllByPage(pageRequest);
        OrderGrid<Order> orderGrid = new OrderGrid<>();
        orderGrid.setCurrentPage(orderPage.getNumber() + 1);
        orderGrid.setTotalPages(orderPage.getTotalPages());
        orderGrid.setTotalRecords(orderPage.getTotalElements());
        orderGrid.setOrderData(Sets.newHashSet(orderPage.iterator()));
        return orderGrid;
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        Order order = orderService.findById(id);
        uiModel.addAttribute("order", order);
        return "orders/show";
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.POST)
    public String update (@PathVariable ("id") Long id, @Valid Order order, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating order");
        order = orderService.findById(id);
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("order_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("order", order);
            return "orders/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("order_save_success", new Object[]{}, locale)));
        order.setState("confirmed");
        orderService.save(order);
        return "redirect:/orders/" + UrlUtil.encodeUrlPathSegment(order.getId().toString(), httpServletRequest);
    }
    
    /*
    @RequestMapping (value = "/{id}", params="update", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") Long id, Model uiModel) {
        Order order = orderService.findById(id);
        order.setState("confirmed");
        uiModel.addAttribute("order", order);
        return "orders/update";
    }
    */
    
    @RequestMapping (value ="/{id}", method = RequestMethod.DELETE)
    public String delete (@PathVariable("id") Long id) {
        LOGGER.info("Deleting order");
        orderService.delete(id);
        LOGGER.info("Delete order with Id: " + id);
        return "redirect:/orders";
    }
    
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    
    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }
    
    
    
    private String getPrincipal(){
        String phoneNumber = null;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
 
        if (principal instanceof UserDetails) {
            phoneNumber = ((UserDetails)principal).getUsername();
        } else {
            phoneNumber = principal.toString();
        }
        return phoneNumber;
    }
    
}
