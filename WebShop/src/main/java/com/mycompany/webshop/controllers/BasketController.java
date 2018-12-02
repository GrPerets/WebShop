/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.customer.CustomerService;
import com.mycompany.webshop.db.product.ProductService;
import com.mycompany.webshop.service_and_special_classes.Message;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/baskets")
@SessionAttributes (types = Basket.class)
public class BasketController {
    private final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);
    private MessageSource messageSource;
    private CustomerService customerService;
    private ProductService productService;
    
    /*
    //@PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping( value = "/{phoneNumber}", method = RequestMethod.GET)
    public String list(@PathVariable ("phoneNumber") String phoneNumber, Model uiModel) {
        LOGGER.info("Listing basket");
        Customer customer = customerService.findByPhoneNumber(phoneNumber);
        Set<Basket> baskets = basketService.findByCustomer(customer);
        uiModel.addAttribute("customer", customer);
        
        LOGGER.info("No. of baskets: " + baskets.size());
        LOGGER.info("Phone Number: " + phoneNumber);
        LOGGER.info("Listing basket: " + baskets.size());
        return "basket/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BasketGrid<Basket> listGrid (@RequestParam (value = "page", required = false) Integer page,
        @RequestParam (value = "rows", required = false) Integer rows,
        @RequestParam (value = "sidx", required = false) String sortBy,
        @RequestParam (value = "sord", required = false) String order,
        @RequestParam (value = "phoneNumber", required = false) String phoneNumber) {
        LOGGER.info("Listing baskets for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing baskets for grid with sort: {}, order: {}", sortBy, order);
        LOGGER.info("Listing Customer phoneNumber: " + phoneNumber);
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
        Page<Basket> basketPage = basketService.findAllByPage(pageRequest);
        
        BasketGrid<Basket> basketGrid = new BasketGrid<Basket>();
        basketGrid.setCurrentPage(basketPage.getNumber() + 1);
        basketGrid.setTotalPages(basketPage.getTotalPages());
        basketGrid.setTotalRecords(basketPage.getTotalElements());
        basketGrid.setBasketData(Sets.newHashSet(basketPage.iterator()));
        //basketGrid.setBasketData(Sets.newHashSet(baskets.iterator()));
        return basketGrid;
    }
    
    */
    @RequestMapping (method = RequestMethod.GET)
    public String show ( Basket basket , Model uiModel) {
        uiModel.addAttribute("basket", basket);
        return "baskets/show";
    }
    
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.POST)
    public String update(Basket basket, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale,
                        @RequestParam (value = "productId")Long productId,
                        @PathVariable ("id") Long id) {
        LOGGER.info("Creating basket");
        //basket = basketService.findById(id);
        //basket.setCustomer(customerService.findByPhoneNumber(getPrincipal()));
        basket.setOrderDate(new DateTime());
        basket.setEnabled(true);
        basket.addProduct(productService.findById(productId));
        
                
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("customer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("basket", basket);
            return "baskets/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("customer_save_success", new Object[]{}, locale)));
        
        //basketService.save(basket);
        LOGGER.info("Basket: " + basket.toString());
        //return "redirect:/basket/" + UrlUtil.encodeUrlPathSegment(basket.getCustomer().getPhoneNumber().toString(), httpServletRequest);
        uiModel.addAttribute("basket", basket);
        return "baskets/next";
        
    }
    
    //@ResponseBody
    @RequestMapping(method = RequestMethod.POST)
    
    public String create(Basket basket, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale,
                        @RequestParam (value = "productId")Long productId) {
        LOGGER.info("Creating basket");
        if (basket == null) {
            basket = new Basket();
            basket.setOrderDate(new DateTime());
            basket.setEnabled(true);
        }
        basket.addProduct(productService.findById(productId));
        
                
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("customer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("basket", basket);
            return "baskets/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("customer_save_success", new Object[]{}, locale)));
            
        LOGGER.info("Basket : " + basket.toString());
        //return "redirect:/basket/" + UrlUtil.encodeUrlPathSegment(basket.getCustomer().getPhoneNumber().toString(), httpServletRequest);
        uiModel.addAttribute("basket", basket);
        return "baskets/show";
        
    }
    
    /*
    @RequestMapping (method = RequestMethod.GET)
    public String createBasket (@RequestParam (value = "productId") @RequestBody Long productId, Model uiModel) {
        Basket basket = new Basket();
        Product product = productService.findById(productId);
        uiModel.addAttribute("product", product);
        uiModel.addAttribute("basket", basket);
        return "basket/create";
    }
    */
    
    @RequestMapping (method = RequestMethod.DELETE)
    public String delete (SessionStatus status) {
        LOGGER.info("Deleting basket");
        status.setComplete();
        
        return "redirect:/baskets";
    } 
    
    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
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
