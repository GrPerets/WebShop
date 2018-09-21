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
import javax.annotation.Resource;
import javax.sql.DataSource;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
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
    public List<Product> findProductByCategory(String category) {
        Map<String,Object> paramMap = new HashMap<String, Object>();
        paramMap.put("category", category);
        return selectProductByCategory.executeByNamedParam(paramMap);
    }

    @Override
    public List<Product> findProductByManufacturer(String manufacturer) {
        Map<String, Object> paramMap = new HashMap<String, Object>();
        paramMap.put("manufacturer", manufacturer);
        return selectProductByManufacturer.executeByNamedParam(paramMap);
    }

    @Override
    public void insertProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateProduct(Product product) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteProduct(Long productId) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
    }
    
    //public void init(){}
    
    
}
