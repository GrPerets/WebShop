/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.manager;

import com.mycompany.webshop.db.customer.Customer;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface ManagerRepository extends PagingAndSortingRepository<Manager, Long> {
    Manager findByUsername(String username);
    
}
