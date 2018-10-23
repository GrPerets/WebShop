/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product.jdbc;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

/**
 *
 * @author grperets
 */
public class InsertProduct extends SqlUpdate {
    private static final String SQL_INSERT_PRODUCT = "insert into product (model, category, manufacturer, price) values (:model, :category, :manufacturer, :price)";

    public InsertProduct(DataSource dataSource) {
        super(dataSource, SQL_INSERT_PRODUCT);
        super.declareParameter(new SqlParameter("model", Types.VARCHAR));
        super.declareParameter(new SqlParameter("category", Types.VARCHAR));
        super.declareParameter(new SqlParameter("manufacturer", Types.VARCHAR));
        super.declareParameter(new SqlParameter("price", Types.DOUBLE));
        super.setGeneratedKeysColumnNames(new String[] {"id"});
        super.setReturnGeneratedKeys(true);
    }
    
    
    
}
