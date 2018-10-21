/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.Category;
import com.mycompany.webshop.db.CategoryService;
import com.mycompany.webshop.db.Manufacturer;
import com.mycompany.webshop.db.ManufacturerService;
import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductService;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.ProductGrid;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;
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
@RequestMapping("/products")
@Controller
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    private MessageSource messageSource;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;
        
    @RequestMapping(method = RequestMethod.GET)
    public String list(Model uiModel) {
        LOGGER.info("Listing products");
        List<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        LOGGER.info("No. of products: " + products.size());
        return "products/list";
    }
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ProductGrid listGrid(@RequestParam(value = "page", required = false) Integer page, @RequestParam(value = "rows", required = false) Integer rows,
            @RequestParam(value = "sidx", required = false) String sortBy, @RequestParam(value = "sort", required = false) String order) {
        LOGGER.info("Licting products for grid with page: {}, rows: {}", page, rows);
        LOGGER.info("Listing products for grid with sort: {}, order: {}", sortBy, order);
        Sort sort = null;
        String orderBy = sortBy;
        
        if (orderBy != null && orderBy.equals("dateLastModifiedString"))
            orderBy = "dateLastModified";
        if (orderBy != null && order != null) {
            if (order.equals("desc")) {
                sort = new Sort (Sort.Direction.DESC, orderBy);
            } else sort = new Sort(Sort.Direction.ASC, orderBy);
        }
        PageRequest pageRequest = null;
        if (sort != null) {
            pageRequest = new PageRequest (page - 1, rows, sort);
        } else {
            pageRequest = new PageRequest (page - 1, rows);
        }
        
        Page<Product> productPage = productService.findAllByPage(pageRequest);
        //JSON grid
        ProductGrid productGrid = new ProductGrid();
        productGrid.setCurrentPage(productPage.getNumber() + 1);
        productGrid.setTotalPages(productPage.getTotalPages());
        productGrid.setTotalRecords(productPage.getTotalElements());
        productGrid.setProductData(Lists.newArrayList(productPage.iterator()));
        
        LOGGER.info("No. of products>>>>: " + productGrid.getProductData().size());
        return productGrid;
    }
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public String show (@PathVariable("id") Long id, Model uiModel) {
        Product product = productService.findById(id);
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
    public String updateForm(@PathVariable("id") Long id, Model uiModel) {
        List<Category> categories = categoryService.findAll();
        uiModel.addAttribute("categories", categories);
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        uiModel.addAttribute("manufacturers", manufacturers);
        Product product = productService.findById(id);
        uiModel.addAttribute("product", product);
        return "products/update";
    }
    
    @RequestMapping (params = "form", method = RequestMethod.POST)
    public String create (Product product, BindingResult bindingResult, Model uiModel, HttpServletRequest httpServletRequest, RedirectAttributes redirectAttributes, Locale locale) {
        LOGGER.info("Creating product");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("product_save_fail", new Object[] {}, locale)));
            uiModel.addAttribute("product", product);
            return "products/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("product_save_success", new Object[]{}, locale)));
        LOGGER.info("Product id: " + product.getId());
        productService.save(product);
        return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (params ="form", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        List<Category> categories = categoryService.findAll();
        uiModel.addAttribute("categories", categories);
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        uiModel.addAttribute("manufacturers", manufacturers);
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "products/create";
    }
    
    @RequestMapping (value ="/{id}", params = "form", method = RequestMethod.DELETE)
    public String delete (@PathVariable("id") Long id) {
        LOGGER.info("Deleting products");
        productService.delete(id);
        LOGGER.info("Delete product Id: " + id);
        return "redirect:/products/";
    }
    

    @Autowired
    public void setProductService(ProductService productService) {
        this.productService = productService;
    }

    @Autowired
    public void setMessageSource(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    @Autowired
    public void setCategoryService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @Autowired
    public void setManufacturerService(ManufacturerService manufacturerService) {
        this.manufacturerService = manufacturerService;
    }
        
}
