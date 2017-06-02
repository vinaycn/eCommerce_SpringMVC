package com.me.pojo;

import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Supplier {

	@Id
	@GeneratedValue
	private int supplierId;
	@OneToOne
	private Person person;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="supplier",fetch=FetchType.LAZY)
	@NotFound(action=NotFoundAction.IGNORE)
	private List<Product> productList;
	/**
	 * @return the supplierId
	 */
	public int getSupplierId() {
		return supplierId;
	}
	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}
	/**
	 * @param supplierId the supplierId to set
	 */
	public void setSupplierId(int supplierId) {
		this.supplierId = supplierId;
	}
	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
	/**
	 * @return the productList
	 */
	
	public List<Product> getProductList() {
		return productList;
	}
	
	/**
	 * @param productList the productList to set
	 */
	public void setProductList(List<Product> productList) {
		this.productList = productList;
	}
}
