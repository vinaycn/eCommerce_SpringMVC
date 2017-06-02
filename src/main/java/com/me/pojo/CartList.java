package com.me.pojo;

import java.util.ArrayList;
import java.util.List;

public class CartList {

	private List<Cart> cartList;

	public CartList()
	{
		cartList=new ArrayList<Cart>();
	}
	/**
	 * @return the cartList
	 */
	public List<Cart> getCartList() {
		return cartList;
	}

	/**
	 * @param cartList the cartList to set
	 */
	public void setCartList(List<Cart> cartList) {
		this.cartList = cartList;
	}
	
}
