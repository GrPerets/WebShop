/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.product;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.mycompany.webshop.db.basket.Basket;
import com.mycompany.webshop.db.order.Order;
import com.mycompany.webshop.db.photo.ProductPhoto;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author grperets
 */
@Entity
@Table(name="product")
@NamedQueries({
    @NamedQuery(name="Product.findAll",
        query="select p from Product p"),
    @NamedQuery(name="Product.findById",
        query="select p from Product p  where p.id = :id"),
    @NamedQuery(name="Product.findProductByModel",
        query="select p from Product p where p.model=:model"),
})
public class Product implements Serializable{
    private Long id;
    private int version;
    private String model;
    private String category;
    private String manufacturer;
    private Double price;
    private String description;
    private byte[] photo;
    private DateTime dateLastModified;
    private Set<Order> orders = new HashSet<Order>();
    private Set<ProductPhoto> productPhotos = new HashSet<ProductPhoto>();
    
     

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
    
    @Column (name = "model", unique = true, nullable = false)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column (name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column (name = "manufacturer")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column (name = "price", nullable = false)
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column (name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    /*
    @Basic (fetch = FetchType.LAZY)
    @Lob
    @Column (name="photo")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    */

    @OneToMany (mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    public Set<ProductPhoto> getProductPhotos() {
        return productPhotos;
    }

    public void setProductPhotos(Set<ProductPhoto> productPhotos) {
        this.productPhotos = productPhotos;
    }
    
    public void addProductPhoto (ProductPhoto productPhoto) {
        productPhoto.setProduct(this);
        getProductPhotos().add(productPhoto);
    }
    
    public void removeProductPhoto (ProductPhoto productPhoto) {
        getProductPhotos().remove(productPhoto);
    }
       
    
    @Column (name="date_last_modified")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso =ISO.DATE)
    public DateTime getDateLastModified() {
        return dateLastModified;
    }

    public void setDateLastModified(DateTime dateLastModified) {
        this.dateLastModified = dateLastModified;
    }
    
    @Transient
    public String getDateLastModifiedString (){
        String dateLastModifiedString = "";
        if (dateLastModified != null)
            dateLastModifiedString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(dateLastModified);
        return dateLastModifiedString;
    }
    
    
    @ManyToMany
    @JoinTable (name = "customer_order_product_detail",
            joinColumns = @JoinColumn (name = "product_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn (name = "order_id", referencedColumnName = "id"))
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    


    
    @Override
    public String toString() {
        return "Product - Id: "+id+", Model: "+model+", Category: "+category+", Manufacturer: "+manufacturer+", Price: "+price;
    }

    
    
}
