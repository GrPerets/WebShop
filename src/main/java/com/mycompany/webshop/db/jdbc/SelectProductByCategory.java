/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jdbc;

import com.mycompany.webshop.db.Product;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.MappingSqlQuery;

/**
 *
 * @author grperets
 */
public class SelectProductByCategory extends MappingSqlQuery<Product> {
    private static final String SQL_FIND_BY_CATEGORY = "select id, model, category, manufacturer, price from product where category = :category";

    public SelectProductByCategory(DataSource dataSource) {
        super(dataSource, SQL_FIND_BY_CATEGORY);
        super.declareParameter(new SqlParameter("category", Types.VARCHAR));
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
