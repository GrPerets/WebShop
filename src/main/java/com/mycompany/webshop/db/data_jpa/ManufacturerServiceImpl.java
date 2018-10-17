/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.Manufacturer;
import com.mycompany.webshop.db.ManufacturerRepository;
import com.mycompany.webshop.db.ManufacturerService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service ("manufacturerService")
@Repository
@Transactional
public class ManufacturerServiceImpl implements ManufacturerService {
    
    private ManufacturerRepository manufacturerRepository;

    @Transactional (readOnly=true)
    @Override
    public List<Manufacturer> findAll() {
        return Lists.newArrayList(manufacturerRepository.findAll());
    }
    
    @Override
    public Manufacturer findById(String manufacturer) {
        return manufacturerRepository.findOne(manufacturer);
    }

    @Override
    public Manufacturer save(Manufacturer manufacturer) {
        return manufacturerRepository.save(manufacturer);
    }
    
    @Override
    public void delete(Manufacturer manufacturer) {
        manufacturerRepository.delete(manufacturer);
    }

    @Autowired
    public void setManufacturerRepository(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    
    
    
}
