/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.basket.BasketService;
import com.mycompany.webshop.service_and_special_classes.BasketGrid;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import org.joda.time.DateTime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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
@RequestMapping ("/basket")
public class BasketController {
    private final Logger LOGGER = LoggerFactory.getLogger(BasketController.class);
    private BasketService basketService;
    private MessageSource messageSource;
    
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing basket");
        List<Basket> baskets = basketService.findAll();
        uiModel.addAttribute("baskets", baskets);
        LOGGER.info("No. of baskets: " + baskets.size());
        return "basket/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public BasketGrid<Basket> listGrid (@RequestParam (value = "page", required = false) Integer page,
        @RequestParam (value = "rows", required = false) Integer rows,
        @RequestParam (value = "sidx", required = false) String sortBy,
        @RequestParam (value = "sord", required = false) String order) {
        LOGGER.info("Listing baskets for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing baskets for grid with sort: {}, order: {}", sortBy, order);
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
        BasketGrid<Basket> basketGrid = new BasketGrid<>();
        basketGrid.setCurrentPage(basketPage.getNumber() + 1);
        basketGrid.setTotalPages(basketPage.getTotalPages());
        basketGrid.setTotalRecords(basketPage.getTotalElements());
        basketGrid.setBasketData(Lists.newArrayList(basketPage.iterator()));
        return basketGrid;
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        Basket basket = basketService.findById(id);
        uiModel.addAttribute("basket", basket);
        return "basket/show";
    }
    
    @RequestMapping(value = "/{productId}", params = "new", method = RequestMethod.POST)
    public String create(@Valid Basket basket, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating basket");
               
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("customer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("basket", basket);
            return "basket/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("customer_save_success", new Object[]{}, locale)));
        LOGGER.info("Basket id: " + basket.getId());
        basketService.save(basket);
        return "redirect:/basket/" + UrlUtil.encodeUrlPathSegment(basket.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/{productId}", params = "new", method = RequestMethod.GET)
    public String createForm (@PathVariable ("productId") Long productId, Model uiModel) {
        Basket basket = new Basket();
        basket.setCustomerId(productId);
        basket.setOrderDate(new DateTime());
        basket.setEnabled(true);
        uiModel.addAttribute("basket", basket);
        return "basket/create";
    }
    
    
    @Autowired
    public void setBasketService(BasketService basketService) {
        this.basketService = basketService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
    
}
