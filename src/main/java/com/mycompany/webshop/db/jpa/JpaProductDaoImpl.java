/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jpa;

import com.mycompany.webshop.db.JpaProductDao;
import com.mycompany.webshop.db.Product;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author grperets
 */
@Service("jpaProductDao")
@Repository
@Transactional
public class JpaProductDaoImpl implements JpaProductDao {
    private Log LOG = LogFactory.getLog(JpaProductDaoImpl.class);
    @PersistenceContext
    private EntityManager entityManager;

    @Transactional(readOnly=true)
    @Override
    public List<Product> findAll() {
        List<Product> products = entityManager.createNamedQuery("Product.findAll", Product.class).getResultList();
        return products;
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findProductById(Long productId) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findById", Product.class);
        query.setParameter("id", productId);
        return query.getResultList();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findProductByModel(String model) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findProductByModel", Product.class);
        query.setParameter("model", model);
        return query.getResultList();
    }

    @Transactional(readOnly= true)
    @Override
    public List<Product> findProductByCategoryId(String categoryId) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findProductByCategory", Product.class);
        query.setParameter("category_id", categoryId);
        return query.getResultList();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findProductByManufacturerId(String manufacturerId) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findProductByManufacturer", Product.class);
        query.setParameter("manufacturer_id", manufacturerId);
        return query.getResultList();
    }
    
    @Override
    public Product save(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void delete(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
