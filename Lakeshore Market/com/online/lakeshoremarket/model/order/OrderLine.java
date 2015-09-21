package com.online.lakeshoremarket.model.order;

public interface OrderLine {

	public int getOrderLineID();
	public void setOrderLineID(int orderLineID);
	public int getOrderID();
	public void setOrderID(int orderID);
	public int getProductID();
	public void setProductID(int productID);
	public int getQuantity();
	public void setQuantity(int quantity);
	
	
}
