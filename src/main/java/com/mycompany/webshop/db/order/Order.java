/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.order;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.webshop.db.customer.Customer;
import com.mycompany.webshop.db.payment.PaymentType;
import com.mycompany.webshop.db.product.Product;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
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

@Entity
@Table (name = "customer_order")
public class Order implements Serializable {
    private Long id;
    private int version;
    private Customer customer;
    private DateTime orderDate;
    private String state = "checked";
    private Set<Product> products = new HashSet<Product>();
    
    private String deliveryAddress;
    private PaymentType paymentType;
    private Double total;

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

    @ManyToOne
    @JoinColumn (name = "customer_id")
    @JsonBackReference
    //@JsonManagedReference
    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Column (name = "order_date")
    @Type (type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    public DateTime getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(DateTime orderDate) {
        this.orderDate = orderDate;
    }

    @Transient
    public String getOrderDateString() {
        String orderDateString = "";
        if (orderDate != null) 
            orderDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(orderDate);
        return orderDateString;
    }

    @Column (name = "state", nullable = false)
    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
    
    //@JsonBackReference
    @JsonIgnore
    //@JsonManagedReference
    @ManyToMany
    @JoinTable (name = "customer_order_product_detail",
            joinColumns = @JoinColumn (name = "order_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "product_id", referencedColumnName = "id"))
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
    
    
    @Column (name = "delivery_address")
    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public void setDeliveryAddress(String deliveryAddress) {
        this.deliveryAddress = deliveryAddress;
    }

    
    @ManyToOne
    @JoinColumn (name = "payment_type_id")
    //@JsonBackReference
    //@JsonManagedReference
    @JsonIgnore
    //@Column (name = "payment_type_id")
    public PaymentType getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(PaymentType paymentType) {
        this.paymentType = paymentType;
    }

    @Column (name = "total")
    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
    
    /*
    @Override
    public String toString () {
        return null;
    }
    */

}
