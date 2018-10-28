/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.basket;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
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
@Entity
@Table (name = "basket")
public class Basket implements Serializable {
    private Long id;
    private int version;
    private Long customerId;
    private DateTime orderDate;
    private boolean enabled;

    @Id
    @GeneratedValue (strategy = IDENTITY)
    @Column (name = "ID")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Version
    @Column (name = "VERSION")
    public int getVersion() {
        return version;
    }

    public void setVersion(int version) {
        this.version = version;
    }

    @Column (name = "CUSTOMER_ID")
    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    @Column (name = "ORDER_DATE")
    @Type (type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Column (name = "ENABLED")
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
        
}
