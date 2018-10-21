/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface ProductRepository extends PagingAndSortingRepository<Product, Long> {
    List<Product> findByModel(String model);
    List<Product> findByCategoryAndManufacturer (String category, String manufacturer);
}
