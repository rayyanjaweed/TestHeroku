package com.online.lakeshoremarket.model.customer;

import com.online.lakeshoremarket.model.cart.Cart;


public class CustomerImpl implements Customer{

	private Address billingAddress;
	private Address shippingAddress;
	private Cart shoppingCart;
	private int customerId;
	private long phone;
	private String firstName;
	private String lastName;
	private String paypalCustID;
	private String title;
	
	
	public Address getBillingAddress() {
		return billingAddress;
	}
	public void setBillingAddress(Address billingAddress) {
		this.billingAddress = billingAddress;
	}
	public Address getShippingAddress() {
		return shippingAddress;
	}
	public void setShippingAddress(Address shippingAddress) {
		this.shippingAddress = shippingAddress;
	}
	public Cart getShoppingCart() {
		return shoppingCart;
	}
	public void setShoppingCart(Cart shoppingCart) {
		this.shoppingCart = shoppingCart;
	}
	public int getCustomerId() {
		return customerId;
	}
	public void setCustomerId(int customerId) {
		this.customerId = customerId;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPaypalCustID() {
		return paypalCustID;
	}
	public void setPaypalCustID(String paypalCustID) {
		this.paypalCustID = paypalCustID;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public long getPhone() {
		return phone;
	}
	public void setPhone(long phone) {
		this.phone = phone;
	}
		
	
}
