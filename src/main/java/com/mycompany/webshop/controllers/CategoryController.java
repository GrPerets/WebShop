/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.Category;
import com.mycompany.webshop.db.CategoryService;
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
@Controller
@RequestMapping ("/categories")
public class CategoryController {
    private final Logger LOGGER = LoggerFactory.getLogger(CategoryController.class);
    private CategoryService categoryService;
    private MessageSource messageSource;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing categories");
        List<Category> categories = categoryService.findAll();
        uiModel.addAttribute("categories", categories);
        LOGGER.info("No. of categories: " + categories.size());
        return "categories/list";
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") String id, Model uiModel) {
        Category category = categoryService.findById(id);
        uiModel.addAttribute("category", category);
        return "categories/show";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.POST )
    public String update (Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletrequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating category");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("category_save_fail", new Object[] {}, locale)));
            uiModel.addAttribute("category", category);
            return "categories/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addAttribute("message", new Message("success", messageSource.getMessage("category_save_success", new Object[]{}, locale)));
        categoryService.save(category);
        return "redirect:/category"+UrlUtil.encodeUrlPathSegment(category.getCategory().toString(), httpServletrequest);
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") String id, Model uiModel) {
        Category category = categoryService.findById(id);
        uiModel.addAttribute("category", category);
        return "categories/update";
    }
    
    @RequestMapping (params = "form", method = RequestMethod.POST)
    public String create (Category category, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating category");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("category_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("category", category);
            return "categories/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("category_save_success", new Object[]{}, locale)));
        LOGGER.info("Category name: " + category.getCategory());
        categoryService.save(category);
        return "redirect:/categories/" + UrlUtil.encodeUrlPathSegment(category.getCategory().toString(), httpServletRequest);
    }
    
    @RequestMapping (params = "form", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        Category category = new Category();
        uiModel.addAttribute("category", category);
        return "categories/create";
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
    
    
    
}
