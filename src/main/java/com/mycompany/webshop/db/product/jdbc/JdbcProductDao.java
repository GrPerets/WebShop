/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.jdbc;

import com.mycompany.webshop.db.product.ProductDao;
import com.mycompany.webshop.db.product.Product;

/**
 *
 * @author grperets
 */
public interface JdbcProductDao extends ProductDao{
    void insertProduct(Product product);
    void updateProduct(Product product);
    void deleteProduct(Long productId);
}
