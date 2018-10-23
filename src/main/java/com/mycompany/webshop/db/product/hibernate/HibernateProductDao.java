/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.hibernate;

import com.mycompany.webshop.db.product.ProductDao;
import com.mycompany.webshop.db.product.Product;

/**
 *
 * @author grperets
 */
public interface HibernateProductDao extends ProductDao {
    Product save (Product product);
    void delete (Product product);
    
}
