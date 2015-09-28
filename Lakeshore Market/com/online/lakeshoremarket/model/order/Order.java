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
	
	
}
