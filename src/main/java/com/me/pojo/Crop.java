package com.me.pojo;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.Min;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.web.multipart.MultipartFile;

@Entity
public class Crop {
	@Id
	@GeneratedValue
	private int id;
	@NotEmpty(message="Name is Required")
	@Pattern(regexp="[\\p{L} .'-]+$",message="Please Enter a Valid Crop Name")
	private String name;
	@Min(value=1,message="you should add atleast one Quantity")
	private int quantity;
	@Min(value=1,message="your price should atleast 1")
	private int expectedPrice;
	@ManyToOne
	@JoinColumn(name="ByFarmer_Id")
	private Farmer farmer;
	@ManyToOne
	@JoinColumn(name="CropCategory_Id")
	private CropCategory cropCategory;
	@OneToMany(cascade=CascadeType.ALL,mappedBy="crop")
	private List<Bids> bidList;
	private String status;
	@Transient
	private MultipartFile cropPhoto;
	private String cropPhotoName;
	/**
	 * @return the cropCategory
	 */
	public CropCategory getCropCategory() {
		return cropCategory;
	}
	/**
	 * @param cropCategory the cropCategory to set
	 */
	public void setCropCategory(CropCategory cropCategory) {
		this.cropCategory = cropCategory;
	}
	/**
	 * @return the farmer
	 */
	public Farmer getFarmer() {
		return farmer;
	}
	/**
	 * @param farmer the farmer to set
	 */
	public void setFarmer(Farmer farmer) {
		this.farmer = farmer;
	}
	/**
	 * @return the id
	 */
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @return the quantity
	 */
	public int getQuantity() {
		return quantity;
	}
	/**
	 * @return the expectedPrice
	 */
	public int getExpectedPrice() {
		return expectedPrice;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	/**
	 * @param quantity the quantity to set
	 */
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}
	/**
	 * @param expectedPrice the expectedPrice to set
	 */
	public void setExpectedPrice(int expectedPrice) {
		this.expectedPrice = expectedPrice;
	}
	/**
	 * @return the bidList
	 */
	public List<Bids> getBidList() {
		return bidList;
	}
	/**
	 * @param bidList the bidList to set
	 */
	public void setBidList(List<Bids> bidList) {
		this.bidList = bidList;
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
	/**
	 * @return the cropPhoto
	 */
	public MultipartFile getCropPhoto() {
		return cropPhoto;
	}
	/**
	 * @return the cropPhotoName
	 */
	public String getCropPhotoName() {
		return cropPhotoName;
	}
	/**
	 * @param cropPhoto the cropPhoto to set
	 */
	public void setCropPhoto(MultipartFile cropPhoto) {
		this.cropPhoto = cropPhoto;
	}
	/**
	 * @param cropPhotoName the cropPhotoName to set
	 */
	public void setCropPhotoName(String cropPhotoName) {
		this.cropPhotoName = cropPhotoName;
	}
	
	
}
