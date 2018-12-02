/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.basket;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.product.Product;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

/**
 *
 * @author grperets
 */
/*
@Entity
@Table (name = "basket")
*/
public class Basket implements Serializable {
    private DateTime orderDate;
    private boolean enabled;
    private Set<Product> products = new HashSet<Product>();
         
    
    
    @Type (type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    @Transient
    public String getOrderDateString() {
        String orderDateString = "";
        if (orderDate != null) 
            orderDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(orderDate);
        return orderDateString;
    }

        
    
    public Set<Product> getProducts() {
        return products;
    }

    public void setProducts(Set<Product> products) {
        this.products = products;
    }
    
    
    public void addProduct (Product product) {
        getProducts().add(product);
    }
    
    public void removeProduct (Product product) {
        getProducts().remove(product);
    }
    



    @Override
    public String toString () {
        return "Order Date: "+ orderDate + ", Product: " + products;
    }
    
        
}
