/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.order.Order;
import com.mycompany.webshop.db.order.OrderRepository;
import com.mycompany.webshop.db.order.OrderService;
import java.util.Set;
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
@Service ("orderService")
@Repository
@Transactional
public class OrderServiceImpl implements OrderService {
    private Log LOG = LogFactory.getLog(CustomerServiceImpl.class);
    private OrderRepository orderRepository;
    

    @Transactional (readOnly = true)
    @Override
    public Set<Order> findAll() {
        return Sets.newHashSet(orderRepository.findAll());
    }
    
    @Transactional (readOnly = true)
    @Override
    public Page<Order> findAllByPage(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Transactional (readOnly = true)
    @Override
    public Order findById(Long id) {
        return orderRepository.findOne(id);
    }

    @Transactional (readOnly = true)
    @Override
    public Set<Order> findByCustomer(Customer customer) {
        return Sets.newHashSet(orderRepository.findByCustomer(customer));
    }

    @Override
    public Order save(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public void delete(Long id) {
        orderRepository.delete(id);
    }
    
    @Autowired
    public void setOrderRepository(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }
    
    
    
}
