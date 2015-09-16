package com.online.lakeshoremarket.model.cart;

public interface CartLine {

	public int getCartLineID();
	public void setCartLineID(int cartLineID);
	public int getCustomerID();
	public void setCustomerID(int customerID);
	public int getProductID();
	public void setProductID(int productID);
	public int getQuantity();
	public void setQuantity(int quantity);
	
	
}
