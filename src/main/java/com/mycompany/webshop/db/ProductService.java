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
public interface ProductService {
    List<Product> findAll();
    List<Product> findByModel(String model);
    List<Product> findByCategoryIdAndManufacturerId (String categoryId, String manufacturerId);
}
