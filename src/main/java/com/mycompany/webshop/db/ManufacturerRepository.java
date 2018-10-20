/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import org.springframework.data.repository.CrudRepository;

/**
 *
 * @author grperets
 */
public interface ManufacturerRepository extends CrudRepository <Manufacturer, Long> {
    
}
