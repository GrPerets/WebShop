/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.basket;

import com.mycompany.webshop.db.basket.Basket;
import java.util.List;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author grperets
 */
public interface BasketService {
    Set<Basket> findAll();
    Basket findById(Long id);
    List<Basket> findByCustomerId(Long customerId);
    Basket save (Basket basket);
    void delete (Long id);
    Page<Basket> findAllByPage(Pageable pageable);
}
