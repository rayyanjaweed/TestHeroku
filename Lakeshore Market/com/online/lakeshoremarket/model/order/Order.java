package com.online.lakeshoremarket.model.order;

import java.util.Date;

public interface Order {

	public Date getDatePurchased();
	public void setDatePurchased(Date datePurchased);
	public Date getDateRefunded();
	public void setDateRefunded(Date dateRefunded);
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
