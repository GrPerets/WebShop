/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jdbc;

import com.mycompany.webshop.db.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author grperets
 */
public class SelectAllProducts extends MappingSqlQuery<Product> {
    private static final String SQL_SELECT_ALL_PRODUCT = "select id, model, category_id, manufacturer_id, price from product";

    public SelectAllProducts(DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_PRODUCT);
    }
    
    

    @Override
    protected Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setModel(rs.getString("model"));
        product.setCategoryId(rs.getLong("category_id"));
        product.setManufacturerId(rs.getLong("manufacturer_id"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }
    
}
