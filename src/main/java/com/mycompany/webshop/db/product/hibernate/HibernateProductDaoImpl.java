/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.hibernate;

import com.mycompany.webshop.db.product.Product;
import java.util.List;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;



/**
 *
 * @author grperets
 */
@Transactional
@Repository("hibernateProductDao")
public class HibernateProductDaoImpl implements HibernateProductDao{
    private static final Log LOG = LogFactory.getLog(HibernateProductDaoImpl.class);
    private SessionFactory sessionFactory;

    
    @Override
    @Transactional(readOnly=true)
    public List<Product> findAll() {
        return sessionFactory.getCurrentSession().getNamedQuery("Product.findAll").list();
    }

    @Override
    @Transactional(readOnly=true)
    public Product findProductById(Long productId) {
        return (Product) sessionFactory.getCurrentSession().getNamedQuery("Product.findById").setParameter("id", productId).uniqueResult();
    }

    @Override
    @Transactional(readOnly=true)
    public List<Product> findProductByModel(String model) {
        return sessionFactory.getCurrentSession().getNamedQuery("Product.findProductByModel").setParameter("model", model).list();
    }

    @Override
    public Product save(Product product) {
        sessionFactory.getCurrentSession().saveOrUpdate(product);
        LOG.info("Product saved with id: " + product.getId());
        return product;
    }

    @Override
    public void delete(Product product) {
        sessionFactory.getCurrentSession().delete(product);
        LOG.info("Product deleted with id: " + product.getId());
    }

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    //@Resource (name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    
    
    
    
}
