/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.basket;

import com.mycompany.webshop.db.product.Product;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

/**
 *
 * @author grperets
 */

public class Basket implements Serializable {
    private Set<Product> products = new HashSet<Product>();
    private Double total = 0.00;     
            
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    
    
    public void addProduct (Product product) {
        getProducts().add(product);
    }
    
    public void removeProduct (Product deleteProduct) {
        Iterator<Product> iterator = products.iterator();
        while (iterator.hasNext()) {
            Product basketProduct = iterator.next();
            if (basketProduct.getId()== deleteProduct.getId()) {
                iterator.remove();
            }
        }
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    public void addPrice (Double price) {
        this.setTotal(this.total + price);
    }
    
    public void removePrice (Double price) {
        setTotal(this.total - price);
    }
    
        
}
