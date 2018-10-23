/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.manufacturer.Manufacturer;
import com.mycompany.webshop.db.manufacturer.ManufacturerService;
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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/manufacturers")
public class ManufacturerController {
    private final Logger LOGGER = LoggerFactory.getLogger(ManufacturerController.class);
    private ManufacturerService manufacturerService;
    private MessageSource messageSource;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing manufacturers");
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        uiModel.addAttribute("manufacturers", manufacturers);
        LOGGER.info("No. of manufacturers: " + manufacturers.size());
        return "manufacturers/list";
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable("id") Long id, Model uiModel) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        uiModel.addAttribute("manufacturer", manufacturer);
        return "manufacturers/show";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update (Manufacturer manufacturer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating manufacturer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage ("manufacturer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("manufacturer", manufacturer);
            return "manufacturers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("manufacturer_save_success", new Object[]{}, locale)));
        manufacturerService.save(manufacturer);
        return "redirect:/manufacturers/" + UrlUtil.encodeUrlPathSegment(manufacturer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") Long id, Model uiModel) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        uiModel.addAttribute("manufacturer", manufacturer);
        return "manufacturers/update";
    }
    
    @RequestMapping (params = "form", method = RequestMethod.POST)
    public String create (Manufacturer manufacturer, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating manufacturer");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("manufacturer_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("manufacturer", manufacturer);
            return "manufacturers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("manufacturer_save_success", new Object[]{}, locale )));
        LOGGER.info("Manufacturer id: " + manufacturer.getManufacturer());
        manufacturerService.save(manufacturer);
        return "redirect:/manufacturers/" + UrlUtil.encodeUrlPathSegment(manufacturer.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (params = "form", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        Manufacturer manufacturer = new Manufacturer();
        uiModel.addAttribute("manufacturer", manufacturer);
        return "manufacturers/create";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.DELETE)
    public  String delete (@PathVariable ("id") Long id) {
        LOGGER.info("Deleting manufacturer");
        Manufacturer manufacturer = manufacturerService.findById(id);
        manufacturerService.delete(manufacturer);
        LOGGER.info("Manufacturer delete: " + manufacturer);
        
        return "redirect:/manufacturers/";
    }
    
    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
    
}
