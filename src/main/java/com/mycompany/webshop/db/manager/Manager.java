/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.webshop.db.manager;

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
@Table(name="manager")
public class Manager implements Serializable {
    private Long id;
    private int version;
    private boolean enabled = true;
    private String authority = "ROLE_MANAGER";
    private String username;
    private String password;
    private String firstName;
    private String lastName;    
    private String email;
    private DateTime registrationDate;

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

    @Column (name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    @Column (name = "email", unique = true, nullable = false)
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    @Column (name = "registration_date")
    @Type (type="org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat (iso = DateTimeFormat.ISO.DATE)
    public DateTime getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(DateTime registrationDate) {
        this.registrationDate = registrationDate;
    }
    
    @Transient
    public String getRegistrationDateString() {
        String registrationDateString = "";
        if (registrationDate != null) 
            registrationDateString = org.joda.time.format.DateTimeFormat.forPattern("yyyy-MM-dd").print(registrationDate);
        return registrationDateString;
    }
    
    
}
