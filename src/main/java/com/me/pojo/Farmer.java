package com.me.pojo;


import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;

@Entity
public class Farmer  {

	@Id
	@GeneratedValue
	private int farmerId;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PersonDetails_Id")
	private Person person;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="farmer")
	@NotFound(action=NotFoundAction.IGNORE)
    private List<Crop> cropList;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="farmer1")
	private List<FarmerOrder> orderList;
	



	/**
	 * @return the orderList
	 */
	public List<FarmerOrder> getOrderList() {
		return orderList;
	}

	/**
	 * @param orderList the orderList to set
	 */
	public void setOrderList(List<FarmerOrder> orderList) {
		this.orderList = orderList;
	}

	/**
	 * @return the cropList
	 */
	public List<Crop> getCropList() {
		return cropList;
	}

	/**
	 * @param cropList the cropList to set
	 */
	public void setCropList(List<Crop> cropList) {
		this.cropList = cropList;
	}

	/**
	 * @return the farmerId
	 */
	public int getFarmerId() {
		return farmerId;
	}

	/**
	 * @param farmerId the farmerId to set
	 */
	public void setFarmerId(int farmerId) {
		this.farmerId = farmerId;
	}

	/**
	 * @return the person
	 */
	public Person getPerson() {
		return person;
	}

	/**
	 * @param person the person to set
	 */
	public void setPerson(Person person) {
		this.person = person;
	}
	
	
}
