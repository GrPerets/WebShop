package com.mycompany.webshop.db.metamodels;

import com.mycompany.webshop.db.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> manufacturerId;
	public static volatile SingularAttribute<Product, String> model;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, Integer> version;
	public static volatile SingularAttribute<Product, String> categoryId;

	public static final String PRICE = "price";
	public static final String MANUFACTURER_ID = "manufacturerId";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String VERSION = "version";
	public static final String CATEGORY_ID = "categoryId";

}
