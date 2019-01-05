/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.customer;

import com.fasterxml.jackson.annotation.JsonBackReference;
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
import javax.persistence.Transient;
import javax.persistence.Version;
import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.format.annotation.DateTimeFormat.ISO;

/**
 *
 * @author grperets
 */
@Entity
@Table(name="customer")
public class Customer implements Serializable {
    private Long id;
    private int version;
    private boolean enabled = true;
    private String authority = "ROLE_CUSTOMER";
    private String phoneNumber;
    private String password;
    private String firstName;
    private String lastName;    
    private String email;
    private String address;
    private DateTime birthDate;
    private Set<Order> orders = new HashSet<Order>();

    public Customer() {
    }
    
    

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
    
    @Column (name = "enabled", nullable = false)
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }
    
    @Column (name = "authority", nullable = false)
    public String getAuthority() {
        return authority;
    }

    public void setAuthority(String authority) {
        this.authority = authority;
    }
    

    
    @NotEmpty (message = "{validation.phonenumber.NotEmpty.message}")
    @Range (min=10, max=13, message="{validation.phonenumber.Size.message}")
    @Column (name = "phone_number", unique = true, nullable = false)
    public String getPhoneNumber() {
        return phoneNumber;
    }

    

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    
    @NotEmpty (message = "{validation.password.NotEmpty.message}")
    //@Size (min=6, message = "{validation.password.Size.message}")
    @Column (name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column (name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Column (name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Email
    @NotEmpty (message ="{validation.email.NotEmpty.message}")
    @Column (name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column (name = "address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column (name = "birth_date")
    @Type (type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso = ISO.DATE)
    public DateTime getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(DateTime birthDate) {
        this.birthDate = birthDate;
    }
    
    @Transient
    public String getBirthDateString() {
        String birthDateString = "";
        if (birthDate != null) 
            birthDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(birthDate);
        return birthDateString;
    }

    @OneToMany (mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval=true)
    @JsonManagedReference
    //@JsonBackReference
    public Set<Order> getOrders() {
        return orders;
    }

    public void setOrders(Set<Order> orders) {
        this.orders = orders;
    }
    
    public void addOrder (Order order) {
        order.setCustomer(this);
        getOrders().add(order);
    }
    
    public void removeOrder (Order order) {
        getOrders().remove(order);
    }
    
    
    
}
