/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jpa;

import com.mycompany.webshop.db.JpaProductDao;
import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.metamodels.Product_;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
    public Product findProductById(Long productId) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findById", Product.class);
        query.setParameter("id", productId);
        return query.getSingleResult();
    }

    @Transactional(readOnly=true)
    @Override
    public List<Product> findProductByModel(String model) {
        TypedQuery<Product> query = entityManager.createNamedQuery("Product.findProductByModel", Product.class);
        query.setParameter("model", model);
        return query.getResultList();
    }

    @Override
    public Product save(Product product) {
        if (product.getId() == null) {
            LOG.info("Inserting new product");
            entityManager.persist(product);
        } else {
            entityManager.merge(product);
            LOG.info("Updating existing product");
        }    
        LOG.info("Product saved with id: " + product.getId());
        return product;
        
    }

    @Override
    public void delete(Product product) {
        Product mergedProduct = entityManager.merge(product);
        entityManager.remove(mergedProduct);
        LOG.info("Product with id: " + product.getId() + " deleted successfully");
    }

    @Transactional (readOnly=true)
    @Override
    public List<Product> findByCriteriaQuery(String categoryId, String manufacturerId) {
        LOG.info("Finding product for categoryId: " + categoryId + " and manufacturerId: " + manufacturerId);
        CriteriaBuilder criteriaBuilder = entityManager.getCriteriaBuilder();
        CriteriaQuery<Product> criteriaQuery = criteriaBuilder.createQuery(Product.class);
        Root<Product> productRoot = criteriaQuery.from(Product.class);
        criteriaQuery.select(productRoot);
        Predicate criteria = criteriaBuilder.conjunction();
        if (categoryId != null) {
            Predicate predicate = criteriaBuilder.equal(productRoot.get(Product_.categoryId), categoryId);
            criteria = criteriaBuilder.and(criteria, predicate);
        }
        if (manufacturerId != null) {
            Predicate predicate = criteriaBuilder.equal(productRoot.get(Product_.manufacturerId), manufacturerId);
            criteria = criteriaBuilder.and(criteria, predicate);
        }
        criteriaQuery.where(criteria);
        return entityManager.createQuery(criteriaQuery).getResultList();
    }
    
}
