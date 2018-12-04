/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.photo.ProductPhoto;
import com.mycompany.webshop.db.photo.ProductPhotoRepository;
import com.mycompany.webshop.db.photo.ProductPhotoService;
import com.mycompany.webshop.db.product.Product;
import java.util.Set;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.slf4j.Logger;
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
@Service("productPhotoService")
@Repository
@Transactional
public class ProductPhotoServiceImpl implements ProductPhotoService {
    private Log LOG = LogFactory.getLog(ProductPhotoServiceImpl.class);
    private ProductPhotoRepository productPhotoRepository;

    @Transactional(readOnly=true)
    @Override
    public Set<ProductPhoto> findAll() {
        return Sets.newHashSet(productPhotoRepository.findAll());
    }

    @Transactional(readOnly=true)
    @Override
    public ProductPhoto findById(Long id) {
        return productPhotoRepository.findOne(id);
    }

    @Transactional(readOnly=true)
    @Override
    public Set<ProductPhoto> findByProduct(Product product) {
        return Sets.newHashSet(productPhotoRepository.findByProduct(product));
    }

    @Override
    public ProductPhoto save(ProductPhoto productPhoto) {
        return productPhotoRepository.save(productPhoto);
    }

    @Override
    public void delete(Long id) {
        productPhotoRepository.delete(id);
    }

    @Transactional(readOnly=true)
    @Override
    public Page<ProductPhoto> findAllByPage(Pageable pageable) {
        return productPhotoRepository.findAll(pageable);
    }

    
    @Autowired
    public void setProductPhotoRepository(ProductPhotoRepository productPhotoRepository) {
        this.productPhotoRepository = productPhotoRepository;
    }
    
    
    
}
