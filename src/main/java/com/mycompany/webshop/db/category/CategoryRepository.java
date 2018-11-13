/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.category;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author grperets
 */
public interface CategoryRepository extends CrudRepository <Category, Long> {
    
}
