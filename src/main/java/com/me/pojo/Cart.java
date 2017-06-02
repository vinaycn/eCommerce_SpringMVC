package com.me.pojo;

public class Cart {

	private int selectedQuantity;
	private int totalPrice;
	private Product product;

	/**
	 * @return the selectedQuantity
	 */
	public int getSelectedQuantity() {
		return selectedQuantity;
	}
	/**
	 * @return the totalPrice
	 */
	public int getTotalPrice() {
		return totalPrice;
	}
	/**
	 * @return the product
	 */
	public Product getProduct() {
		return product;
	}
	
	/**
	 * @param selectedQuantity the selectedQuantity to set
	 */
	public void setSelectedQuantity(int selectedQuantity) {
		this.selectedQuantity = selectedQuantity;
	}
	/**
	 * @param totalPrice the totalPrice to set
	 */
	public void setTotalPrice(int totalPrice) {
		this.totalPrice = totalPrice;
	}
	/**
	 * @param product the product to set
	 */
	public void setProduct(Product product) {
		this.product = product;
	}
}
