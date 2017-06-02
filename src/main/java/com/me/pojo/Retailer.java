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

@Entity
public class Retailer {
 
	@Id
	@GeneratedValue
	private int id;
	@OneToOne(fetch=FetchType.EAGER)
	@JoinColumn(name="PersonDetails_Id")
	private Person person;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="retailer")
	private List<Bids> bidList;

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

	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}

	/**
	 * @return the bidList
	 */
	public List<Bids> getBidList() {
		return bidList;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * @param bidList the bidList to set
	 */
	public void setBidList(List<Bids> bidList) {
		this.bidList = bidList;
	}
	
}
