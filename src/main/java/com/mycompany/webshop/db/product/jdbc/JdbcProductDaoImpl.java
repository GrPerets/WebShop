/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.jdbc;

import com.mycompany.webshop.db.product.Product;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationException;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

/**
 *
 * @author grperets
 */
@Repository("jdbcProductDao")
public class JdbcProductDaoImpl implements JdbcProductDao{
    private Log LOG = LogFactory.getLog(JdbcProductDaoImpl.class);
    private DataSource dataSource;
    private SelectAllProducts selectAllProducts;
    private SelectProductById selectProductById;
    private SelectProductByModel selectProductByModel;
    private UpdateProduct updateProduct;
    private InsertProduct insertProduct;
    private DeleteProduct deleteProduct;
    

    @Override
    public List<Product> findAll() {
        return selectAllProducts.execute();
    }

    @Override
    public Product findProductById(Long productId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", productId);
        return selectProductById.executeByNamedParam(paramMap).get(0);        
    }

    @Override
    public List<Product> findProductByModel(String model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("model", model);
        return selectProductByModel.executeByNamedParam(paramMap);
    }
    
    @Override
    public void insertProduct(Product product) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("model", product.getModel());
        paramMap.put("category", product.getCategory());
        paramMap.put("manufacturer", product.getManufacturer());
        paramMap.put("price", product.getPrice());
        KeyHolder keyHolder = new GeneratedKeyHolder();
        insertProduct.updateByNamedParam(paramMap, keyHolder);
        product.setId(keyHolder.getKey().longValue());
        LOG.info("New product insert with id: " + product.getId());
    }

    @Override
    public void updateProduct(Product product) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("model", product.getModel());
        paramMap.put("category", product.getCategory());
        paramMap.put("manufacturer", product.getManufacturer());
        paramMap.put("price", product.getPrice());
        paramMap.put("id", product.getId());
        updateProduct.updateByNamedParam(paramMap);
        LOG.info("Existing product update with id: " + product.getId());
    }

    @Override
    public void deleteProduct(Long productId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", productId);
        deleteProduct.updateByNamedParam(paramMap);
        LOG.info("Delete product with id: " + productId);
    }

    public DataSource getDataSource() {
        return dataSource;
    }

    @Resource(name="dataSource")
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.selectAllProducts = new SelectAllProducts(dataSource);
        this.selectProductById = new SelectProductById(dataSource);
        this.selectProductByModel = new SelectProductByModel(dataSource);
        this.updateProduct = new UpdateProduct(dataSource);
        this.insertProduct = new InsertProduct(dataSource);
        this.deleteProduct = new DeleteProduct(dataSource);
    }
    
    @PostConstruct
    public void postConstruct() {
        if (dataSource == null) {
            throw new BeanCreationException("Must set dataSource on ProductDao");
        }
    
    }
    
    
}
