/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.jdbc;

import java.sql.Types;
import javax.sql.DataSource;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.object.SqlUpdate;

/**
 *
 * @author grperets
 */
public class UpdateProduct extends SqlUpdate {
    private static final String SQL_UPDATE_PRODUCT = "update product set model=:model, category=:category, manufacturer=:manufacturer, price=:price where id=:id";

    public UpdateProduct(DataSource dataSource) {
        super(dataSource, SQL_UPDATE_PRODUCT);
        super.declareParameter(new SqlParameter("model", Types.VARCHAR));
        super.declareParameter(new SqlParameter("category", Types.VARCHAR));
        super.declareParameter(new SqlParameter("manufacturer", Types.VARCHAR));
        super.declareParameter(new SqlParameter("price", Types.DOUBLE));
        super.declareParameter(new SqlParameter("id", Types.INTEGER));
    }
}
