/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.photo;

import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.order.Order;
import com.mycompany.webshop.db.product.Product;
import java.util.Set;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

/**
 *
 * @author grperets
 */
public interface ProductPhotoService {
    Set<ProductPhoto> findAll();
    ProductPhoto findById(Long id);
    Set<ProductPhoto> findByProduct(Product product);
    ProductPhoto save (ProductPhoto productPhoto);
    void delete (Long id);
    Page<ProductPhoto> findAllByPage(Pageable pageable);
}
