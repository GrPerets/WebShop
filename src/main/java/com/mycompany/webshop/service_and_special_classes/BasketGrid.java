/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.service_and_special_classes;

import com.mycompany.webshop.db.basket.Basket;
import java.util.List;

/**
 *
 * @author grperets
 */
public class BasketGrid<Basket> {
    private int totalPages;
    private int currentPage;
    private long totalRecords;
    private List<Basket> basketData;

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

    public List<Basket> getBasketData() {
        return basketData;
    }

    public void setBasketData(List<Basket> basketData) {
        this.basketData = basketData;
    }
    
    
}
