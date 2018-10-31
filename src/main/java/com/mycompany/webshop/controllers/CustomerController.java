/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.customer.CustomerService;
import com.mycompany.webshop.service_and_special_classes.CustomerGrid;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping ("/customers")
public class CustomerController {
    private final Logger LOGGER = LoggerFactory.getLogger(CustomerController.class);
    private CustomerService customerService;
    private MessageSource messageSource;
    
    
    @PreAuthorize ("hasRole('ROLE_ADMIN')")
    @RequestMapping (method = RequestMethod.GET)
    public String list (Model uiModel) {
        LOGGER.info("Listing customers");
        List<Customer> customers = customerService.findAll();
        uiModel.addAttribute("customers", customers);
        LOGGER.info("No. of customers: " + customers.size());
        return "customers/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public CustomerGrid<Customer> listGrid (@RequestParam (value = "page", required = false) Integer page,
        @RequestParam (value = "rows", required = false) Integer rows,
        @RequestParam (value = "sidx", required = false) String sortBy,
        @RequestParam (value = "sord", required = false) String order) {
        LOGGER.info("Listing customers for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing customers for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("birthDateString"))
            orderBy = "birthDate";
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
        Page<Customer> customerPage = customerService.findAllByPage(pageRequest);
        CustomerGrid<Customer> customerGrid = new CustomerGrid<>();
        customerGrid.setCurrentPage(customerPage.getNumber() + 1);
        customerGrid.setTotalPages(customerPage.getTotalPages());
        customerGrid.setTotalRecords(customerPage.getTotalElements());
        customerGrid.setCustomerData(Sets.newHashSet(customerPage.iterator()));
        return customerGrid;
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        Customer customer = customerService.findById(id);
        uiModel.addAttribute("customer", customer);
        return "customers/show";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update (@Valid Customer customer, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating customer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("customer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("customer", customer);
            return "customers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("customer_save_success", new Object[]{}, locale)));
        customerService.save(customer);
        return "redirect:/customers/" + UrlUtil.encodeUrlPathSegment(customer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/{id}", params ="form", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") Long id, Model uiModel) {
        Customer customer = customerService.findById(id);
        uiModel.addAttribute("customer", customer);
        return "customers/update";
    }
    
    @RequestMapping(params = "new", method = RequestMethod.POST)
    public String create(@Valid Customer customer, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating customer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("customer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("customer", customer);
            return "customers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("customer_save_success", new Object[]{}, locale)));
        LOGGER.info("Customer id: " + customer.getId());
        customerService.save(customer);
        return "redirect:/customers/" + UrlUtil.encodeUrlPathSegment(customer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (params = "new", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        Customer customer = new Customer();
        uiModel.addAttribute("customer", customer);
        return "customers/create";
    }
    
    @RequestMapping (value ="/{id}", params = "form", method = RequestMethod.DELETE)
    public String delete (@PathVariable("id") Long id) {
        LOGGER.info("Deleting cuctomer");
        customerService.delete(id);
        LOGGER.info("Delete customer with Id: " + id);
        return "redirect:/customers";
    }

    @Autowired
    public void setCustomerService(CustomerService customerService) {
        this.customerService = customerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
        
}
