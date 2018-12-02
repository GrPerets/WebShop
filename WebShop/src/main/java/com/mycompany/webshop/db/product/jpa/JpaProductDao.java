/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.jpa;

import com.mycompany.webshop.db.product.hibernate.HibernateProductDao;
import com.mycompany.webshop.db.product.Product;
import java.util.List;

/**
 *
 * @author grperets
 */
public interface JpaProductDao extends HibernateProductDao {
        
        List<Product> findByCriteriaQuery(String category, String manufacturer);
    
}
