package com.me.pojo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


@Entity
public class FarmerOrder {

	@Id
	@GeneratedValue
	private int orderId;
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "forder")
	private List<OrderItem> itemList;
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "Farmer_Id")
	private Farmer farmer1;
	private String orderedDate;
	/**
	 * @return the farmer1
	 */
	public Farmer getFarmer1() {
		return farmer1;
	}

	/**
	 * @param farmer1 the farmer1 to set
	 */
	public void setFarmer1(Farmer farmer1) {
		this.farmer1 = farmer1;
	}



	/**
	 * @return the orderId
	 */
	public int getOrderId() {
		return orderId;
	}

	/**
	 * @return the itemList
	 */
	public List<OrderItem> getItemList() {
		return itemList;
	}

	/**
	 * @param orderId
	 *            the orderId to set
	 */
	public void setOrderId(int orderId) {
		this.orderId = orderId;
	}

	/**
	 * @param itemList
	 *            the itemList to set
	 */
	public void setItemList(List<OrderItem> itemList) {
		this.itemList = itemList;
	}

	/**
	 * @return the orderedDate
	 */
	public String getOrderedDate() {
		return orderedDate;
	}

	/**
	 * @param orderedDate the orderedDate to set
	 */
	public void setOrderedDate(String orderedDate) {
		this.orderedDate = orderedDate;
	}



	



}
