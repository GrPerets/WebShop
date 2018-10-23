/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.manufacturer;

import java.util.List;

/**
 *
 * @author grperets
 */
public interface ManufacturerService {
    List<Manufacturer> findAll();
    Manufacturer findById(Long id); 
    Manufacturer save (Manufacturer manufacturer);
    void delete (Manufacturer manufacturer);    
}
