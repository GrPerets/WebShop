package com.mycompany.webshop.db.metamodels;

import com.mycompany.webshop.db.customer.*;
import com.mycompany.webshop.db.basket.Basket;
import javax.annotation.Generated;
import javax.persistence.metamodel.SetAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.joda.time.DateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Customer.class)
public abstract class Customer_ {

	public static volatile SingularAttribute<Customer, String> firstName;
	public static volatile SingularAttribute<Customer, String> lastName;
	public static volatile SingularAttribute<Customer, String> password;
	public static volatile SetAttribute<Customer, Basket> baskets;
	public static volatile SingularAttribute<Customer, String> phoneNumber;
	public static volatile SingularAttribute<Customer, String> address;
	public static volatile SingularAttribute<Customer, Long> id;
	public static volatile SingularAttribute<Customer, Integer> version;
	public static volatile SingularAttribute<Customer, DateTime> birthDate;
	public static volatile SingularAttribute<Customer, String> email;

	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String PASSWORD = "password";
	public static final String BASKETS = "baskets";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String ADDRESS = "address";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String BIRTH_DATE = "birthDate";
	public static final String EMAIL = "email";

}

