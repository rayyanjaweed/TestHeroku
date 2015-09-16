package com.online.lakeshoremarket.model.customer;

import com.online.lakeshoremarket.model.cart.Cart;

public interface Customer {
	
	public Address getBillingAddress();
	public void setBillingAddress(Address billingAddress);
	public Address getShippingAddress();
	public void setShippingAddress(Address shippingAddress);
	public Cart getShoppingCart();
	public void setShoppingCart(Cart shoppingCart);
	public int getCustomerId();
	public void setCustomerId(int customerId);
	public long getPhone();
	public void setPhone(long phone);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getPaypalCustID();
	public void setPaypalCustID(String paypalCustID);
	public String getTitle();
	public void setTitle(String title);
	
	
}
