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
    List<Product> findProductById(Long productId);
    List<Product> findProductByModel(String model);
    List<Product> findProductByCategoryId(Long categoryId);
    List<Product> findProductByManufacturerId(Long manufacturerId);
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);
}
