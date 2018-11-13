/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.controllers;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.category.Category;
import com.mycompany.webshop.db.category.CategoryService;
import com.mycompany.webshop.db.manufacturer.Manufacturer;
import com.mycompany.webshop.db.manufacturer.ManufacturerService;
import com.mycompany.webshop.db.product.Product;
import com.mycompany.webshop.db.product.ProductService;
import com.mycompany.webshop.service_and_special_classes.Message;
import com.mycompany.webshop.service_and_special_classes.ProductGrid;
import com.mycompany.webshop.service_and_special_classes.UrlUtil;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Locale;
import java.util.Set;
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
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;
import org.joda.time.DateTime;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestBody;


/**
 *
 * @author grperets
 */
@Controller
@RequestMapping("/products")
public class ProductController {
    private final Logger LOGGER = LoggerFactory.getLogger(ProductController.class);
    private ProductService productService;
    private MessageSource messageSource;
    private CategoryService categoryService;
    private ManufacturerService manufacturerService;
    
        
    @RequestMapping(method = RequestMethod.GET)
    //@ResponseBody
    public String list(@RequestParam (value = "basketId", required=false) Long basketId, Model uiModel) {
        LOGGER.info("Listing products");
        Set<Product> products = productService.findAll();
        uiModel.addAttribute("products", products);
        LOGGER.info("No. of products: " + products.size());
        LOGGER.info("BasketId: " + basketId);
        uiModel.addAttribute("basketId", basketId);
        
        return "products/list";
    }
    
    
    @RequestMapping (value = "/listgrid", method = RequestMethod.GET, produces = "application/json")
    @ResponseBody
    public ProductGrid<Product> listGrid(
            @RequestParam(value = "page", defaultValue = "1", required = false) Integer page,
            @RequestParam(value = "rows", defaultValue = "10", required = false) Integer rows,
            @RequestParam(value = "sidx", defaultValue = "id", required = false) String sortBy,
            @RequestParam(value = "sord", defaultValue = "ASC", required = false) String order) {
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
        ProductGrid<Product> productGrid = new ProductGrid<>();
        productGrid.setCurrentPage(productPage.getNumber() + 1);
        productGrid.setTotalPages(productPage.getTotalPages());
        productGrid.setTotalRecords(productPage.getTotalElements());
        productGrid.setProductData(Sets.newHashSet(productPage.iterator()));
        
        return productGrid;
    }
    
    
    @RequestMapping(value = "/{id}", method = RequestMethod.GET )
    public String show (@PathVariable("id") Long id, @RequestParam (value = "basketId", required=false) Long basketId, Model uiModel) {
        Product product = productService.findById(id);
        uiModel.addAttribute("product", product);
        uiModel.addAttribute("basketId", basketId);
        return "products/show";
    }
    
    @RequestMapping(value = "/{id}", params = "form", method = RequestMethod.POST )
    public String update (Product product, BindingResult bindingResult,
                            Model uiModel, HttpServletRequest httpServletRequest,
                            RedirectAttributes redirectAttributes, Locale locale,
                            @RequestParam (value = "file", required = false) Part file) {
        LOGGER.info("Updating product");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message("error", messageSource.getMessage("product_save_fail", new Object[]{}, locale)));
            uiModel.addAttribute("product", product);
        return "products/update";
    }
    uiModel.asMap().clear();
    redirectAttributes.addFlashAttribute("message", new Message("success", messageSource.getMessage("product_save_success", new Object[]{}, locale)));
    LOGGER.info("Product id: " + product.getId());
    
    if(file != null) {
            LOGGER.info("File name: " + file.getName());
            LOGGER.info("File size: " + file.getSize());
            LOGGER.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) LOGGER.info("File inputstream is null");
                fileContent = IOUtils.toByteArray(inputStream);
                product.setPhoto(fileContent);
            } catch (IOException ex) {
                LOGGER.error("Error saving uploaded file");
            }
            product.setPhoto(fileContent);
            
        }
    product.setDateLastModified(new DateTime());
    productService.save(product);
    return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }
    
    @PreAuthorize ("hasRole('ROLE_MANAGER')")
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
    
    @RequestMapping (params = "new",  method = RequestMethod.POST)
    public String create (Product product, BindingResult bindingResult,
                            Model uiModel, HttpServletRequest httpServletRequest,
                            RedirectAttributes redirectAttributes, Locale locale,
                            @RequestParam (value = "file", required = false) Part file) {
        LOGGER.info("Creating product");
        if (bindingResult.hasErrors()) {
            uiModel.addAttribute("message", new Message ("error", messageSource.getMessage("product_save_fail", new Object[] {}, locale)));
            uiModel.addAttribute("product", product);
            return "products/create";
        }
        uiModel.asMap().clear();
        redirectAttributes.addFlashAttribute("message", new Message ("success", messageSource.getMessage("product_save_success", new Object[]{}, locale)));
        LOGGER.info("Product id: " + product.getId());
        
        if(file != null) {
            LOGGER.info("File name: " + file.getName());
            LOGGER.info("File size: " + file.getSize());
            LOGGER.info("File content type: " + file.getContentType());
            byte[] fileContent = null;
            try {
                InputStream inputStream = file.getInputStream();
                if (inputStream == null) LOGGER.info("File inputstream is null");
                fileContent = IOUtils.toByteArray(inputStream);
                product.setPhoto(fileContent);
            } catch (IOException ex) {
                LOGGER.error("Error saving uploaded file");
            }
            product.setPhoto(fileContent);
        } 
        product.setDateLastModified(new DateTime());
        productService.save(product);
        return "redirect:/products/" + UrlUtil.encodeUrlPathSegment(product.getId().toString(), httpServletRequest);
    }
    
    @RequestMapping (value = "/photo/{id}", method = RequestMethod.GET)
    @ResponseBody
    public byte[] downloadPhoto (@PathVariable ("id") Long id) {
        Product product = productService.findById(id);
        if (product.getPhoto() != null) {
            LOGGER.info("Downloading photo for id: {} with size: {}", product.getId(), product.getPhoto().length);
        }
        return product.getPhoto();
    }
    
    @PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping (params ="new", method = RequestMethod.GET)
    public String createForm (Model uiModel) {
        List<Category> categories = categoryService.findAll();
        uiModel.addAttribute("categories", categories);
        List<Manufacturer> manufacturers = manufacturerService.findAll();
        uiModel.addAttribute("manufacturers", manufacturers);
        Product product = new Product();
        uiModel.addAttribute("product", product);
        return "products/create";
    }
    
    @PreAuthorize ("hasRole('ROLE_MANAGER')")
    @RequestMapping (value ="/{id}", params = "form", method = RequestMethod.DELETE)
    public String delete (@PathVariable("id") Long id) {
        LOGGER.info("Deleting product");
        productService.delete(id);
        LOGGER.info("Delete product with Id: " + id);
        return "redirect:/products";
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
