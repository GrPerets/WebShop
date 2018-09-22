/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jdbc;

import com.mycompany.webshop.db.Product;
import com.mycompany.webshop.db.ProductDao;
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
@Repository("productDao")
public class JdbcProductDaoImpl implements ProductDao{
    private Log LOG = LogFactory.getLog(JdbcProductDaoImpl.class);
    private DataSource dataSource;
    private SelectAllProducts selectAllProducts;
    private SelectProductById selectProductById;
    private SelectProductByModel selectProductByModel;
    private SelectProductByCategory selectProductByCategory;
    private SelectProductByManufacturer selectProductByManufacturer;
    private UpdateProduct updateProduct;
    private InsertProduct insertProduct;
    private DeleteProduct deleteProduct;

    @Override
    public List<Product> findAll() {
        return selectAllProducts.execute();
    }

    @Override
    public List<Product> findProductById(Long productId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("id", productId);
        return selectProductById.executeByNamedParam(paramMap);        
    }

    @Override
    public List<Product> findProductByModel(String model) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("model", model);
        return selectProductByModel.executeByNamedParam(paramMap);
    }

    @Override
    public List<Product> findProductByCategoryId(Long categoryId) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("category_id", categoryId);
        return selectProductByCategory.executeByNamedParam(paramMap);
    }

    @Override
    public List<Product> findProductByManufacturerId(Long manufacturerId) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("manufacturer_id", manufacturerId);
        return selectProductByManufacturer.executeByNamedParam(paramMap);
    }

    @Override
    public void insertProduct(Product product) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("model", product.getModel());
        paramMap.put("category_id", product.getCategoryId());
        paramMap.put("manufacturer_id", product.getManufacturerId());
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
        paramMap.put("category_id", product.getCategoryId());
        paramMap.put("manufacturer_id", product.getManufacturerId());
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
        this.selectProductByCategory = new SelectProductByCategory(dataSource);
        this.selectProductByManufacturer = new SelectProductByManufacturer(dataSource);
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
