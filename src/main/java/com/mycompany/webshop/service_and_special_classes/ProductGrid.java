/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.service_and_special_classes;

import com.mycompany.webshop.db.product.Product;
import java.util.List;
import java.util.Set;

/**
 *
 * @author grperets
 */
public class ProductGrid<Product> {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private Set<Product> productData;

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(int totalPages) {
        this.totalPages = totalPages;
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public long getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(long totalRecords) {
        this.totalRecords = totalRecords;
    }

    public Set<Product> getProductData() {
        return productData;
    }

    public void setProductData(Set<Product> productData) {
        this.productData = productData;
    }
    
    
}
