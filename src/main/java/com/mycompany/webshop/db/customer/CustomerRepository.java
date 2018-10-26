/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.customer;

import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface CustomerRepository extends PagingAndSortingRepository<Customer, Long>{
    Customer findByPhoneNumber(String phoneNumber);
}
