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
public interface JpaProductDao extends HibernateProductDao {
        
        List<Product> findByCriteriaQuery(String categoryId, String manufacturerId);
    
}
