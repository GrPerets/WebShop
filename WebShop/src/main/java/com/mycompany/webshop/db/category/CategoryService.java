/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.category;

import java.util.List;

/**
 *
 * @author grperets
 */
public interface CategoryService {
    List<Category> findAll();
    Category findById (Long id);
    Category save (Category category);
    void delete (Category category);
    
}
