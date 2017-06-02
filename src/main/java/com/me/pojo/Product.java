package com.me.pojo;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;




@Entity
public class Product {
	
@Id
@GeneratedValue	
 private int productId;
 @NotEmpty(message="Name is Required")
 @Pattern(regexp="[\\p{L} .'-]+$",message="Please Enter a Valid Product Name")
 private String name;
 @Min(value=1,message="you should add atleast one Quantity")
 private int availableQuantity;
 @Min(value=1,message="Price should be atlest 1 !")
 private int pricePerUnit;
 @ManyToOne
 @JoinColumn(name="Supplier_ID")
 private Supplier supplier;
 @ManyToOne(fetch=FetchType.EAGER)
 @JoinColumn(name="Category_ID")
 private ProductCategory pCategory;
 @NotEmpty(message="Description")
 private String description;
 @Transient
 private MultipartFile productImage;
 private String getPhotoName;
 /**
 * @return the description
 */
public String getDescription() {
	return description;
}
/**
 * @return the pCategory
 */
public ProductCategory getpCategory() {
	return pCategory;
}
/**
 * @param description the description to set
 */
public void setDescription(String description) {
	this.description = description;
}
/**
 * @param pCategory the pCategory to set
 */
public void setpCategory(ProductCategory pCategory) {
	this.pCategory = pCategory;
}
/**
 * @return the category
 */
public ProductCategory getCategory() {
	return pCategory;
}
/**
 * @param category the category to set
 */
public void setCategory(ProductCategory pCategory) {
	this.pCategory = pCategory;
}
/**
 * @return the supplier
 */

public Supplier getSupplier() {
	return supplier;
}
/**
 * @param supplier the supplier to set
 */
public void setSupplier(Supplier supplier) {
	this.supplier = supplier;
}

/**
 * @return the productId
 */

public int getProductId() {
	return productId;
}
/**
 * @return the name
 */
public String getName() {
	return name;
}
/**
 * @return the availableQuantity
 */
public int getAvailableQuantity() {
	return availableQuantity;
}
/**
 * @return the availablePrice
 */

/**
 * @param productId the productId to set
 */
public void setProductId(int productId) {
	this.productId = productId;
}
/**
 * @return the pricePerUnit
 */
public int getPricePerUnit() {
	return pricePerUnit;
}
/**
 * @param pricePerUnit the pricePerUnit to set
 */
public void setPricePerUnit(int pricePerUnit) {
	this.pricePerUnit = pricePerUnit;
}
/**
 * @param name the name to set
 */
public void setName(String name) {
	this.name = name;
}

/**
 * @param availableQuantity the availableQuantity to set
 */
public void setAvailableQuantity(int availableQuantity) {
	this.availableQuantity = availableQuantity;
}
/**
 * @param availablePrice the availablePrice to set
 */
/**
 * @return the productImage
 */
public MultipartFile getProductImage() {
	return productImage;
}
/**
 * @return the getPhotoName
 */
public String getGetPhotoName() {
	return getPhotoName;
}
/**
 * @param productImage the productImage to set
 */
public void setProductImage(MultipartFile productImage) {
	this.productImage = productImage;
}
/**
 * @param getPhotoName the getPhotoName to set
 */
public void setGetPhotoName(String getPhotoName) {
	this.getPhotoName = getPhotoName;
}

 
}
