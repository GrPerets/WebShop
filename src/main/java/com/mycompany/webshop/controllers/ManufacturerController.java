/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.Manufacturer;
import com.mycompany.webshop.db.ManufacturerService;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/manufacturers")
public class ManufacturerController {
    private final Logger LOGGER = LoggerFactory.getLogger(ManufacturerController.class);
    private ManufacturerService manufacturerService;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing manufacturers");
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        uiModel.addAttribute("manufacturers", manufacturers);
        LOGGER.info("No. of manufacturers: " + manufacturers.size());
        return "manufacturers/list";
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable("id") String id, Model uiModel) {
        Manufacturer manufacturer = manufacturerService.findById(id);
        uiModel.addAttribute("manufacturer", manufacturer);
        return "manufacturers/show";
    }
    
    

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
    
    
}
