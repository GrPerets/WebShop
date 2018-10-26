/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.customer.CustomerRepository;
import com.mycompany.webshop.db.customer.CustomerService;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service ("customerService")
@Repository
@Transactional
public class CustomerServiceImpl implements CustomerService {
    private Log LOG = LogFactory.getLog(CustomerServiceImpl.class);
    private CustomerRepository customerRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional (readOnly = true)
    @Override
    public List<Customer> findAll() {
        return Lists.newArrayList(customerRepository.findAll());
    }
    
    @Transactional (readOnly=true)
    @Override
    public Page<Customer> findAllByPage(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional (readOnly = true)
    @Override
    public Customer findById(Long id) {
        return customerRepository.findOne(id);
    }
    
    @Transactional (readOnly=true)
    @Override
    public Customer findByPhoneNumber(String phoneNumber) {
        return customerRepository.findByPhoneNumber(phoneNumber);
    }

    @Override
    public Customer save(Customer customer) {
        customer.setPassword(passwordEncoder.encode(customer.getPassword()));
        return customerRepository.save(customer);
    }

    @Override
    public void delete(Long id) {
        customerRepository.delete(id);
    }

    @Autowired
    public void setCustomerRepository(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
}
