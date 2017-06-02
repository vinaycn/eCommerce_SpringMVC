package com.me.business;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import com.me.pojo.Cart;
import com.me.pojo.CartList;
import com.me.pojo.Farmer;
import com.me.pojo.FarmerOrder;
import com.me.pojo.OrderItem;

public class Business {

	
	public CartList updateCartItem(int id,int sq,int tp,CartList cartList)
	{
		List<Cart> cList=cartList.getCartList();
		for(Cart cart:cList)
		{
			if(cart.getProduct().getProductId()==id)
			{
				cart.setSelectedQuantity(sq);
				cart.setTotalPrice(tp);
			}
		}
		return cartList;
	}
	
	public CartList deleteCartItem(int id,CartList cartList)
	{
		Iterator<Cart> cList=cartList.getCartList().iterator();
		while(cList.hasNext())
		{
			Cart cart=cList.next();
			if(cart.getProduct().getProductId()==id)
			{
				cList.remove();
			}
		}
		return cartList;
	}
	
	public FarmerOrder generateOrder(Farmer farmer,CartList cartList)
	{
		FarmerOrder order=new FarmerOrder();
		List<Cart> cList=cartList.getCartList();
		List<OrderItem> oi=new ArrayList<OrderItem>();
		for(Cart cart:cList)
		{
			System.out.println("coming.....");
			OrderItem orderItem=new OrderItem();
			orderItem.setProduct(cart.getProduct());
			orderItem.setSelectedQuantity(cart.getSelectedQuantity());
			orderItem.setStatus("Processing");
			orderItem.setOrderItemPrice(cart.getTotalPrice());
			orderItem.setForder(order);
			oi.add(orderItem);
		}

		Date date=new Date();
		java.text.SimpleDateFormat sdf = 
			     new java.text.SimpleDateFormat("yyyy-MM-dd");
		String currentTime = sdf.format(date);
		order.setItemList(oi);
		order.setFarmer1(farmer);
		order.setOrderedDate(currentTime);
		return order;
	}
	
	public long calculateCartTotalPrice(CartList cartList)
	{
		long tp=0;
		List<Cart> cList=cartList.getCartList();
		for(Cart cart:cList)
		{
			tp+=cart.getTotalPrice();
		}
		return tp;
	}
	
}
