/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.manager;

import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author grperets
 */
public interface ManagerService {
    Set<Manager> findAll();
    Manager findById(Long id);
    Manager findByUserName(String userName);
    Manager save(Manager manager);
    void delete(Long id);
    Page<Manager> findAllByPage(Pageable pageable);
    
}
