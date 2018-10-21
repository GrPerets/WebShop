/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
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

    @Column (name = "CATEGORY")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    @Column (name = "MANUFACTURER")
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    @Column (name = "PRICE")
    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Column (name = "DESCRIPTION")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Basic (fetch = FetchType.LAZY)
    @Lob
    @Column (name="PHOTO")
    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }
    
    @Column (name="DATE_LAST_MODIFIED")
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
    
    
    
    @Override
    public String toString() {
        return "Product - Id: "+id+", Model: "+model+", Category: "+category+", Manufacturer: "+manufacturer+", Price: "+price;
    }

    
    
}
