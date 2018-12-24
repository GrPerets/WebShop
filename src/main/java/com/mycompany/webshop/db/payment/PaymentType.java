/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.payment;

//import static org.apache.commons.lang.WordUtils.*;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.webshop.db.order.Order;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Version;


/**
 *
 * @author grperets
 */
@Entity
@Table (name = "payment_type")
public class PaymentType implements Serializable {
    private Long id;
    private int version;
    private String paymentType;
    private Set<Order> orders = new HashSet<Order>();

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column (name = "version")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column (name = "payment_type", unique = true, nullable = false)
    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }
    
    
    @OneToMany (mappedBy = "paymentType", cascade = CascadeType.ALL, orphanRemoval=true)
    //@JsonBackReference
    @JsonManagedReference
    //@JsonIgnore
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    public void addOrder (Order order) {
        order.setPaymentType(this);
        getOrders().add(order);
    }
    
    public void removeOrder (Order order) {
        getOrders().remove(order);
    }
    
    /*
    @Override
    public String toString() {
        return paymentType;
    } 
*/
}
