package com.mycompany.webshop.db.metamodels;



import com.mycompany.webshop.db.product.Product;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import org.joda.time.DateTime;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Product.class)
public abstract class Product_ {

	public static volatile SingularAttribute<Product, DateTime> dateLastModified;
	public static volatile SingularAttribute<Product, Double> price;
	public static volatile SingularAttribute<Product, String> description;
	public static volatile SingularAttribute<Product, byte[]> photo;
	public static volatile SingularAttribute<Product, String> model;
	public static volatile SingularAttribute<Product, Long> id;
	public static volatile SingularAttribute<Product, String> category;
	public static volatile SingularAttribute<Product, Integer> version;
	public static volatile SingularAttribute<Product, String> manufacturer;

	public static final String DATE_LAST_MODIFIED = "dateLastModified";
	public static final String PRICE = "price";
	public static final String DESCRIPTION = "description";
	public static final String PHOTO = "photo";
	public static final String MODEL = "model";
	public static final String ID = "id";
	public static final String CATEGORY = "category";
	public static final String VERSION = "version";
	public static final String MANUFACTURER = "manufacturer";

}
