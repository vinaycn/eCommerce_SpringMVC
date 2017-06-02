package com.me.pojo;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Bids {
 @Id
 @GeneratedValue
 private int id;
 private long price;
 private String createdOn;	
 @ManyToOne(fetch=FetchType.EAGER)
 @JoinColumn(name="Crop_Id")
 private Crop crop;
 @ManyToOne(fetch=FetchType.EAGER)
 @JoinColumn(name="Retailer_Id")
 private Retailer retailer;
 private String status;
/**
 * @return the id
 */
public int getId() {
	return id;
}
/**
 * @return the price
 */
public long getPrice() {
	return price;
}
/**
 * @return the createdOn
 */
public String getCreatedOn() {
	return createdOn;
}
/**
 * @return the crop
 */
public Crop getCrop() {
	return crop;
}
/**
 * @param id the id to set
 */
public void setId(int id) {
	this.id = id;
}
/**
 * @param price the price to set
 */
public void setPrice(long price) {
	this.price = price;
}
/**
 * @param createdOn the createdOn to set
 */
public void setCreatedOn(String createdOn) {
	this.createdOn = createdOn;
}
/**
 * @param crop the crop to set
 */
public void setCrop(Crop crop) {
	this.crop = crop;
}
/**
 * @return the retailer
 */
public Retailer getRetailer() {
	return retailer;
}
/**
 * @param retailer the retailer to set
 */
public void setRetailer(Retailer retailer) {
	this.retailer = retailer;
}
/**
 * @return the status
 */
public String getStatus() {
	return status;
}
/**
 * @param status the status to set
 */
public void setStatus(String status) {
	this.status = status;
}



}
