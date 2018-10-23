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
public class DeleteProduct extends SqlUpdate {
    private static final String SQL_DELETE_PRODUCT = "delete from product where id=:id";

    public DeleteProduct(DataSource dataSource) {
        super(dataSource, SQL_DELETE_PRODUCT);
        super.declareParameter(new SqlParameter("id", Types.INTEGER ));
    }
    
    
    
}
