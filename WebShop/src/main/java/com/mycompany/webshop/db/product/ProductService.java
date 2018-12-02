/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product;

import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author grperets
 */
public interface ProductService {
    Set<Product> findAll();
    Product findById(Long id);
    Product findByModel(String model);
    List<Product> findByCategoryAndManufacturer (String category, String manufacturer);
    Product save (Product product);
    void delete (Long id);
    Page<Product> findAllByPage(Pageable pageable);
}
