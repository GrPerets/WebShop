/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.basket;

import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.customer.Customer;
import java.util.List;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface BasketRepository extends PagingAndSortingRepository<Basket, Long> {
    Set<Basket> findByCustomer(Customer customer);
    
}
