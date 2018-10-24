/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.customer;

import java.util.List;

/**
 *
 * @author grperets
 */
public interface CustomerService {
    List<Customer> findAll();
    Customer findById(Long id);
    Customer findByPhoneNumber(String phoneNumber);
    Customer save(Customer customer);
    void delete(Long id); 
    
}
