/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.basket.BasketService;
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
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        Basket basket = basketService.findById(id);
        uiModel.addAttribute("basket", basket);
        return "basket/show";
    }
    
    @RequestMapping(params = "new", method = RequestMethod.POST)
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
    
    @RequestMapping (params = "new", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        Basket basket = new Basket();
        basket.setCustomerId(22L);
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
