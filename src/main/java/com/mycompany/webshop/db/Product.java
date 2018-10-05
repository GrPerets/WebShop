/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Version;

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
    @NamedQuery(name="Product.findProductByCategory",
        query="from Product where category_id=:category_id"),
    @NamedQuery(name="Product.findProductByManufacturer",
        query="from Product where manufacturer_id=:manufacturer_id")
})
public class Product implements Serializable{
    private Long id;
    private int version;
    private String model;
    private String categoryId;
    private String manufacturerId;
    private Double price;

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
    
    @Column (name = "MODEL")
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Column (name = "CATEGORY_ID")
    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    @Column (name = "MANUFACTURER_ID")
    public String getManufacturerId() {
        return manufacturerId;
    }

    public void setManufacturerId(String manufacturerId) {
        this.manufacturerId = manufacturerId;
    }

    @Column (name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    
    @Override
    public String toString() {
        return "Product - Id: "+id+", Model: "+model+", Category: "+categoryId+", Manufacturer: "+manufacturerId+", Price: "+price;
    }
    
}
