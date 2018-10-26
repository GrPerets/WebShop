/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.service_and_special_classes.Message;
import java.util.Locale;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author grperets
 */
@Controller
@RequestMapping ("/security")
public class SecurityController {
    private final Logger LOGGER = LoggerFactory.getLogger(SecurityController.class);
    private MessageSource messageSource;
    
    
    @RequestMapping("/login")
    public String page(Model uiModel) {
        uiModel.addAttribute("attribute", "value");
        return "view.name";
    }
    
    @RequestMapping ("/loginfail")
    public String loginFail (Model uiModel, Locale locale) {
        LOGGER.info("Login failed detected");
        uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("message_login_fail", new Object[]{}, locale)));
        return "products/list";
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
        
}
