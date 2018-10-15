/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductService;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/products")
@Controller
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    private MessageSource messageSource;
        
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing products");
        List<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        LOGGER.info("No. of products: " + products.size());
        return "products/list";
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public String show (@PathVariable("id") Long productId, Model uiModel) {
        Product product = productService.findById(productId);
        uiModel.addAttribute("product", product);
        return "products/show";
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST )
    public String update (Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating product");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("product_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("product", product);
        return "products/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("product_save_success", new Object[]{}, locale)));
    productService.save(product);
    return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/{id}", params = "form", method= RequestMethod.GET)
    public String updateForm(@PathVariable("id") Long productId, Model uiModel) {
        uiModel.addAttribute("product", productService.findById(productId));
        return "products/update";
    }

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
    

    
    
    
    
}