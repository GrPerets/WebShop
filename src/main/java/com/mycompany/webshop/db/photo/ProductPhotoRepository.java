/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.photo;

import com.mycompany.webshop.db.product.Product;
import java.util.Set;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 *
 * @author grperets
 */
public interface ProductPhotoRepository extends PagingAndSortingRepository<ProductPhoto, Long>{
    Set<ProductPhoto> findByProduct(Product product);
}
