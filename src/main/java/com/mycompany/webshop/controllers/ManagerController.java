/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.manager.Manager;
import com.mycompany.webshop.db.manager.ManagerService;
import com.mycompany.webshop.service_and_special_classes.CustomerGrid;
import com.mycompany.webshop.service_and_special_classes.ManagerGrid;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
@RequestMapping ("/managers")
public class ManagerController {
    private final Logger LOGGER = LoggerFactory.getLogger(ManagerController.class);
    private ManagerService managerService;
    private MessageSource messageSource;

    
    //@PreAuthorize ("hasRole('ROLE_ADMIN')")
    @RequestMapping (method = RequestMethod.GET)
    public String list (Model uiModel) {
        LOGGER.info("Listing managers");
        Set<Manager> managers = managerService.findAll();
        uiModel.addAttribute("managers", managers);
        LOGGER.info("No. of managers: " + managers.size());
        return "managers/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ManagerGrid<Manager> listGrid (@RequestParam (value = "page", required = false) Integer page,
        @RequestParam (value = "rows", required = false) Integer rows,
        @RequestParam (value = "sidx", required = false) String sortBy,
        @RequestParam (value = "sord", required = false) String order) {
        LOGGER.info("Listing managers for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing managers for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        if (orderBy != null && orderBy.equals("registrationDateString"))
            orderBy = "registrationDate";
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
        Page<Manager> managerPage = managerService.findAllByPage(pageRequest);
        ManagerGrid<Manager> managerGrid = new ManagerGrid<>();
        managerGrid.setCurrentPage(managerPage.getNumber() + 1);
        managerGrid.setTotalPages(managerPage.getTotalPages());
        managerGrid.setTotalRecords(managerPage.getTotalElements());
        managerGrid.setManagerData(Sets.newHashSet(managerPage.iterator()));
        return managerGrid;
    }
    
    @RequestMapping (value = "/{id}", method = RequestMethod.GET)
    public String show (@PathVariable ("id") Long id, Model uiModel) {
        Manager manager = managerService.findById(id);
        uiModel.addAttribute("manager", manager);
        return "managers/show";
    }
    
    @RequestMapping (value = "/{id}", params = "form", method = RequestMethod.POST)
    public String update (@Valid Manager manager, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Updating manager");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("manager_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("manager", manager);
            return "managers/update";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("manager_save_success", new Object[]{}, locale)));
        managerService.save(manager);
        return "redirect:/managers/" + UrlUtil.encodeUrlPathSegment(manager.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/{id}", params ="form", method = RequestMethod.GET)
    public String updateForm (@PathVariable ("id") Long id, Model uiModel) {
        Manager manager = managerService.findById(id);
        uiModel.addAttribute("manager", manager);
        return "managers/update";
    }
    
    @RequestMapping(params = "new", method = RequestMethod.POST)
    public String create(@Valid Manager manager, BindingResult bindingResult,
                        Model uiModel, HttpServletRequest httpServletRequest,
                        RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating manager");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("manager_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("manager", manager);
            return "managers/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("manager_save_success", new Object[]{}, locale)));
        LOGGER.info("Manager id: " + manager.getId());
        managerService.save(manager);
        return "redirect:/managers/" + UrlUtil.encodeUrlPathSegment(manager.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (params = "new", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        Manager manager = new Manager();
        uiModel.addAttribute("manager", manager);
        return "managers/create";
    }
    
    @RequestMapping (value ="/{id}", params = "form", method = RequestMethod.DELETE)
    public String delete (@PathVariable("id") Long id) {
        LOGGER.info("Deleting manager");
        managerService.delete(id);
        LOGGER.info("Delete manager with Id: " + id);
        return "redirect:/managers";
    }
    
    @Autowired
    public void setManagerService(ManagerService managerService) {
        this.managerService = managerService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }
    
    
}
