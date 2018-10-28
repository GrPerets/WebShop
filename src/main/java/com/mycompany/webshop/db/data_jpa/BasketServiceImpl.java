/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.basket.BasketRepository;
import com.mycompany.webshop.db.basket.BasketService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service ("basketServece")
@Repository
@Transactional
public class BasketServiceImpl implements BasketService {
    private final Log LOG = LogFactory.getLog(BasketServiceImpl.class);
    private BasketRepository basketRepository;

    @Transactional (readOnly = true)
    @Override
    public List<Basket> findAll() {
        return Lists.newArrayList(basketRepository.findAll());
    }

    @Transactional (readOnly = true)
    @Override
    public Basket findById(Long id) {
        return basketRepository.findOne(id);
    }

    @Transactional (readOnly = true)
    @Override
    public List<Basket> findByCustomerId(Long customerId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Basket save(Basket basket) {
        return basketRepository.save(basket);
    }

    @Override
    public void delete(Long id) {
        basketRepository.delete(id);
    }

    @Transactional (readOnly = true)
    @Override
    public Page<Basket> findAllByPage(Pageable pageable) {
        return basketRepository.findAll(pageable);
    }

    @Autowired
    public void setBasketRepository(BasketRepository basketRepository) {
        this.basketRepository = basketRepository;
    }
    
    
}
