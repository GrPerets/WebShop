/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.order;

import com.mycompany.webshop.db.customer.Customer;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author grperets
 */
public interface OrderService {
    Set<Order> findAll();
    Order findById(Long id);
    Set<Order> findByCustomer(Customer customer);
    Order save (Order order);
    void delete (Long id);
    Page<Order> findAllByPage(Pageable pageable);
    
}
