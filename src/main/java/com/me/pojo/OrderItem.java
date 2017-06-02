package com.me.pojo;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class OrderItem {
    
	@Id
	@GeneratedValue
	private int orderItemId;
    private int selectedQuantity;
    @OneToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
    @JoinColumn(name="Product_Id")
    private Product product;
    private long orderItemPrice;
    @ManyToOne(fetch=FetchType.EAGER,cascade=CascadeType.ALL)
    @JoinColumn(name="Order_Id")
    private FarmerOrder forder;
    private String status;
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
	 * @return the forder
	 */
	public FarmerOrder getForder() {
		return forder;
	}

	/**
	 * @param forder the forder to set
	 */
	public void setForder(FarmerOrder forder) {
		this.forder = forder;
	}

	/**
	 * @return the orderItemPrice
	 */
	public long getOrderItemPrice() {
		return orderItemPrice;
	}
	
	/**
	 * @param orderItemPrice the orderItemPrice to set
	 */
	public void setOrderItemPrice(long orderItemPrice) {
		this.orderItemPrice = orderItemPrice;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
	/**
	 * @return the orderItemId
	 */
	public int getOrderItemId() {
		return orderItemId;
	}
	/**
	 * @return the selectedQuantity
	 */
	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	
	/**
	 * @param orderItemId the orderItemId to set
	 */
	public void setOrderItemId(int orderItemId) {
		this.orderItemId = orderItemId;
	}
	/**
	 * @param selectedQuantity the selectedQuantity to set
	 */
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	
}
