package com.online.lakeshoremarket.model.order;

import java.sql.Timestamp;

public interface Order {

	public Timestamp getDatePurchased();
	public void setDatePurchased(Timestamp datePurchased);
	public Timestamp getDateRefunded();
	public void setDateRefunded(Timestamp dateRefunded);
	public int getCustomerID();
	public void setCustomerID(int customerID);
	public int getOrderID();
	public void setOrderID(int orderID);
	public int getOrderStatusCode();
	public void setOrderStatusCode(int orderStatusCode);
	public int getPaymentID();
	public void setPaymentID(int paymentID);
	public String getTrackingNumber();
	public void setTrackingNumber(String trackingNumber);
	
	
	
	//We might remove these two methods later on
	public int getProductID();
	public void setProductID(int productID);
	public int getQty();
	public void setQty(int qty);
	//We might remove the above two methods later on
	
	
}
