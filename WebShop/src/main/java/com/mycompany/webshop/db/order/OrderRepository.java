/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.order;

import com.mycompany.webshop.db.customer.Customer;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface OrderRepository extends PagingAndSortingRepository<Order, Long> {
    Set<Order> findByCustomer(Customer customer);
    
}
