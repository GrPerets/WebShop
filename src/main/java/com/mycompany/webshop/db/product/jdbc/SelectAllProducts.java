/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.jdbc;

import com.mycompany.webshop.db.product.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.sql.DataSource;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author grperets
 */
public class SelectAllProducts extends MappingSqlQuery<Product> {
    private static final String SQL_SELECT_ALL_PRODUCT = "select id, model, category, manufacturer, price from product";

    public SelectAllProducts(DataSource dataSource) {
        super(dataSource, SQL_SELECT_ALL_PRODUCT);
    }
    
    

    @Override
    protected Product mapRow(ResultSet rs, int rowNum) throws SQLException {
        Product product = new Product();
        product.setId(rs.getLong("id"));
        product.setModel(rs.getString("model"));
        product.setCategory(rs.getString("category"));
        product.setManufacturer(rs.getString("manufacturer"));
        product.setPrice(rs.getDouble("price"));
        return product;
    }
    
}
