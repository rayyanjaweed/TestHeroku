package com.online.lakeshoremarket.model.customer;

public interface Customer {
	
	public int getBillingAddress();
	public void setBillingAddress(int billingAddress);
	public int getShippingAddress();
	public void setShippingAddress(int shippingAddress);
	public int getCustomerId();
	public void setCustomerId(int customerId);
	public String getEmail();
	public void setEmail(String email);
	public String getFirstName();
	public void setFirstName(String firstName);
	public String getLastName();
	public void setLastName(String lastName);
	public String getPassword();
	public void setPassword(String password);
	public String getPaypalCustID();
	public void setPaypalCustID(String paypalCustID);
	public String getPhone();
	public void setPhone(String phone);
	public String getTitle();
	public void setTitle(String title);
	
}
