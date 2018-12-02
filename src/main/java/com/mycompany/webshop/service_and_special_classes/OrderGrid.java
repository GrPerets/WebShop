/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.service_and_special_classes;

import java.util.Set;

/**
 *
 * @author grperets
 */
public class OrderGrid<Order> {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private Set<Order> orderData;

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

    public Set<Order> getOrderData() {
        return orderData;
    }

    public void setOrderData(Set<Order> orderData) {
        this.orderData = orderData;
    }
    
    
    
}
