/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Sets;
import com.mycompany.webshop.db.manager.Manager;
import com.mycompany.webshop.db.manager.ManagerRepository;
import com.mycompany.webshop.db.manager.ManagerService;
import java.util.Set;
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
@Service ("managerService")
@Repository
@Transactional
public class ManagerServiceImpl implements ManagerService {
    private Log LOG = LogFactory.getLog(CustomerServiceImpl.class);
    private ManagerRepository managerRepository;
    private BCryptPasswordEncoder passwordEncoder;

    @Transactional (readOnly = true)
    @Override
    public Set<Manager> findAll() {
        return Sets.newHashSet(managerRepository.findAll());
    }

    @Transactional (readOnly = true)
    @Override
    public Manager findById(Long id) {
        return managerRepository.findOne(id);
    }

    @Transactional (readOnly = true)
    @Override
    public Manager findByUserName(String userName) {
        return managerRepository.findByUserName(userName);
    }

    @Override
    public Manager save(Manager manager) {
        manager.setPassword(passwordEncoder.encode(manager.getPassword()));
        return managerRepository.save(manager);
    }

    @Override
    public void delete(Long id) {
        managerRepository.delete(id);
    }

    @Transactional (readOnly = true)
    @Override
    public Page<Manager> findAllByPage(Pageable pageable) {
        return managerRepository.findAll(pageable);
    }

    @Autowired
    public void setManagerRepository(ManagerRepository managerRepository) {
        this.managerRepository = managerRepository;
    }

    @Autowired
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }
    
    
    
}
