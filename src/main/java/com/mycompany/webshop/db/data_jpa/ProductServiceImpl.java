/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.product.Product;
import com.mycompany.webshop.db.product.ProductRepository;
import com.mycompany.webshop.db.product.ProductService;
import com.mycompany.webshop.db.product.jpa.JpaProductDaoImpl;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    
    private Log LOG = LogFactory.getLog(ProductServiceImpl.class);
    
    private ProductRepository productRepository;

    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        return Lists.newArrayList(productRepository.findAll());
    }
    
    @Transactional (readOnly=true)
    @Override
    public Product findById(Long id) {
        return productRepository.findOne(id);
    }

    @Transactional(readOnly=true)
    @Override
    public Product findByModel(String model) {
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
    public void delete(Long id) {
        productRepository.delete(id);
    }
    
    @Transactional (readOnly=true)
    @Override
    public Page<Product> findAllByPage(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    

    
    
    
    
    
        
}
