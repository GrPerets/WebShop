/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import java.util.List;

/**
 *
 * @author grperets
 */
public interface ProductDao {
    List<Product> findAll();
    Product findProductById(Long productId);
    List<Product> findProductByModel(String model);
}
