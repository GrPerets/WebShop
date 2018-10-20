/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.data_jpa;

import com.google.common.collect.Lists;
import com.mycompany.webshop.db.Category;
import com.mycompany.webshop.db.CategoryRepository;
import com.mycompany.webshop.db.CategoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service ("categoryService")
@Repository
@Transactional
public class CategoryServiceImpl implements CategoryService {

    private CategoryRepository categoryRepository;
    
    @Transactional (readOnly=true)
    @Override
    public List<Category> findAll() {
        return Lists.newArrayList(categoryRepository.findAll());
    }
    
    @Override
    public Category findById(Long id) {
        return categoryRepository.findOne(id);
    }
    
    @Override
    public Category save(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public void delete(Category category) {
        categoryRepository.delete(category);
    }
    
    @Autowired
    public void setCategoryRepository(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    

    
    
    
    
}
