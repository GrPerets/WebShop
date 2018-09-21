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
public class SelectProductById extends MappingSqlQuery<Product> {
    private static final String SQL_FIND_BY_ID = "select id, model, category, manufacturer, price from product where id=:id";

    public SelectProductById(DataSource dataSource) {
        super(dataSource, SQL_FIND_BY_ID);
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
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
