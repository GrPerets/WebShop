/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductRepository;
import com.mycompany.webshop.db.ProductService;
import com.mycompany.webshop.db.jpa.JpaProductDaoImpl;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service("productService")
@Repository
@Transactional
public class ProductServiceImpl implements ProductService {
    
    private Log LOG = LogFactory.getLog(JpaProductDaoImpl.class);
    
    private ProductRepository productRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        return Lists.newArrayList(productRepository.findAll());
    }
    
    @Override
    public Product findById(Long productId) {
        return productRepository.findOne(productId);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findByModel(String model) {
        return productRepository.findByModel(model);
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findByCategoryAndManufacturer(String category, String manufacturer) {
        return productRepository.findByCategoryAndManufacturer(category, manufacturer);
    }

    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }
    
    @Override
    public void delete(Product product) {
        productRepository.delete(product);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    
    
    
    
    
        
}
