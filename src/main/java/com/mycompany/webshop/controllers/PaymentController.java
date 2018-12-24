/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.mycompany.webshop.db.payment.PaymentType;
import com.mycompany.webshop.db.payment.PaymentTypeService;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.Locale;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.security.access.prepost.PreAuthorize;
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
@RequestMapping ("/payments")
public class PaymentController {
    private final Logger LOGGER = LoggerFactory.getLogger(PaymentController.class);
    private PaymentTypeService paymentTypeService;
    private MessageSource messageSource;
    
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing payment types");
        Set<PaymentType> paymentTypes = paymentTypeService.findAll();
        uiModel.addAttribute("paymentTypes", paymentTypes);
        LOGGER.info("No. of pyment types: " + paymentTypes.size());
        return "payments/list";
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        PaymentType paymentType = paymentTypeService.findById(id);
        uiModel.addAttribute("paymentType", paymentType);
        return "payments/show";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.POST )
    public String update (PaymentType paymentType, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating payment type");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("payment_type_save_fail", new Object[] {}, locale)));
            uiModel.addAttribute("paymentType", paymentType);
            return "payments/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("payment_type_save_success", new Object[]{}, locale)));
        paymentTypeService.save(paymentType);
        return "redirect:/payments/"+UrlUtil.encodeUrlPathSegment(paymentType.getId().toString(), httpServletRequest);
    }
    
    //@PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") Long id, Model uiModel) {
        PaymentType paymentType = paymentTypeService.findById(id);
        uiModel.addAttribute("paymentType", paymentType);
        return "payments/update";
    }
    
    @RequestMapping (params = "new", method = RequestMethod.POST)
    //@ResponseStatus(HttpStatus.CREATED)
    public String create (PaymentType paymentType, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating payment type");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("payment_type_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("paymentType", paymentType);
            return "payments/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("payment_type_save_success", new Object[]{}, locale)));
        LOGGER.info("Payment type name: " + paymentType.getPaymentType());
        paymentTypeService.save(paymentType);
        return "redirect:/payments/" + UrlUtil.encodeUrlPathSegment(paymentType.getId().toString(), httpServletRequest);
    }
    
    //@PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping (params = "new", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        PaymentType paymentType = new PaymentType();
        uiModel.addAttribute("paymentType", paymentType);
        return "payments/create";
    }
    
    @PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping (value="/{id}", params="form", method = RequestMethod.DELETE)
    public  String delete (@PathVariable ("id") Long id) {
        LOGGER.info("Deleting payment type");
        PaymentType paymentType = paymentTypeService.findById(id);
        paymentTypeService.delete(paymentType);
        LOGGER.info("Payment type delete: " + paymentType);
        return "redirect:/payments/";        
    }

    @Autowired
    public void setPaymentTypeService(PaymentTypeService paymentTypeService) {
        this.paymentTypeService = paymentTypeService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
}
